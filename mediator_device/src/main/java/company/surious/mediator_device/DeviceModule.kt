package company.surious.mediator_device

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DeviceModule {
    @Provides
    @Singleton
    @JvmStatic
    fun providePrefs(context: Context): Prefs = Prefs(context)
}