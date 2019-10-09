package company.surious.mediator_domain.entities.users.doctors

import company.surious.mediator_domain.entities.Identifiable

open class WorkExperience(
    override val id: Long,
    open val startYear: Int,
    open val startMonth: Int,
    open val finishYear: Int?,
    open val finishMonth: Int?,
    open val placeOfWork: String,
    open val position: String
) : Identifiable