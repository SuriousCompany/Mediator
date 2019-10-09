package company.surious.mediator_domain.entities.chat.registration

import company.surious.mediator_domain.entities.Identifiable
import company.surious.mediator_domain.entities.chat.attachment.Attachment

class RegistrationChatMessage(
    override val id: Long,
    var message: String,
    var sentTime: Long,
    val senderId: Long,
    var attachment: Attachment?
) : Identifiable