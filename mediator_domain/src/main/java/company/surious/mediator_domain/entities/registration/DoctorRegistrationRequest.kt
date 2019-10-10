package company.surious.mediator_domain.entities.registration

import android.os.Parcel
import android.os.Parcelable
import company.surious.mediator_domain.entities.interfaces.UpdatableEntity
import company.surious.mediator_domain.entities.users.doctors.Doctor
import company.surious.mediator_domain.entities.utils.UpdatableEntityUtils

class DoctorRegistrationRequest(
    override var id: Long = -1,
    override var status: RegistrationRequestStatus = RegistrationRequestStatus.CREATED,
    override var userId: Long = -1,
    var doctor: Doctor = Doctor(),
    var userPhotoUrl: String = "",
    var userPassportPhotoUrl: String = ""
) : RegistrationRequest(id, status, userId), UpdatableEntity<DoctorRegistrationRequest>,
    Parcelable {

    override fun isChanged(anotherVersion: DoctorRegistrationRequest): Boolean {
        UpdatableEntityUtils.checkSameEntity(this, anotherVersion.id)
        return status != anotherVersion.status
                || userId != anotherVersion.userId
                || doctor.isChanged(anotherVersion.doctor)
                || userPhotoUrl != anotherVersion.userPhotoUrl
                || userPassportPhotoUrl != anotherVersion.userPassportPhotoUrl
    }

    override fun update(anotherVersion: DoctorRegistrationRequest) {
        UpdatableEntityUtils.checkSameEntity(this, anotherVersion.id)
        status = anotherVersion.status
        userId = anotherVersion.userId
        doctor.update(anotherVersion.doctor)
        userPhotoUrl = anotherVersion.userPhotoUrl
        userPassportPhotoUrl = anotherVersion.userPassportPhotoUrl
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as DoctorRegistrationRequest
        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    constructor(source: Parcel) : this(
        source.readLong(),
        RegistrationRequestStatus.values()[source.readInt()],
        source.readLong(),
        source.readParcelable<Doctor>(Doctor::class.java.classLoader)!!,
        source.readString()!!,
        source.readString()!!
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeLong(id)
        writeInt(status.ordinal)
        writeLong(userId)
        writeParcelable(doctor, 0)
        writeString(userPhotoUrl)
        writeString(userPassportPhotoUrl)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<DoctorRegistrationRequest> =
            object : Parcelable.Creator<DoctorRegistrationRequest> {
                override fun createFromParcel(source: Parcel): DoctorRegistrationRequest =
                    DoctorRegistrationRequest(source)

                override fun newArray(size: Int): Array<DoctorRegistrationRequest?> =
                    arrayOfNulls(size)
            }
    }
}
