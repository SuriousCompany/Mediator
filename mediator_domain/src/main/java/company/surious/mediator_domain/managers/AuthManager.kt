package company.surious.mediator_domain.managers

import android.content.Intent
import io.reactivex.Single

interface AuthManager {
    fun isSigned(): Boolean
    fun getSignInIntent(): Intent
    fun authWithGoogle(userId: String): Single<String>
}