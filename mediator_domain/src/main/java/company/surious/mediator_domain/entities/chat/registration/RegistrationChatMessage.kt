package company.surious.mediator_domain.entities.chat.registration

import android.os.Parcel
import android.os.Parcelable
import company.surious.mediator_domain.entities.chat.attachment.Attachment
import company.surious.mediator_domain.entities.interfaces.UpdatableEntity
import company.surious.mediator_domain.entities.utils.EqualsUtils
import company.surious.mediator_domain.entities.utils.UpdatableEntityUtils
import company.surious.mediator_domain.entities.utils.updateInnerObject

class RegistrationChatMessage(
    override var id: Long = -1,
    var message: String = "",
    var sentTime: Long = -1,
    var senderId: Long = -1,
    var attachment: Attachment?
) : UpdatableEntity<RegistrationChatMessage> {

    override fun update(anotherVersion: RegistrationChatMessage) {
        UpdatableEntityUtils.checkSameEntity(this, anotherVersion.id)
        message = anotherVersion.message
        sentTime = anotherVersion.sentTime
        updateInnerObject(
            anotherVersion.attachment,
            attachment,
            { attachment = null },
            { attachment = anotherVersion.attachment }
        )
    }

    override fun isChanged(anotherVersion: RegistrationChatMessage): Boolean {
        UpdatableEntityUtils.checkSameEntity(this, anotherVersion.id)
        return message != anotherVersion.message
                || sentTime != anotherVersion.sentTime
                || senderId != anotherVersion.senderId
                || !EqualsUtils.nullEquals(attachment, anotherVersion.attachment)
                || attachment != null && attachment!!.isChanged(anotherVersion.attachment!!)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as RegistrationChatMessage
        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    constructor(source: Parcel) : this(
        source.readLong(),
        source.readString()!!,
        source.readLong(),
        source.readLong(),
        source.readParcelable<Attachment>(Attachment::class.java.classLoader)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeLong(id)
        writeString(message)
        writeLong(sentTime)
        writeLong(senderId)
        writeParcelable(attachment, 0)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<RegistrationChatMessage> =
            object : Parcelable.Creator<RegistrationChatMessage> {
                override fun createFromParcel(source: Parcel): RegistrationChatMessage =
                    RegistrationChatMessage(source)

                override fun newArray(size: Int): Array<RegistrationChatMessage?> =
                    arrayOfNulls(size)
            }
    }
}