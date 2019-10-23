package company.surious.mediator_data.repositories.specializations

import android.os.Parcel
import android.os.Parcelable
import company.surious.mediator_domain.entities.interfaces.Nameable
import company.surious.mediator_domain.entities.interfaces.UpdatableEntity
import company.surious.mediator_domain.entities.utils.UpdatableEntityUtils

class SpecializationNetworkModel(
    override var id: Long = -1,
    override var name: String = "",
    override var nameRu: String? = null,
    override var nameUa: String? = null,
    var parentSpecializationId: Long = -1,
    var description: String = "",
    var descriptionRu: String = "",
    var descriptionUa: String = ""

) : UpdatableEntity<SpecializationNetworkModel>, Nameable, Parcelable {

    override fun isChanged(anotherVersion: SpecializationNetworkModel): Boolean {
        UpdatableEntityUtils.checkSameEntity(this, anotherVersion.id)
        return name != anotherVersion.name
                || nameRu != anotherVersion.nameRu
                || nameUa != anotherVersion.nameUa
                || description != anotherVersion.description
                || descriptionRu != anotherVersion.descriptionRu
                || descriptionUa != anotherVersion.descriptionUa
                || parentSpecializationId != anotherVersion.parentSpecializationId
    }

    override fun update(anotherVersion: SpecializationNetworkModel) {
        UpdatableEntityUtils.checkSameEntity(this, anotherVersion.id)
        name = anotherVersion.name
        nameRu = anotherVersion.nameRu
        nameUa = anotherVersion.nameUa
        description = anotherVersion.description
        descriptionRu = anotherVersion.descriptionRu
        descriptionUa = anotherVersion.descriptionUa
        parentSpecializationId = anotherVersion.parentSpecializationId
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as SpecializationNetworkModel
        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    constructor(source: Parcel) : this(
        source.readLong(),
        source.readString()!!,
        source.readString()!!,
        source.readString()!!,
        source.readLong(),
        source.readString()!!,
        source.readString()!!,
        source.readString()!!
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeLong(id)
        writeString(name)
        writeString(nameRu)
        writeString(nameUa)
        writeLong(parentSpecializationId)
        writeString(description)
        writeString(descriptionRu)
        writeString(descriptionUa)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<SpecializationNetworkModel> =
            object : Parcelable.Creator<SpecializationNetworkModel> {
                override fun createFromParcel(source: Parcel): SpecializationNetworkModel =
                    SpecializationNetworkModel(source)

                override fun newArray(size: Int): Array<SpecializationNetworkModel?> =
                    arrayOfNulls(size)
            }
    }
}