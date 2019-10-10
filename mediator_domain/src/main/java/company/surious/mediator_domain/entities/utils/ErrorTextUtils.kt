package company.surious.mediator_domain.entities.utils

object ErrorTextUtils {
    fun notTheSameEntityText(entityClass: Class<*>, id: Long, argumentId: Long) =
        "Not the same ${entityClass.simpleName}. Id:$id, argument id:$argumentId"
}