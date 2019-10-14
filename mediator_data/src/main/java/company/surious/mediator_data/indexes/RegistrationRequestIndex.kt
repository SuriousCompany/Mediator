package company.surious.mediator_data.indexes

import android.os.Parcel
import android.os.Parcelable
import company.surious.mediator_domain.entities.interfaces.UpdatableEntity
import company.surious.mediator_domain.entities.utils.UpdatableEntityUtils

class RegistrationRequestIndex(
    override var id: Long = -1,
    var userId: Long = -1,
    var registrationRequestType: RegistrationRequestType = RegistrationRequestType.DOCTOR
) : UpdatableEntity<RegistrationRequestIndex>, Parcelable {

    override fun isChanged(anotherVersion: RegistrationRequestIndex): Boolean {
        UpdatableEntityUtils.checkSameEntity(this, anotherVersion.id)
        return userId != anotherVersion.userId
                || registrationRequestType != anotherVersion.registrationRequestType
    }

    override fun update(anotherVersion: RegistrationRequestIndex) {
        UpdatableEntityUtils.checkSameEntity(this, anotherVersion.id)
        userId = anotherVersion.userId
        registrationRequestType = anotherVersion.registrationRequestType
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as RegistrationRequestIndex
        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    constructor(source: Parcel) : this(
        source.readLong(),
        source.readLong(),
        RegistrationRequestType.values()[source.readInt()]
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeLong(id)
        writeLong(userId)
        writeInt(registrationRequestType.ordinal)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<RegistrationRequestIndex> =
            object : Parcelable.Creator<RegistrationRequestIndex> {
                override fun createFromParcel(source: Parcel): RegistrationRequestIndex =
                    RegistrationRequestIndex(source)

                override fun newArray(size: Int): Array<RegistrationRequestIndex?> =
                    arrayOfNulls(size)
            }
    }
}