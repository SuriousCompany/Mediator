package company.surious.mediator_data

import android.content.Context
import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import company.surious.mediator_domain.managers.SignInManager

class FirebaseSignInManager(private val context: Context) :
    SignInManager {

    private val signInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .requestId()
        .requestProfile().build()

    private val client = GoogleSignIn.getClient(context, signInOptions)

    override fun getSignInIntent(): Intent = client.signInIntent

    override fun isSigned(): Boolean = GoogleSignIn.getLastSignedInAccount(context) != null
}