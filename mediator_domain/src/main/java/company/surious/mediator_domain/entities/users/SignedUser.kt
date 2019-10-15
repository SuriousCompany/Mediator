package company.surious.mediator_domain.entities.users

import company.surious.mediator_domain.entities.interfaces.Identifiable

open class SignedUser(
    override var id: Long = -1,
    open var uId: String = "",
    open var email: String = "",
    open var familyName: String = "",
    open var familyNameRu: String? = null,
    open var familyNameUa: String? = null,
    open var givenName: String = "",
    open var givenNameRu: String? = null,
    open var givenNameUa: String? = null,
    open var photoUrl: String? = null,
    open var blocked: Boolean = false
) : Identifiable