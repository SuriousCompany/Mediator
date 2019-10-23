package company.surious.mediator_presentation.ui.components.activities.specializations

import android.os.Parcel
import android.os.Parcelable
import company.surious.mediator_domain.entities.interfaces.Nameable
import company.surious.mediator_domain.entities.interfaces.UpdatableEntity
import company.surious.mediator_domain.entities.users.doctors.Specialization
import company.surious.mediator_domain.entities.utils.UpdatableEntityUtils
import company.surious.mediator_domain.entities.utils.isInnerObjectChanged
import company.surious.mediator_domain.entities.utils.updateInnerObject

class SelectableSpecialization(
    override var id: Long = -1,
    override var name: String = "",
    override var nameRu: String? = null,
    override var nameUa: String? = null,
    var parentSpecialization: SelectableSpecialization? = null,
    var description: String = "",
    var descriptionRu: String = "",
    var descriptionUa: String = "",
    var selected: Boolean = false
) : UpdatableEntity<SelectableSpecialization>, Nameable, Parcelable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as SelectableSpecialization
        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun isChanged(anotherVersion: SelectableSpecialization): Boolean {
        UpdatableEntityUtils.checkSameEntity(this, anotherVersion.id)
        return name != anotherVersion.name
                || nameRu != anotherVersion.nameRu
                || nameUa != anotherVersion.nameUa
                || description != anotherVersion.description
                || descriptionRu != anotherVersion.descriptionRu
                || descriptionUa != anotherVersion.descriptionUa
                || isInnerObjectChanged(anotherVersion.parentSpecialization, parentSpecialization)
                || selected != anotherVersion.selected
    }

    override fun update(anotherVersion: SelectableSpecialization) {
        UpdatableEntityUtils.checkSameEntity(this, anotherVersion.id)
        name = anotherVersion.name
        nameRu = anotherVersion.nameRu
        nameUa = anotherVersion.nameUa
        description = anotherVersion.description
        descriptionRu = anotherVersion.descriptionRu
        descriptionUa = anotherVersion.descriptionUa
        updateInnerObject(
            anotherVersion.parentSpecialization,
            parentSpecialization,
            { parentSpecialization = null },
            { parentSpecialization = anotherVersion.parentSpecialization }
        )
        selected = anotherVersion.selected
    }

    constructor(source: Parcel) : this(
        source.readLong(),
        source.readString()!!,
        source.readString(),
        source.readString(),
        source.readParcelable<SelectableSpecialization>(SelectableSpecialization::class.java.classLoader),
        source.readString()!!,
        source.readString()!!,
        source.readString()!!,
        1 == source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeLong(id)
        writeString(name)
        writeString(nameRu)
        writeString(nameUa)
        writeParcelable(parentSpecialization, 0)
        writeString(description)
        writeString(descriptionRu)
        writeString(descriptionUa)
        writeInt((if (selected) 1 else 0))
    }

    companion object {

        fun fromSpecialization(
            specialization: Specialization,
            mappedParentSpecialization: Specialization? = null
        ): SelectableSpecialization =
            SelectableSpecialization().apply {
                id = specialization.id
                name = specialization.name
                nameRu = specialization.nameRu
                nameUa = specialization.nameUa
                parentSpecialization =
                    if (mappedParentSpecialization != null) {
                        fromSpecialization(mappedParentSpecialization, null)
                    } else {
                        null
                    }
                description = specialization.description
                descriptionRu = specialization.descriptionRu
                descriptionUa = specialization.descriptionUa
            }

        @JvmField
        val CREATOR: Parcelable.Creator<SelectableSpecialization> =
            object : Parcelable.Creator<SelectableSpecialization> {
                override fun createFromParcel(source: Parcel): SelectableSpecialization =
                    SelectableSpecialization(source)

                override fun newArray(size: Int): Array<SelectableSpecialization?> =
                    arrayOfNulls(size)
            }
    }
}