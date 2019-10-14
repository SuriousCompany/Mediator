package company.surious.mediator_domain

import android.os.Looper
import android.util.Log

object Logger {

    fun logThread(message: String?) {
        val thread = if (Looper.myLooper() == Looper.getMainLooper()) {
            "main thread"
        } else {
            "worker thread"
        }
        i("threading", "${message ?: ""}request on $thread")
    }

    fun i(tag: String, message: String) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, message)
        }
    }
}