package company.surious.mediator_presentation.di

import androidx.lifecycle.ViewModel
import company.surious.mediator_presentation.ui.components.view_models.SignInButtonViewModel
import company.surious.mediator_presentation.ui.components.view_models.SpecializationsListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(SignInButtonViewModel::class)
    internal abstract fun bindSignInButtonViewModel(viewModel: SignInButtonViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SpecializationsListViewModel::class)
    internal abstract fun bindSpecializationsListViewModel(viewModel: SpecializationsListViewModel): ViewModel

}