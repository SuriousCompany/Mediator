package company.surious.mediator_presentation.di

import android.app.Application
import company.surious.mediator_data.di.DataManagersModule
import company.surious.mediator_presentation.core.MediatorApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [AndroidInjectionModule::class, AppModule::class, DataManagersModule::class,
        ActivityModule::class]
)
interface AppComponent {

    fun inject(app: MediatorApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}