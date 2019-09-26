package company.surious.mediator_presentation.di

import androidx.lifecycle.ViewModel
import company.surious.mediator_presentation.ui.components.view_models.SignInButtonViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(SignInButtonViewModel::class)
    internal abstract fun bindSignInButtonViewModel(viewModel: SignInButtonViewModel): ViewModel
}