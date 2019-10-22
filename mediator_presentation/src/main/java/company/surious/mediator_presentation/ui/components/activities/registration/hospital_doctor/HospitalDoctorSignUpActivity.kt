package company.surious.mediator_presentation.ui.components.activities.registration.hospital_doctor

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import company.surious.mediator_domain.Language
import company.surious.mediator_domain.entities.interfaces.Nameable
import company.surious.mediator_domain.entities.users.doctors.Specialization
import company.surious.mediator_presentation.R
import company.surious.mediator_presentation.databinding.ActivityHospitalDoctorSignUpBinding
import company.surious.mediator_presentation.ui.components.activities.specializations.SelectSpecializationsActivity

class HospitalDoctorSignUpActivity : AppCompatActivity() {
    private companion object {
        private const val SELECT_SPECIALIZATIONS_CODE = 18
    }

    private val selectedSpecializationsText = ObservableField<String>("none")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityHospitalDoctorSignUpBinding>(
            this,
            R.layout.activity_hospital_doctor_sign_up
        )
        binding.lifecycleOwner = this
        binding.selectedSpecializationsText = selectedSpecializationsText
        binding.eventsHandler = HospitalDoctorSignUpActivityEventsHandler()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SELECT_SPECIALIZATIONS_CODE && resultCode == Activity.RESULT_OK) {
            data?.let {
                val selectedSpecializations: List<Specialization> =
                    it.getParcelableArrayListExtra(SelectSpecializationsActivity.SELECTED_SPECIALIZATIONS_KEY)
                selectedSpecializationsText.set(getSpecializationsText(selectedSpecializations))
            }
        }
    }

    private fun getSpecializationsText(specializations: List<Specialization>): String {
        val builder = StringBuilder()
        specializations.forEachIndexed { index, specialization ->
            builder.append(getNameInLanguage(specialization, Language.RU))
            builder.append(
                if (index != specializations.size - 1) {
                    ", "
                } else {
                    "."
                }
            )
        }
        return builder.toString()
    }

    private fun getNameInLanguage(nameable: Nameable, language: Language): String? =
        when (language) {
            Language.EN -> nameable.name
            Language.RU -> nameable.nameRu
            Language.UA -> nameable.nameUa
        }


    inner class HospitalDoctorSignUpActivityEventsHandler {
        fun onSelectSpecializationsButtonClicked() =
            startActivityForResult(
                Intent(
                    this@HospitalDoctorSignUpActivity,
                    SelectSpecializationsActivity::class.java
                ), SELECT_SPECIALIZATIONS_CODE
            )
    }

}
