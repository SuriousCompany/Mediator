package company.surious.mediator_data.di

import android.content.Context
import company.surious.mediator_data.FirebaseSignInManager
import company.surious.mediator_domain.managers.SignInManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DataManagersModule {
    @Provides
    @Singleton
    @JvmStatic
    fun provideSignInManager(context: Context): SignInManager = FirebaseSignInManager(context)
}