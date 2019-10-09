package company.surious.mediator_domain.entities.users.doctors

import company.surious.mediator_domain.entities.HasGooglePlaceId
import company.surious.mediator_domain.entities.Identifiable
import company.surious.mediator_domain.entities.Nameable

open class Hospital(
    override val placeId: String,
    override val id: Long,
    override val name: String,
    override val nameRu: String,
    override val nameUa: String,
    open val description: String?,
    open val descriptionRu: String?,
    open val descriptionUa: String?
) : HasGooglePlaceId, Identifiable, Nameable