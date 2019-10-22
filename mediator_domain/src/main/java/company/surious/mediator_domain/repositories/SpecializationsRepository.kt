package company.surious.mediator_domain.repositories

import company.surious.mediator_domain.Language
import company.surious.mediator_domain.entities.users.doctors.Specialization
import io.reactivex.Observable

interface SpecializationsRepository {
    fun observeSpecializations(language: Language): Observable<List<Specialization>>
}