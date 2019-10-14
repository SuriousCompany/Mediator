package company.surious.mediator_domain.entities.registration

import android.os.Parcel
import android.os.Parcelable
import company.surious.mediator_domain.entities.interfaces.UpdatableEntity
import company.surious.mediator_domain.entities.users.doctors.Hospital
import company.surious.mediator_domain.entities.utils.UpdatableEntityUtils

class HospitalRegistrationRequest(
    override var id: Long = -1,
    override var status: RegistrationRequestStatus = RegistrationRequestStatus.CREATED,
    override var userId: Long = -1,
    var doctorRegistrationRequest: DoctorRegistrationRequest = DoctorRegistrationRequest(),
    var hospital: Hospital = Hospital(),
    var responsiblePersonContact: String = ""
) : RegistrationRequest(id, status, userId), UpdatableEntity<HospitalRegistrationRequest>,
    Parcelable {

    override fun isChanged(anotherVersion: HospitalRegistrationRequest): Boolean {
        UpdatableEntityUtils.checkSameEntity(this, anotherVersion.id)
        return status != anotherVersion.status
                || userId != anotherVersion.userId
                || doctorRegistrationRequest.isChanged(anotherVersion.doctorRegistrationRequest)
                || hospital.isChanged(anotherVersion.hospital)
                || responsiblePersonContact != anotherVersion.responsiblePersonContact
    }

    override fun update(anotherVersion: HospitalRegistrationRequest) {
        UpdatableEntityUtils.checkSameEntity(this, anotherVersion.id)
        status = anotherVersion.status
        userId = anotherVersion.userId
        doctorRegistrationRequest.update(anotherVersion.doctorRegistrationRequest)
        hospital.update(anotherVersion.hospital)
        responsiblePersonContact = anotherVersion.responsiblePersonContact
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as HospitalRegistrationRequest
        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    constructor(source: Parcel) : this(
        source.readLong(),
        RegistrationRequestStatus.values()[source.readInt()],
        source.readLong(),
        source.readParcelable<DoctorRegistrationRequest>(DoctorRegistrationRequest::class.java.classLoader)!!,
        source.readParcelable<Hospital>(Hospital::class.java.classLoader)!!,
        source.readString()!!
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeLong(id)
        writeInt(status.ordinal)
        writeLong(userId)
        writeParcelable(doctorRegistrationRequest, 0)
        writeParcelable(hospital, 0)
        writeString(responsiblePersonContact)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<HospitalRegistrationRequest> =
            object : Parcelable.Creator<HospitalRegistrationRequest> {
                override fun createFromParcel(source: Parcel): HospitalRegistrationRequest =
                    HospitalRegistrationRequest(source)

                override fun newArray(size: Int): Array<HospitalRegistrationRequest?> =
                    arrayOfNulls(size)
            }
    }
}