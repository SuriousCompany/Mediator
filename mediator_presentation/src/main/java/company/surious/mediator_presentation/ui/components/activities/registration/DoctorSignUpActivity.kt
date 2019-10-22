package company.surious.mediator_presentation.ui.components.activities.registration

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import company.surious.mediator_presentation.R
import company.surious.mediator_presentation.databinding.ActivityDoctorSignUpBinding
import company.surious.mediator_presentation.ui.components.activities.registration.hospital_doctor.HospitalDoctorSignUpActivity
import company.surious.mediator_presentation.ui.utils.extensions.showNotImplementedToast

class DoctorSignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityDoctorSignUpBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_doctor_sign_up)
        binding.lifecycleOwner = this
        binding.eventsHandler = DoctorSignUpActivityEventsHandler()
    }

    inner class DoctorSignUpActivityEventsHandler {
        fun onSelfEmployedButtonClicked() {
            showNotImplementedToast()
        }

        fun onHospitalButtonClicked() =
            startActivity(
                Intent(
                    this@DoctorSignUpActivity,
                    HospitalDoctorSignUpActivity::class.java
                )
            )
    }
}
