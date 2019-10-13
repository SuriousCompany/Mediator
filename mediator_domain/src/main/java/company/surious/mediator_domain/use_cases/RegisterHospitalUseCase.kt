package company.surious.mediator_domain.use_cases

import company.surious.mediator_domain.entities.users.doctors.Hospital
import company.surious.mediator_domain.managers.RegistrationManager
import company.surious.mediator_domain.use_cases.base.CompletableUseCase
import io.reactivex.Completable
import javax.inject.Inject

class RegisterHospitalUseCase
@Inject constructor(private val registrationManager: RegistrationManager) :
    CompletableUseCase<Hospital>() {
    override fun createCompletable(params: Hospital): Completable =
        registrationManager.registerHospital(params)
}