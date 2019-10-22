package company.surious.mediator_presentation.ui.components.activities.main

import android.content.Intent
import android.os.Bundle
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
import company.surious.mediator_presentation.ui.components.activities.DoctorSignUpActivity
import company.surious.mediator_presentation.ui.components.activities.PatientSignUpActivity
import company.surious.mediator_presentation.ui.components.view_models.SignInButtonViewModel
import company.surious.mediator_presentation.ui.components.view_models.SpecializationsListViewModel
import company.surious.mediator_presentation.ui.utils.extensions.showNotImplementedToast
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {

    companion object {
        private const val SIGN_IN_REQUEST_CODE = 18
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var signInButtonViewModel: SignInButtonViewModel
    lateinit var specializationsListViewModel: SpecializationsListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModels()
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.eventsHandler = MainActivityViewEventsHandler()
    }

    private fun initViewModels() {
        signInButtonViewModel =
            ViewModelProviders.of(this, viewModelFactory)[SignInButtonViewModel::class.java]
        signInButtonViewModel.showSignInActivityFunction = { startSignInActivity(it) }
        signInButtonViewModel.signedInCallback = { showNotImplementedToast() }
        specializationsListViewModel = ViewModelProviders.of(this, viewModelFactory)[
                SpecializationsListViewModel::class.java]
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
            signInButtonViewModel.onSignedInWithGoogle(account!!)
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

    inner class MainActivityViewEventsHandler {
        fun onSignInButtonClicked() = signInButtonViewModel.onSignInButtonClick()


        fun onDoctorSignUpButtonClicked() =
            startActivity(Intent(this@MainActivity, DoctorSignUpActivity::class.java))

        fun onPatientSignUpButtonClicked() =
            startActivity(Intent(this@MainActivity, PatientSignUpActivity::class.java))

        fun onRegButtonClicked() = specializationsListViewModel.onRegistrationButtonClick()

    }
}
