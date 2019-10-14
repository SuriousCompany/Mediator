package company.surious.mediator_domain.entities.users

import company.surious.mediator_domain.entities.interfaces.Identifiable

abstract class SignedUser(
    override var id: Long = -1,
    open var googleAuthId: String = "",
    open var email: String = "",
    open var displayName: String = "",
    open var displayNameRu: String? = null,
    open var displayNameUa: String? = null,
    open var familyNameRu: String? = null,
    open var familyNameUa: String? = null,
    open var givenName: String = "",
    open var givenNameRu: String? = null,
    open var givenNameUa: String? = null,
    open var familyName: String = "",
    open var photoUrl: String? = null,
    open var blocked: Boolean = false
) : Identifiable