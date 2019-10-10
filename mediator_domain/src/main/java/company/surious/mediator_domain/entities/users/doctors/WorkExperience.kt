package company.surious.mediator_domain.entities.users.doctors

import android.os.Parcel
import android.os.Parcelable
import company.surious.mediator_domain.entities.interfaces.UpdatableEntity
import company.surious.mediator_domain.entities.utils.UpdatableEntityUtils

open class WorkExperience(
    override var id: Long = -1,
    open var startYear: Int = -1,
    open var startMonth: Int = -1,
    open var finishYear: Int? = null,
    open var finishMonth: Int? = null,
    open var placeOfWork: String = "",
    open var placeOfWorkRu: String? = null,
    open var placeOfWorkUa: String? = null,
    open var position: String = "",
    open var positionRu: String? = null,
    open var positionUa: String? = null
) : UpdatableEntity<WorkExperience>, Parcelable {

    override fun isChanged(anotherVersion: WorkExperience): Boolean {
        UpdatableEntityUtils.checkSameEntity(this, anotherVersion.id)
        return startYear != anotherVersion.startYear
                || startMonth != anotherVersion.startMonth
                || finishYear != anotherVersion.finishYear
                || finishMonth != anotherVersion.finishMonth
                || placeOfWork != anotherVersion.placeOfWork
                || placeOfWorkRu != anotherVersion.placeOfWorkRu
                || placeOfWorkUa != anotherVersion.placeOfWorkUa
                || position != anotherVersion.position
                || positionRu != anotherVersion.positionRu
                || positionUa != anotherVersion.positionUa
    }

    override fun update(anotherVersion: WorkExperience) {
        UpdatableEntityUtils.checkSameEntity(this, anotherVersion.id)
        startYear = anotherVersion.startYear
        startMonth = anotherVersion.startMonth
        finishYear = anotherVersion.finishYear
        finishMonth = anotherVersion.finishMonth
        placeOfWork = anotherVersion.placeOfWork
        placeOfWorkRu = anotherVersion.placeOfWorkRu
        placeOfWorkUa = anotherVersion.placeOfWorkUa
        position = anotherVersion.position
        positionRu = anotherVersion.positionRu
        positionUa = anotherVersion.positionUa
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as WorkExperience
        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    constructor(source: Parcel) : this(
        source.readLong(),
        source.readInt(),
        source.readInt(),
        source.readValue(Int::class.java.classLoader) as Int?,
        source.readValue(Int::class.java.classLoader) as Int?,
        source.readString()!!,
        source.readString(),
        source.readString(),
        source.readString()!!,
        source.readString(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeLong(id)
        writeInt(startYear)
        writeInt(startMonth)
        writeValue(finishYear)
        writeValue(finishMonth)
        writeString(placeOfWork)
        writeString(placeOfWorkRu)
        writeString(placeOfWorkUa)
        writeString(position)
        writeString(positionRu)
        writeString(positionUa)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<WorkExperience> =
            object : Parcelable.Creator<WorkExperience> {
                override fun createFromParcel(source: Parcel): WorkExperience =
                    WorkExperience(source)

                override fun newArray(size: Int): Array<WorkExperience?> = arrayOfNulls(size)
            }
    }
}