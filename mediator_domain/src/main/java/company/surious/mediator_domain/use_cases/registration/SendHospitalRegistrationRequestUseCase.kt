package company.surious.mediator_domain.use_cases.registration

import company.surious.mediator_domain.entities.registration.HospitalRegistrationRequest
import company.surious.mediator_domain.managers.RegistrationManager
import company.surious.mediator_domain.use_cases.base.CompletableUseCase
import io.reactivex.Completable
import javax.inject.Inject

class SendHospitalRegistrationRequestUseCase
@Inject constructor(private val registrationManager: RegistrationManager) :
    CompletableUseCase<HospitalRegistrationRequest>() {
    override fun createCompletable(params: HospitalRegistrationRequest): Completable =
        registrationManager.sendHospitalRegistrationRequest(params)
}