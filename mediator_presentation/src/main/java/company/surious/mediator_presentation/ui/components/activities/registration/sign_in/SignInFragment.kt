package company.surious.mediator_presentation.ui.components.activities.registration.sign_in


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import company.surious.mediator_domain.entities.users.current_user.CurrentUser
import company.surious.mediator_domain.entities.users.current_user.CurrentUserStatus
import company.surious.mediator_domain.managers.PreferencesManager
import company.surious.mediator_presentation.R
import company.surious.mediator_presentation.databinding.FragmentSignInBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SignInFragment : DaggerFragment() {
    lateinit var binding: FragmentSignInBinding
    @Inject
    lateinit var preferencesManager: PreferencesManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_sign_in, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preferencesManager.currentUser?.let {
            if (it.currentUserStatus == CurrentUserStatus.SIGNED_WITH_GOOGLE) {
                findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToRegistrationFragment())
            }
        } ?: run {
            binding.lifecycleOwner = this
            binding.eventHandler = SignInFragmentEventHandler()
        }

    }


    inner class SignInFragmentEventHandler {
        fun onSignInButtonClicked() {
            preferencesManager.currentUser =
                CurrentUser(currentUserStatus = CurrentUserStatus.SIGNED_WITH_GOOGLE)
        }
    }
}
