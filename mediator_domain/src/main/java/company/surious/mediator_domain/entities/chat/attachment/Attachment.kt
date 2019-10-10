package company.surious.mediator_domain.entities.chat.attachment

import android.os.Parcel
import android.os.Parcelable
import company.surious.mediator_domain.entities.interfaces.UpdatableEntity
import company.surious.mediator_domain.entities.utils.UpdatableEntityUtils

class Attachment(
    override var id: Long = -1,
    var url: String = "",
    var extension: String = "",
    var type: AttachmentType = AttachmentType.IMAGE
) : UpdatableEntity<Attachment> {

    override fun update(anotherVersion: Attachment) {
        UpdatableEntityUtils.checkSameEntity(this, anotherVersion.id)
        url = anotherVersion.url
        extension = anotherVersion.extension
        type = anotherVersion.type
    }

    override fun isChanged(anotherVersion: Attachment): Boolean {
        UpdatableEntityUtils.checkSameEntity(this, anotherVersion.id)
        return anotherVersion.url != url
                || anotherVersion.extension == extension
                || anotherVersion.type == type
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Attachment
        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    constructor(source: Parcel) : this(
        source.readLong(),
        source.readString()!!,
        source.readString()!!,
        AttachmentType.values()[source.readInt()]
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeLong(id)
        writeString(url)
        writeString(extension)
        writeInt(type.ordinal)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Attachment> = object : Parcelable.Creator<Attachment> {
            override fun createFromParcel(source: Parcel): Attachment = Attachment(source)
            override fun newArray(size: Int): Array<Attachment?> = arrayOfNulls(size)
        }
    }
}
