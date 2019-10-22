package company.surious.mediator_presentation.ui.components.activities.specializations

import company.surious.mediator_presentation.di.ViewModelModule
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = [ViewModelModule::class])
interface SelectSpecializationsActivityComponent : AndroidInjector<SelectSpecializationsActivity> {
    @Subcomponent.Factory
    interface Builder : AndroidInjector.Factory<SelectSpecializationsActivity>
}