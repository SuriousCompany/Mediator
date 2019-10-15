package company.surious.mediator_domain.entities.users.current_user

import android.os.Parcel
import android.os.Parcelable
import company.surious.mediator_domain.entities.interfaces.UpdatableEntity
import company.surious.mediator_domain.entities.users.doctors.Doctor
import company.surious.mediator_domain.entities.utils.isInnerObjectChanged
import company.surious.mediator_domain.entities.utils.updateInnerObject

class CurrentUser(
    override var id: Long = -1,
    var currentUserType: CurrentUserType = CurrentUserType.UNDEFINED,
    var currentUserStatus: CurrentUserStatus = CurrentUserStatus.SIGNED_WITH_GOOGLE,
    var doctorData: Doctor? = null,
    var uId: String = ""
    //TODO add patient model
) : UpdatableEntity<CurrentUser>, Parcelable {

    override fun isChanged(anotherVersion: CurrentUser): Boolean =
        currentUserType != anotherVersion.currentUserType
                || currentUserStatus != anotherVersion.currentUserStatus
                || isInnerObjectChanged(anotherVersion.doctorData, doctorData)
                || uId != anotherVersion.uId

    override fun update(anotherVersion: CurrentUser) {
        currentUserType = anotherVersion.currentUserType
        currentUserStatus = anotherVersion.currentUserStatus
        updateInnerObject(
            anotherVersion.doctorData,
            doctorData,
            { doctorData = null },
            { doctorData = anotherVersion.doctorData })
        uId = anotherVersion.uId
    }

    constructor(source: Parcel) : this(
        source.readLong(),
        CurrentUserType.values()[source.readInt()],
        CurrentUserStatus.values()[source.readInt()],
        source.readParcelable<Doctor>(Doctor::class.java.classLoader),
        source.readString()!!
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeLong(id)
        writeInt(currentUserType.ordinal)
        writeInt(currentUserStatus.ordinal)
        writeParcelable(doctorData, 0)
        writeString(uId)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<CurrentUser> = object : Parcelable.Creator<CurrentUser> {
            override fun createFromParcel(source: Parcel): CurrentUser = CurrentUser(source)
            override fun newArray(size: Int): Array<CurrentUser?> = arrayOfNulls(size)
        }
    }
}