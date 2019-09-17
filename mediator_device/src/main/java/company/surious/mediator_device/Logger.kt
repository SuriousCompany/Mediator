package company.surious.mediator_device

import android.util.Log

object Logger {
    fun e(tag: String, error: Throwable) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, "", error)
        }
    }
}