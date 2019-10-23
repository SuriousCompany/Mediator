package company.surious.mediator_presentation.ui.components.dialogs


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import company.surious.mediator_presentation.R

class LoadingDialogFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_loading, container, false)

    override fun isCancelable(): Boolean = false
}
