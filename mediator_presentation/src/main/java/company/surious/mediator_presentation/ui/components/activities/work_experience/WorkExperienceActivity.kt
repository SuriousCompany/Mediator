package company.surious.mediator_presentation.ui.components.activities.work_experience

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import company.surious.mediator_presentation.R
import kotlinx.android.synthetic.main.activity_work_experience.*

class WorkExperienceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_experience)
    }

    fun onClick() {
        workExperienceRoot.findNavController()
            .navigate(R.id.action_workExperiencesListFragment_to_editWorkExperienceFragment)
    }
}
