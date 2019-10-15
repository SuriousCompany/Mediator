package company.surious.mediator_presentation.ui.utils.extensions

import android.content.Context
import android.widget.Toast
import company.surious.mediator_presentation.R

fun Context.showNotImplementedToast() {
    Toast.makeText(this, getString(R.string.not_implemented_yet), Toast.LENGTH_SHORT).show()
}