package company.surious.mediator_domain.entities.utils

import company.surious.mediator_domain.entities.interfaces.Identifiable

object IdentifiableUtils {
    fun <T : Identifiable> getById(id: Long, list: List<T>): T? =
        list.firstOrNull { it.id == id }

}