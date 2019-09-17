package company.surious.mediator_presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import company.surious.mediator_device.Logger
import company.surious.mediator_presentation.ui.base.BridgeActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BridgeActivity() {
    companion object {
        private const val SIGN_IN_REQUEST_CODE = 18
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (bridge.signInManager.isSigned()) {
            Toast.makeText(this, "signed", Toast.LENGTH_SHORT).show()
            signInButton.isEnabled = false
        }
        signInButton.setOnClickListener { onSignInClick() }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SIGN_IN_REQUEST_CODE) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            Toast.makeText(this, "signed", Toast.LENGTH_SHORT).show()
        } catch (e: ApiException) {
            Logger.e("signIn", e)
        }

    }

    private fun onSignInClick() {
        startActivityForResult(bridge.signInManager.getSignInIntent(), SIGN_IN_REQUEST_CODE)
    }
}
