package company.surious.mediator_domain.entities.location

import android.os.Parcel
import android.os.Parcelable
import company.surious.mediator_domain.entities.interfaces.UpdatableEntity
import company.surious.mediator_domain.entities.utils.UpdatableEntityUtils

class Location(
    override var id: Long = -1,
    var latitude: Float = 0f,
    var longitude: Float = 0f
) : UpdatableEntity<Location>, Parcelable {

    override fun isChanged(anotherVersion: Location): Boolean {
        UpdatableEntityUtils.checkSameEntity(this, anotherVersion.id)
        return latitude != anotherVersion.latitude
                || longitude != anotherVersion.longitude
    }

    override fun update(anotherVersion: Location) {
        UpdatableEntityUtils.checkSameEntity(this, anotherVersion.id)
        latitude = anotherVersion.latitude
        longitude = anotherVersion.longitude
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Location
        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    constructor(source: Parcel) : this(
        source.readLong(),
        source.readFloat(),
        source.readFloat()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeLong(id)
        writeFloat(latitude)
        writeFloat(longitude)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Location> = object : Parcelable.Creator<Location> {
            override fun createFromParcel(source: Parcel): Location = Location(source)
            override fun newArray(size: Int): Array<Location?> = arrayOfNulls(size)
        }
    }
}