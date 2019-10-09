package company.surious.mediator_domain.entities.chat.attachment

import company.surious.mediator_domain.entities.Identifiable

data class Attachment(
    override val id: Long,
    var url: String,
    var extension: String,
    var type: AttachmentType
) : Identifiable
