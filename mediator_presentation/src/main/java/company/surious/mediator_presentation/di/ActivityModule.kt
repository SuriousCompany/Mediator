package company.surious.mediator_presentation.di

import company.surious.mediator_presentation.ui.components.activities.main.MainActivity
import company.surious.mediator_presentation.ui.components.activities.main.MainActivityComponent
import company.surious.mediator_presentation.ui.components.activities.specializations.SelectSpecializationsActivity
import company.surious.mediator_presentation.ui.components.activities.specializations.SelectSpecializationsActivityComponent
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(subcomponents = [MainActivityComponent::class, SelectSpecializationsActivityComponent::class])
interface ActivityModule {
    @Binds
    @IntoMap
    @ClassKey(MainActivity::class)
    fun bindMainActivity(builder: MainActivityComponent.Builder): AndroidInjector.Factory<*>

    @Binds
    @IntoMap
    @ClassKey(SelectSpecializationsActivity::class)
    fun bindSelectSpecializationsActivity(builder: SelectSpecializationsActivityComponent.Builder): AndroidInjector.Factory<*>

}