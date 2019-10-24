package company.surious.mediator_presentation.ui.components.activities.specializations

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import company.surious.mediator_domain.entities.utils.IdentifiableUtils
import company.surious.mediator_domain.entities.utils.setAll
import company.surious.mediator_presentation.databinding.ItemSpecializationBinding

class SelectableSpecializationsAdapter :
    RecyclerView.Adapter<SelectableSpecializationViewHolder>(), Filterable {

    var onSelectedListener: ((SelectableSpecialization) -> Unit)? = null
    var onUnSelectedListener: ((SelectableSpecialization) -> Unit)? = null
    private val specializations = ArrayList<SelectableSpecialization>()
    private val specializationsFiltered = ArrayList<SelectableSpecialization>()

    private val onClickListener: ((SelectableSpecialization) -> Unit) = { specialization ->
        if (specialization.selected) {
            specialization.selected = false
            onUnSelectedListener?.invoke(specialization)
        } else {
            specialization.selected = true
            onSelectedListener?.invoke(specialization)
        }
        notifyItemChanged(specializationsFiltered.indexOf(specialization))
    }

    fun unSelectSpecialization(id: Long) {
        IdentifiableUtils.getById(id, specializations)?.let {
            if (it.selected) {
                it.selected = false
                notifyItemChanged(specializationsFiltered.indexOf(it))
            }
        }
    }

    fun setAll(items: List<SelectableSpecialization>) {
        specializations.setAll(items)
        specializationsFiltered.setAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SelectableSpecializationViewHolder =
        SelectableSpecializationViewHolder(
            ItemSpecializationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount(): Int = specializationsFiltered.size

    override fun onBindViewHolder(holder: SelectableSpecializationViewHolder, position: Int) {
        holder.bind(specializationsFiltered[position], onClickListener)
    }

    override fun getFilter(): Filter = object : Filter() {

        override fun performFiltering(constraint: CharSequence?): FilterResults {
            if (constraint == null || constraint.isBlank()) {
                specializationsFiltered.setAll(specializations)
            } else {
                specializationsFiltered.setAll(
                    specializations.filter {
                        it.getFormattedName().contains(constraint.trim().toString(), true)
                    }
                )
            }
            return FilterResults().apply { values = specializationsFiltered }
        }

        @Suppress("UNCHECKED_CAST")
        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            results?.let {
                val values = it.values
                values as ArrayList<SelectableSpecialization>
                specializationsFiltered.setAll(values)
                notifyDataSetChanged()
            }
        }
    }

}