package company.surious.mediator_domain.entities.users.doctors

import company.surious.mediator_domain.entities.location.Location
import company.surious.mediator_domain.entities.users.SignedUser

open class Doctor(

    open var category: String,
    open var specialization: String,
    open var education: String,
    open var workExperience: List<WorkExperience> = ArrayList(),

    override val id: Long,
    override val googleAuthId: String,
    override var displayName: String,
    override var email: String,
    override var familyName: String,
    override var givenName: String,
    open var coordinates: Location,
    open var type: DoctorType,

    open var aboutMe: String? = null,
    open var department: String? = null,
    open var floor: Int? = null,
    open var location: String? = null,
    open var scientificAndPracticalActivity: String? = null
) : SignedUser(id, googleAuthId, displayName, email, familyName, givenName)