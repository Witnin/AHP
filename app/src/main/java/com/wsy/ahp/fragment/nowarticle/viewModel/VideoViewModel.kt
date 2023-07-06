package com.wsy.ahp.fragment.nowarticle.viewModel
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.mutableStateListOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.wsy.ahp.R
import com.wsy.ahp.fragment.nowarticle.MinVideoListFragment
import com.wsy.ahp.fragment.nowarticle.adapter.MainTabAdapter
import com.wsy.ahp.fragment.nowarticle.model.MinVideoModel
import com.wsy.ahp.fragment.nowarticle.model.TypeModel
import com.wsy.ahp.http.api.HomeApi
import com.wsy.ahp.http.common.ArouterUrl
import com.wsy.ahp.http.common.RetrofitServiceCreator
import com.wsy.ahp.model.entity.Type
import com.wsy.ahp.model.entity.TypeService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VideoViewModel:WsyViewModel() {

     var fragmentList:MutableList<Fragment>? = mutableListOf()
        private set
     var titles = mutableListOf(
         Type(0,"水质检测","1"),
         Type(1,"土壤","2"),
         Type(2,"气体检测","3"),
         Type(3,"食品","4"),
         Type(4,"噪音","5"),
         Type(5,"其他","6"),
       )
         private set

//    var titleList = listOf<Type>()
    var minVideoAdapter: MainTabAdapter? = null

    val mList:MutableList<MinVideoModel> = ArrayList()
    val mList2:MutableList<MinVideoModel> = ArrayList()


    val item7 = MinVideoModel("极光之夜","https://kcqzypt.oss-cn-beijing.aliyuncs.com/2022/08/11/0df65084705b7d60234a402b2ad88e9_1660198313678.jpg",1,2)
    val item8 = MinVideoModel("试纸检测","https://kcqzypt.oss-cn-beijing.aliyuncs.com/2022/08/11/0df65084705b7d60234a402b2ad88e9_1660198313678.jpg",1,2)

    init {

//        for (index in 0 until titles.size){
//            titleList.add(titles[index])
//        }
        typeList()

        repeat(20){
            mList!!.add(item7)
        }
        repeat(20){
            mList2!!.add(item8)
        }
    }

     fun typeList(){
        val homeApi = RetrofitServiceCreator.create(HomeApi::class.java)
        homeApi.type(1,10).enqueue(object : Callback<TypeService> {
            override fun onResponse(call: Call<TypeService>, response: Response<TypeService>) {
                if(response.isSuccessful){
                    if (response.body()!!.code == 200){
                        Log.d("home","${response.body()}")
                        val data = response.body()!!.result

                        val titleList = data.records.toList()
                        Log.d("home-titleList","${titleList}")

                        titles = titleList.toMutableList()
                        Log.d("home-titles","${titles}")
                    }else{
                        navigation(ArouterUrl.loginUrl)
                    }
                }else{
                    navigation(ArouterUrl.loginUrl)
                }

            }

            override fun onFailure(call: Call<TypeService>, t: Throwable) {
                navigation(ArouterUrl.HOME_ANSWER)
            }

        })
    }

}