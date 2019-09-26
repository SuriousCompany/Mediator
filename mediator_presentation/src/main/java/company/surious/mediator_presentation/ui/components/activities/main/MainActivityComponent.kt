package company.surious.mediator_presentation.ui.components.activities.main

import company.surious.mediator_presentation.di.ViewModelModule
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = [ViewModelModule::class])
interface MainActivityComponent : AndroidInjector<MainActivity> {
    @Subcomponent.Factory
    interface Builder : AndroidInjector.Factory<MainActivity>
}