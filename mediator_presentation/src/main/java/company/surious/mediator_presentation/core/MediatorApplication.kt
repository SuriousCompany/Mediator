package company.surious.mediator_presentation.core

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import company.surious.mediator_data.FirebaseSignInManager
import company.surious.mediator_domain.SignInManager

class MediatorApplication : MultiDexApplication(), AppBridge {
    override lateinit var signInManager: SignInManager

    override fun onCreate() {
        super.onCreate()
        signInManager = FirebaseSignInManager(this)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}