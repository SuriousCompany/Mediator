package company.surious.mediator_domain.managers

import company.surious.mediator_domain.entities.registration.DoctorRegistrationRequest
import company.surious.mediator_domain.entities.registration.HospitalRegistrationRequest
import io.reactivex.Completable

interface RegistrationManager {
    fun sendDoctorRegistrationRequest(request: DoctorRegistrationRequest): Completable
    fun sendHospitalRegistrationRequest(request: HospitalRegistrationRequest): Completable
}