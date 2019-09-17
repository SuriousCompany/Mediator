package company.surious.mediator_domain

import android.content.Intent

interface SignInManager {
    fun isSigned(): Boolean
    fun getSignInIntent(): Intent
}