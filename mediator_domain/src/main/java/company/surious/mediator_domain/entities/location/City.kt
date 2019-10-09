package company.surious.mediator_domain.entities.location

import company.surious.mediator_domain.entities.Identifiable
import company.surious.mediator_domain.entities.Nameable

data class City(
    override val id: Long,
    override val name: String,
    override val nameRu: String,
    override val nameUa: String
) : Identifiable,
    Nameable