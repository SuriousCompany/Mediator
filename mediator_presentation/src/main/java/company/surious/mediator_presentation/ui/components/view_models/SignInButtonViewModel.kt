package company.surious.mediator_presentation.ui.components.view_models

import android.content.Intent
import androidx.lifecycle.ViewModel
import company.surious.mediator_domain.managers.SignInManager
import javax.inject.Inject

class SignInButtonViewModel @Inject constructor(private val signInManager: SignInManager) :
    ViewModel() {

    var showSignInActivityFunction: ((signInIntent: Intent) -> Unit)? = null

    fun onSignInButtonClick() {
        showSignInActivityFunction?.invoke(signInManager.getSignInIntent())
    }
}