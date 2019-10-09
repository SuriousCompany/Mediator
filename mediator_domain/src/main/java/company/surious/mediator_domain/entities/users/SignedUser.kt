package company.surious.mediator_domain.entities.users

import company.surious.mediator_domain.entities.Identifiable

open class SignedUser(
    override val id: Long,
    open val googleAuthId: String,
    open var displayName: String,
    open var email: String,
    open var familyName: String,
    open var givenName: String,
    open var photoUrl: String? = null
) : Identifiable