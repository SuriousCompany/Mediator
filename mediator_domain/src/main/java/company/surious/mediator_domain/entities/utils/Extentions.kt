package company.surious.mediator_domain.entities.utils

import company.surious.mediator_domain.entities.interfaces.VersionComparable

fun <T, F : VersionComparable<F>> VersionComparable<T>.updateInnerObject(
    anotherVersionField: F?,
    currentField: F?,
    assignNull: () -> Unit,
    assignAnotherVersionField: () -> Unit
) {
    anotherVersionField?.let {
        currentField?.update(it) ?: assignAnotherVersionField.invoke()
    } ?: assignNull.invoke()
}

fun <T, F : VersionComparable<F>> VersionComparable<T>.isInnerObjectChanged(
    anotherVersionField: F?,
    currentField: F?
) = anotherVersionField?.let {
    currentField?.isChanged(anotherVersionField) ?: true
} ?: currentField == null

fun <T> MutableCollection<T>.setAll(anotherVersionList: Collection<T>) {
    clear()
    addAll(anotherVersionList)
}

fun <T> ArrayList<T>.setAll(anotherVersionList: Collection<T>) {
    clear()
    addAll(anotherVersionList)
}
