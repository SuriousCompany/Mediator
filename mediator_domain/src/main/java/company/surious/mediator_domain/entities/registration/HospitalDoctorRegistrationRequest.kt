package company.surious.mediator_domain.entities.registration

import company.surious.mediator_domain.entities.users.doctors.HospitalDoctor

class HospitalDoctorRegistrationRequest(
    override val id: Long,
    override val status: RegistrationRequestStatus,
    override val userId: Long,
    override val doctor: HospitalDoctor,
    override val userPhotoUrl: String,
    override val userPassportPhotoUrl: String
) : DoctorRegistrationRequest<HospitalDoctor>(
    id,
    status,
    userId,
    doctor,
    userPhotoUrl,
    userPassportPhotoUrl
)
