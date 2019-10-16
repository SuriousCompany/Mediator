package company.surious.mediator_domain.managers

import company.surious.mediator_domain.entities.users.current_user.CurrentUser

interface PreferencesManager {
    var currentUser: CurrentUser?
}