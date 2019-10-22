package company.surious.mediator_domain.use_cases.specializations

import company.surious.mediator_domain.Language
import company.surious.mediator_domain.entities.users.doctors.Specialization
import company.surious.mediator_domain.repositories.SpecializationsRepository
import company.surious.mediator_domain.use_cases.base.ObservableUseCase
import io.reactivex.Observable
import javax.inject.Inject

class ObserveSpecializationsUseCase @Inject constructor(
    private val specializationsRepository: SpecializationsRepository
) : ObservableUseCase<Language, List<Specialization>>() {
    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun createObservable(language: Language): Observable<List<Specialization>> =
        specializationsRepository.observeSpecializations(language)
}