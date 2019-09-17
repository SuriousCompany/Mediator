package company.surious.mediator_domain.entities

import company.surious.mediator_domain.Identifiable
import company.surious.mediator_domain.Nameable

data class City(override val id: Long, override val name: String) : Identifiable, Nameable