package company.surious.mediator_presentation.ui.components.view_models

import android.util.Log
import androidx.lifecycle.ViewModel
import company.surious.mediator_domain.Language
import company.surious.mediator_domain.use_cases.specializations.ObserveSpecializationsUseCase
import javax.inject.Inject

class SpecializationsListViewModel @Inject constructor(private var observeSpecializationsUseCase: ObserveSpecializationsUseCase) :
    ViewModel() {

    fun onRegistrationButtonClick() {
        observeSpecializationsUseCase.execute(Language.RU).subscribe({
            Log.i("specializations", "registered")
        }, {
            Log.e("specializations", "not registered", it)
        })
    }
}