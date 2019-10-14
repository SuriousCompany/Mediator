package company.surious.mediator_domain.use_cases.registration

import company.surious.mediator_domain.entities.registration.DoctorRegistrationRequest
import company.surious.mediator_domain.managers.RegistrationManager
import company.surious.mediator_domain.use_cases.base.CompletableUseCase
import io.reactivex.Completable
import javax.inject.Inject

class SendDoctorRegistrationRequestUseCase
@Inject constructor(private val registrationManager: RegistrationManager) :
    CompletableUseCase<DoctorRegistrationRequest>() {
    override fun createCompletable(params: DoctorRegistrationRequest): Completable =
        registrationManager.sendDoctorRegistrationRequest(params)
}