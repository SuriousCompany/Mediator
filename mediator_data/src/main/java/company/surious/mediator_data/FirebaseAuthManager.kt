package company.surious.mediator_data

import android.content.Context
import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import company.surious.mediator_domain.managers.AuthManager
import io.reactivex.Single


class FirebaseAuthManager(private val context: Context) : AuthManager {

    private val firebaseAuth = FirebaseAuth.getInstance()

    override fun getSignInIntent(): Intent =
        GoogleSignIn.getClient(
            context,
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestId()
                .requestIdToken("1033855855411-fca20ah46n1ldvlc76h4hkrc5sogs6v8.apps.googleusercontent.com")
                .requestProfile().build()
        ).signInIntent

    override fun isSigned(): Boolean = firebaseAuth.currentUser != null

    override fun authWithGoogle(userId: String): Single<String> =
        Single.create { emitter ->
            val credential = GoogleAuthProvider.getCredential(userId, null)
            firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = firebaseAuth.currentUser
                        if (!emitter.isDisposed) {
                            emitter.onSuccess(user!!.uid)
                        }
                    } else {
                        if (!emitter.isDisposed) {
                            emitter.onError(task.exception!!)
                        }
                    }
                }
                .addOnFailureListener {
                    if (!emitter.isDisposed) {
                        emitter.onError(it)
                    }
                }
        }
}