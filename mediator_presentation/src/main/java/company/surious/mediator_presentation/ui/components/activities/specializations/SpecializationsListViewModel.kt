package company.surious.mediator_presentation.ui.components.activities.specializations

import android.util.Log
import androidx.lifecycle.ViewModel
import company.surious.mediator_domain.Language
import company.surious.mediator_domain.entities.users.doctors.Specialization
import company.surious.mediator_domain.use_cases.specializations.ObserveSpecializationsUseCase
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SpecializationsListViewModel @Inject constructor(observeSpecializationsUseCase: ObserveSpecializationsUseCase) :
    ViewModel() {
    var updateSpecializationsFunction: ((List<Specialization>) -> Unit)? = null
    private val compositeDisposable = CompositeDisposable()

    init {
        compositeDisposable.addAll(observeSpecializationsUseCase.execute(Language.RU).subscribe({
            updateSpecializationsFunction?.invoke(it)
        }, {
            Log.e("specializations", "not registered", it)
        }))
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}