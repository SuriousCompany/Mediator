package company.surious.mediator_domain.managers

import android.content.Intent

interface SignInManager {
    fun isSigned(): Boolean
    fun getSignInIntent(): Intent
}