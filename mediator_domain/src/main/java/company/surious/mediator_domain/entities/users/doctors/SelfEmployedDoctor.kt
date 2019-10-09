package company.surious.mediator_domain.entities.users.doctors

import company.surious.mediator_domain.entities.location.Location

class SelfEmployedDoctor(
    override var category: String,
    override var specialization: String,
    override var education: String,
    override var workExperience: List<WorkExperience>,
    override val id: Long,
    override val googleAuthId: String,
    override var displayName: String,
    override var email: String,
    override var familyName: String,
    override var givenName: String,
    override var coordinates: Location

) : Doctor(
    category,
    specialization,
    education,
    workExperience,
    id,
    googleAuthId,
    displayName,
    email,
    familyName,
    givenName,
    coordinates,
    DoctorType.SELF_EMPLOYED
)