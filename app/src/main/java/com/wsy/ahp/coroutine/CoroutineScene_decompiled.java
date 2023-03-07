package com.wsy.ahp.coroutine;

import android.util.Log;

import androidx.annotation.NonNull;
import kotlin.coroutines.intrinsics.IntrinsicsKt;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.DelayKt;


/**
 * suspend fun request2(): String {
 * delay(2*1000)
 * Log.e(TAG，"request2 completed")
 * return "result from request2"
 * *
 */
public class CoroutineScene_decompiled {
    private static String TAG = "CoroutineScene_decompiled";

    public static final Object request(Continuation preCallback) {
        ContinuationImpl requestContinuation;
       if(!(preCallback instanceof ContinuationImpl) || (((ContinuationImpl) preCallback).label & Integer.MIN_VALUE) ==0){
           requestContinuation = new ContinuationImpl(preCallback) {


               @Override
               Object invokeSuspend(@NonNull Object resumeResult) {
                   this.label |= Integer.MIN_VALUE;
                   this.result = resumeResult;
                   Log.e(TAG, "request2 has resumed");
                   return request(this);
               }
           };
       }else{
           requestContinuation = (ContinuationImpl) preCallback;
       }




        switch (requestContinuation.label) {
            case 0: {
                requestContinuation.label = 1;
                Object request2 = DelayKt.delay(2000, requestContinuation);
                if (request2 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    Log.e(TAG, "request2 has suspended");
                    return IntrinsicsKt.getCOROUTINE_SUSPENDED();
                }
            }
        }
        Log.e(TAG, "request2 completed");
        return "result from request2"+requestContinuation.preCallback;

    }

    static abstract class ContinuationImpl<T> implements Continuation<T> {
        private Continuation preCallback;
        int label;
        Object result;

        public ContinuationImpl(Continuation preCallback) {
            this.preCallback = preCallback;
        }

        @NonNull
        @Override
        public CoroutineContext getContext() {
            return preCallback.getContext();
        }

        @Override
        public void resumeWith(@NonNull Object resumeResult) {
            Object suspend = invokeSuspend(resumeResult);
            if (suspend == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                return ;
            }
            preCallback.resumeWith(suspend);
        }

        abstract Object invokeSuspend(@NonNull Object resumeResult);
    }
}
