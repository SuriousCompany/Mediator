package company.surious.mediator_domain.entities.registration

import company.surious.mediator_domain.entities.interfaces.Identifiable

open class RegistrationRequest(
    override var id: Long = -1,
    open var status: RegistrationRequestStatus = RegistrationRequestStatus.CREATED,
    open var userId: Long = -1
) : Identifiable