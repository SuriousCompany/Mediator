package company.surious.mediator_domain.entities.interfaces

import android.os.Parcelable

interface UpdatableEntity<T> : VersionComparable<T>, Identifiable, Parcelable