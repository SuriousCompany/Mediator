package company.surious.mediator_domain.entities.users

import company.surious.mediator_domain.entities.interfaces.Identifiable

open class SignedUser(
    override var id: Long = -1,
    open var googleAuthId: String = "",
    open var displayName: String = "",
    open var email: String = "",
    open var familyName: String = "",
    open var givenName: String = "",
    open var photoUrl: String? = null,
    open var blocked: Boolean = false
) : Identifiable