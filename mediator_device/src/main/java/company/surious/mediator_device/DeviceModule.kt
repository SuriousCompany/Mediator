package company.surious.mediator_device

import android.content.Context
import company.surious.mediator_domain.managers.PreferencesManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DeviceModule {
    @Provides
    @Singleton
    @JvmStatic
    fun providePreferencesManager(context: Context): PreferencesManager =
        SharedPreferencesManager(context)
}