package company.surious.mediator_presentation.ui.utils.extensions

import androidx.fragment.app.FragmentManager
import company.surious.mediator_presentation.ui.components.dialogs.LoadingDialogFragment

class DialogUtils(private val fragmentManager: FragmentManager) {
    private var loadingDialogFragment: LoadingDialogFragment? = null

    fun showLoadingDialog() {
        if (loadingDialogFragment == null) {
            loadingDialogFragment = LoadingDialogFragment()
        }
        loadingDialogFragment!!.apply { isCancelable = false }.show(fragmentManager, null)
    }

    fun hideLoadingDialog() {
        loadingDialogFragment?.dismiss()
    }
}