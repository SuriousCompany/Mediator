package company.surious.mediator_presentation.ui.components.activities.specializations

import androidx.recyclerview.widget.RecyclerView
import company.surious.mediator_presentation.databinding.ItemSpecializationBinding

class SelectableSpecializationViewHolder(private val binding: ItemSpecializationBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        selectableSpecialization: SelectableSpecialization,
        clickListener: ((SelectableSpecialization) -> Unit)?
    ) {
        binding.specialization = selectableSpecialization
        binding.root.setOnClickListener {
            clickListener?.invoke(selectableSpecialization)
        }
    }
}