package company.surious.mediator_presentation.core

import company.surious.mediator_domain.SignInManager

interface AppBridge {
    var signInManager: SignInManager
}