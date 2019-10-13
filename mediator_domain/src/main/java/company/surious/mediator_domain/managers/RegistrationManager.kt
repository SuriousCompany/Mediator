package company.surious.mediator_domain.managers

import company.surious.mediator_domain.entities.registration.DoctorRegistrationRequest
import company.surious.mediator_domain.entities.users.doctors.Hospital
import company.surious.mediator_domain.entities.users.doctors.Specialization
import io.reactivex.Completable

interface RegistrationManager {
    fun sendDoctorRegistrationRequest(request: DoctorRegistrationRequest): Completable
    fun registerHospital(hospital: Hospital): Completable//remove it
    fun registerSpecification(specialization: Specialization): Completable
}