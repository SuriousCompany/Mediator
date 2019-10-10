package company.surious.mediator_domain.entities.users.doctors

import android.os.Parcel
import android.os.Parcelable
import company.surious.mediator_domain.entities.interfaces.Nameable
import company.surious.mediator_domain.entities.interfaces.UpdatableEntity
import company.surious.mediator_domain.entities.location.Location
import company.surious.mediator_domain.entities.utils.UpdatableEntityUtils

class PatientsReceptionSite(
    override var id: Long = -1,
    val location: Location = Location(),
    override var name: String = "",
    override var nameRu: String? = null,
    override var nameUa: String? = null,
    var patientsReceptionSiteGooglePlacesId: String? = null
) : UpdatableEntity<PatientsReceptionSite>, Nameable, Parcelable {

    override fun update(anotherVersion: PatientsReceptionSite) {
        UpdatableEntityUtils.checkSameEntity(this, anotherVersion.id)
        location.update(anotherVersion.location)
        name = anotherVersion.name
        nameRu = anotherVersion.nameRu
        nameUa = anotherVersion.nameUa
        patientsReceptionSiteGooglePlacesId = anotherVersion.patientsReceptionSiteGooglePlacesId
    }

    override fun isChanged(anotherVersion: PatientsReceptionSite): Boolean {
        UpdatableEntityUtils.checkSameEntity(this, anotherVersion.id)
        return name != anotherVersion.name
                || nameRu != anotherVersion.nameRu
                || nameUa != anotherVersion.nameUa
                || patientsReceptionSiteGooglePlacesId != anotherVersion.patientsReceptionSiteGooglePlacesId
                || location.isChanged(anotherVersion.location)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as PatientsReceptionSite
        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    constructor(source: Parcel) : this(
        source.readLong(),
        source.readParcelable<Location>(Location::class.java.classLoader)!!,
        source.readString()!!,
        source.readString(),
        source.readString(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeLong(id)
        writeParcelable(location, 0)
        writeString(name)
        writeString(nameRu)
        writeString(nameUa)
        writeString(patientsReceptionSiteGooglePlacesId)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<PatientsReceptionSite> =
            object : Parcelable.Creator<PatientsReceptionSite> {
                override fun createFromParcel(source: Parcel): PatientsReceptionSite =
                    PatientsReceptionSite(source)

                override fun newArray(size: Int): Array<PatientsReceptionSite?> = arrayOfNulls(size)
            }
    }
}