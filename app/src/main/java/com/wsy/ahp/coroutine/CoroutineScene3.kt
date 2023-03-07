package com.wsy.ahp.coroutine

import android.content.res.AssetManager
import android.util.Log
import kotlinx.coroutines.suspendCancellableCoroutine
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * 异步的方式，读取assets目录下的文件，并且适配协程的写法，成为正真的挂起函数
 * 方便调用方直接以同步的形式拿到返回值
 */
object CoroutineScene3 {
    suspend fun parseAssetsFile(assetManager: AssetManager, fileName: String): String {
        return suspendCancellableCoroutine { continuation ->
            Thread(Runnable {
                val inputStream = assetManager.open(fileName)
                val br = BufferedReader(InputStreamReader(inputStream))
                var line: String?
                var stringBuilder = StringBuilder()
//                while ((line = br.readLine()) != null){}
                do {
                   line =  br.readLine()
                    if(line !=null) stringBuilder.append(line) else break
                } while (true)
                inputStream.close()
                br.close()
                Thread.sleep(2000)
                Log.e("coroutine file download","parse assets file completed")
                continuation.resumeWith(Result.success((stringBuilder.toString())))
            }).start()
        }
    }
}