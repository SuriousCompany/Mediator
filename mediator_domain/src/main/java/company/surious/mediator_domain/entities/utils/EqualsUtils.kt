package company.surious.mediator_domain.entities.utils

object EqualsUtils {
    fun nullEquals(a: Any?, b: Any?) =
        a == null && b == null || a != null && b != null

}