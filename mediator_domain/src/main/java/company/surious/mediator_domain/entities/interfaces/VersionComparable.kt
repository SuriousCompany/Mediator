package company.surious.mediator_domain.entities.interfaces

interface VersionComparable<T> {
    fun isChanged(anotherVersion: T): Boolean
    fun update(anotherVersion: T)
}