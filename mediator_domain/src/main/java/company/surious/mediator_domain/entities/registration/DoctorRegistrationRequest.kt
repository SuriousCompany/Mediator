package company.surious.mediator_domain.entities.registration

import company.surious.mediator_domain.entities.users.doctors.Doctor

open class DoctorRegistrationRequest<T : Doctor>(
    override val id: Long,
    override val status: RegistrationRequestStatus,
    override val userId: Long,
    open val doctor: T,
    open val userPhotoUrl: String,
    open val userPassportPhotoUrl: String
) : RegistrationRequest(id, status, userId)