package company.surious.mediator_presentation.ui.components.activities.specializations

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import company.surious.mediator_domain.entities.users.doctors.Specialization
import company.surious.mediator_domain.entities.utils.setAll
import company.surious.mediator_presentation.databinding.ItemSpecializationBinding

class SelectableSpecializationsAdapter :
    RecyclerView.Adapter<SelectableSpecializationViewHolder>() {

    var onSelectedListener: ((Specialization) -> Unit)? = null
    var onUnSelectedListener: ((Specialization) -> Unit)? = null
    private val specializations = ArrayList<SelectableSpecialization>()

    private val onClickListener: ((SelectableSpecialization) -> Unit) = { specialization ->
        if (specialization.selected) {
            specialization.selected = false
            onUnSelectedListener?.invoke(specialization.specialization)
        } else {
            specialization.selected = true
            onSelectedListener?.invoke(specialization.specialization)
        }
        notifyItemChanged(specializations.indexOf(specialization))
    }

    fun setAll(items: List<SelectableSpecialization>) {
        specializations.setAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SelectableSpecializationViewHolder =
        SelectableSpecializationViewHolder(
            ItemSpecializationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount(): Int = specializations.size

    override fun onBindViewHolder(holder: SelectableSpecializationViewHolder, position: Int) {
        holder.bind(specializations[holder.adapterPosition], onClickListener)
    }

}