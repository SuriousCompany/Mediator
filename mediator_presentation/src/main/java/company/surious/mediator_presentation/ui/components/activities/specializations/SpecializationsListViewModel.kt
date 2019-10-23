package company.surious.mediator_presentation.ui.components.activities.specializations

import android.util.Log
import androidx.lifecycle.ViewModel
import company.surious.mediator_domain.Language
import company.surious.mediator_domain.use_cases.specializations.ObserveSpecializationsParams
import company.surious.mediator_domain.use_cases.specializations.ObserveSpecializationsUseCase
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SpecializationsListViewModel @Inject constructor(observeSpecializationsUseCase: ObserveSpecializationsUseCase) :
    ViewModel() {
    var updateSpecializationsFunction: ((List<SelectableSpecialization>) -> Unit)? = null
    var excludeParentModels: Boolean = true
    private val compositeDisposable = CompositeDisposable()

    init {
        compositeDisposable.addAll(
            observeSpecializationsUseCase.execute(
                ObserveSpecializationsParams(Language.RU, excludeParentModels)
            ).subscribe({ specializations ->
            updateSpecializationsFunction?.invoke(specializations.map { specialization ->
                SelectableSpecialization.fromSpecialization(specialization)
            })
        }, {
            Log.e("specializations", "not registered", it)
            })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}