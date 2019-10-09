package company.surious.mediator_domain.entities.registration

import company.surious.mediator_domain.entities.Identifiable

open class RegistrationRequest(
    override val id: Long,
    open val status: RegistrationRequestStatus,
    open val userId: Long
) : Identifiable