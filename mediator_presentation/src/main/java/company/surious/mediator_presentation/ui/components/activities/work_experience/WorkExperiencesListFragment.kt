package company.surious.mediator_presentation.ui.components.activities.work_experience

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import company.surious.mediator_presentation.R
import company.surious.mediator_presentation.databinding.FragmentWorkExperiencesListBinding

class WorkExperiencesListFragment : Fragment() {
    lateinit var binding: FragmentWorkExperiencesListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_work_experiences_list,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.eventsHandler = WorkExperiencesListEventsHandler()
    }

    inner class WorkExperiencesListEventsHandler {
        fun onAddExperienceButtonClicked() {
            val action =
                WorkExperiencesListFragmentDirections.actionWorkExperiencesListFragmentToEditWorkExperienceFragment()
            findNavController().navigate(action)
        }
    }
}
