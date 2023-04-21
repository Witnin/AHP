package com.wsy.wsy_library.restful

import com.wsy.wsy_library.log.HiLog
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy
import java.util.concurrent.ConcurrentHashMap

class HiRestful constructor(val baserUrl: String, callFactory: HiCall.Factory) {
    private var interceptors: MutableList<HiInterceptor> = mutableListOf()
    private var methodService: ConcurrentHashMap<Method, MethodParser> = ConcurrentHashMap();
    private var scheduler: Scheduler

    fun addInterceptor(interceptor: HiInterceptor) {
        interceptors.add(interceptor)
    }

    init {
        scheduler = Scheduler(callFactory, interceptors)
    }

    /**
     * interface ApiService {
     *  @Headers("auth-token:token", "accountId:123456")
     *  @BaseUrl("https://api.devio.org/as/")
     *  @POST("/cities/{province}")
     *  @GET("/cities")
     * fun listCities(@Path("province") province: Int,@Filed("page") page: Int): HiCall<JsonObject>
     * }
     */
    fun <T> create(service: Class<T>): T {
        return Proxy.newProxyInstance(
            service.classLoader,
            arrayOf<Class<*>>(service)
        ) { proxy, method, args ->

            //bugFix:此处需要考虑 空参数
            var methodParser = methodService.get(method)
            HiLog.dt("BizInterceptor Http:", methodParser)
            if (methodParser == null) {
                methodParser = MethodParser.parse(baserUrl, method)
                methodService[method] = methodParser
            }

            //bugFix：此处 应当考虑到 methodParser复用，每次调用都应当解析入参
            val request = methodParser.newRequest(method, args)
            scheduler.newCall(request)
        } as T
    }


}