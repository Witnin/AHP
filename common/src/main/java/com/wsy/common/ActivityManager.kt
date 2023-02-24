package com.wsy.common

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.wsy.common.ui.component.HiBaseApplication
import java.lang.ref.WeakReference

class ActivityManager private constructor(){
    private val activityRefs = ArrayList<WeakReference<Activity>>()
    private val frontBackCallback = ArrayList<FrontBackCallback>()
    private var activityStartCount = 0
    private var front = true

    public fun init(application: Application){
        application.registerActivityLifecycleCallbacks(InnerActivityLifecycleCallbacks())
    }

    /**
     * inner内部类
     */
    inner class InnerActivityLifecycleCallbacks:android.app.Application.ActivityLifecycleCallbacks{
        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            activityRefs.add(WeakReference(activity))
        }

        override fun onActivityStarted(activity: Activity) {
            activityStartCount++
            //activityStartCount>0说明应用处于可见状态，也就是前台
            if(!front && activityStartCount>0){
                front = true
                onFrontBackChanged(front)
            }

        }

        override fun onActivityResumed(activity: Activity) {

        }

        override fun onActivityPaused(activity: Activity) {

        }

        override fun onActivityStopped(activity: Activity) {
            activityStartCount--
            //activityStartCount>0说明应用处于可见状态，也就是前台
            if(front && activityStartCount<=0){
                front = false
                onFrontBackChanged(front)
            }
        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

        }

        override fun onActivityDestroyed(activity: Activity) {
            for (activityRef:WeakReference<Activity> in activityRefs){
                if (activityRef !=null && activityRef.get() == activity){
                    activityRefs.remove(activityRef);
                    break
                }
            }
        }
    }

    private fun onFrontBackChanged(front: Boolean) {
        for (callback : FrontBackCallback in frontBackCallback){
            callback.onChanged(front)
        }
    }

    val topActivity:Activity?
    get() {
        if (activityRefs.size <=0){
            return null
        }else{
            return activityRefs[activityRefs.size-1].get()
        }
        return null
    }

    fun addFrontBackCallback(callback: FrontBackCallback){
        frontBackCallback.add(callback)
    }
    fun removeFrontBackCallback(callback: FrontBackCallback){
        frontBackCallback.remove(callback)
    }

    interface FrontBackCallback {
        fun onChanged(front:Boolean)
    }

    public companion object{
        val instance:ActivityManager by lazy(LazyThreadSafetyMode.SYNCHRONIZED){
            ActivityManager()
        }
    }
}