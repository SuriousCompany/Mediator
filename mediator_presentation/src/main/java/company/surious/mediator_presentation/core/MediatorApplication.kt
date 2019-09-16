package company.surious.mediator_presentation.core

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication

class MediatorApplication : MultiDexApplication() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}