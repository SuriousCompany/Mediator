package company.surious.mediator_domain.entities.users.doctors

import android.os.Parcel
import android.os.Parcelable
import company.surious.mediator_domain.entities.interfaces.HasGooglePlaceId
import company.surious.mediator_domain.entities.interfaces.Nameable
import company.surious.mediator_domain.entities.interfaces.UpdatableEntity
import company.surious.mediator_domain.entities.location.Location
import company.surious.mediator_domain.entities.utils.UpdatableEntityUtils

open class Hospital(
    override var id: Long = -1,
    override var placeId: String = "",
    var location: Location = Location(),
    override var name: String = "",
    override var nameRu: String? = null,
    override var nameUa: String? = null,
    open var description: String? = null,
    open var descriptionRu: String? = null,
    open var descriptionUa: String? = null
) : HasGooglePlaceId, Nameable, UpdatableEntity<Hospital>, Parcelable {

    override fun isChanged(anotherVersion: Hospital): Boolean {
        UpdatableEntityUtils.checkSameEntity(this, anotherVersion.id)
        return placeId != anotherVersion.placeId
                || name != anotherVersion.name
                || nameRu != anotherVersion.nameRu
                || nameUa != anotherVersion.nameUa
                || description != anotherVersion.description
                || descriptionRu != anotherVersion.descriptionRu
                || descriptionUa != anotherVersion.descriptionUa
                || location.isChanged(anotherVersion.location)
    }

    override fun update(anotherVersion: Hospital) {
        UpdatableEntityUtils.checkSameEntity(this, anotherVersion.id)
        placeId = anotherVersion.placeId
        name = anotherVersion.name
        nameRu = anotherVersion.nameRu
        nameUa = anotherVersion.nameUa
        description = anotherVersion.description
        descriptionRu = anotherVersion.descriptionRu
        descriptionUa = anotherVersion.descriptionUa
        location.update(anotherVersion.location)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Hospital
        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    constructor(source: Parcel) : this(
        source.readLong(),
        source.readString()!!,
        source.readParcelable<Location>(Location::class.java.classLoader)!!,
        source.readString()!!,
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeLong(id)
        writeString(placeId)
        writeParcelable(location, 0)
        writeString(name)
        writeString(nameRu)
        writeString(nameUa)
        writeString(description)
        writeString(descriptionRu)
        writeString(descriptionUa)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Hospital> = object : Parcelable.Creator<Hospital> {
            override fun createFromParcel(source: Parcel): Hospital = Hospital(source)
            override fun newArray(size: Int): Array<Hospital?> = arrayOfNulls(size)
        }
    }
}