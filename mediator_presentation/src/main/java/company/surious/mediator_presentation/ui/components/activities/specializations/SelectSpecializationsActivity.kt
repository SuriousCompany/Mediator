package company.surious.mediator_presentation.ui.components.activities.specializations

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import company.surious.mediator_domain.entities.users.doctors.Specialization
import company.surious.mediator_presentation.R
import company.surious.mediator_presentation.databinding.ActivitySelectSpecializationsBinding
import company.surious.mediator_presentation.ui.base.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class SelectSpecializationsActivity : DaggerAppCompatActivity() {
    companion object {
        const val SELECTED_SPECIALIZATIONS_KEY = "selectedSpecializations"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val selectedSpecializations = ArrayList<Specialization>()
    private lateinit var specializationsAdapter: SelectableSpecializationsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivitySelectSpecializationsBinding>(
            this,
            R.layout.activity_select_specializations
        )
        initAdapter()
        binding.lifecycleOwner = this
        binding.specializationsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.specializationsRecyclerView.adapter = specializationsAdapter
        binding.eventsHandler = SelectSpecializationsActivityEventsHandler()
        val viewModel =
            ViewModelProviders.of(this, viewModelFactory)[SpecializationsListViewModel::class.java]
        viewModel.updateSpecializationsFunction = ::updateSpecializations
    }

    private fun initAdapter() {
        specializationsAdapter = SelectableSpecializationsAdapter()
        specializationsAdapter.onSelectedListener = ::onSpecializationSelected
        specializationsAdapter.onUnSelectedListener = ::onSpecializationUnSelected
    }

    private fun onSpecializationSelected(specialization: Specialization) {
        selectedSpecializations.add(specialization)
    }

    private fun onSpecializationUnSelected(specialization: Specialization) {
        selectedSpecializations.remove(specialization)
    }

    private fun updateSpecializations(specializations: List<Specialization>) {
        selectedSpecializations.clear()
        specializationsAdapter.setAll(specializations.map { SelectableSpecialization(it) })
    }

    inner class SelectSpecializationsActivityEventsHandler {
        fun onSelectedFabClicked() {
            setResult(
                Activity.RESULT_OK,
                Intent().apply {
                    putParcelableArrayListExtra(
                        SELECTED_SPECIALIZATIONS_KEY,
                        selectedSpecializations
                    )
                })
            finish()
        }
    }
}
