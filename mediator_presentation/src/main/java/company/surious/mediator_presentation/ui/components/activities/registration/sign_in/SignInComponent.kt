package company.surious.mediator_presentation.ui.components.activities.registration.sign_in

import company.surious.mediator_presentation.di.ViewModelModule
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = [ViewModelModule::class])
interface SignInComponent : AndroidInjector<SignInFragment> {
    @Subcomponent.Factory
    interface Builder : AndroidInjector.Factory<SignInFragment>
}