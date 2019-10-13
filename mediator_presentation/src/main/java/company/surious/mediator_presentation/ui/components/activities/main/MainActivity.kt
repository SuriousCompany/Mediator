package company.surious.mediator_presentation.ui.components.activities.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import company.surious.mediator_device.Logger
import company.surious.mediator_presentation.R
import company.surious.mediator_presentation.databinding.ActivityMainBinding
import company.surious.mediator_presentation.ui.base.ViewModelFactory
import company.surious.mediator_presentation.ui.components.view_models.SignInButtonViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {

    companion object {
        private const val SIGN_IN_REQUEST_CODE = 18
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var signInButtonViewModel: SignInButtonViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        val viewModel =
            ViewModelProviders.of(this, viewModelFactory)[SignInButtonViewModel::class.java]
        viewModel.showSignInActivityFunction = {
            startSignInActivity(it)
        }
        binding.signInButtonViewModel = viewModel
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
            Toast.makeText(this, "signed as ${account!!.displayName}", Toast.LENGTH_SHORT).show()

        } catch (e: ApiException) {
            Logger.e("signIn", e)
        }

    }

    private fun startSignInActivity(signInActivityIntent: Intent) {
        startActivityForResult(
            signInActivityIntent,
            SIGN_IN_REQUEST_CODE
        )
    }
}
