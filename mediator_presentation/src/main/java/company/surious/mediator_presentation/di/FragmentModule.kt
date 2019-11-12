package company.surious.mediator_presentation.di

import company.surious.mediator_presentation.ui.components.activities.registration.sign_in.SignInComponent
import company.surious.mediator_presentation.ui.components.activities.registration.sign_in.SignInFragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(subcomponents = [SignInComponent::class])
interface FragmentModule {

    @Binds
    @IntoMap
    @ClassKey(SignInFragment::class)
    fun bindSignInFragment(builder: SignInComponent.Builder): AndroidInjector.Factory<*>
}