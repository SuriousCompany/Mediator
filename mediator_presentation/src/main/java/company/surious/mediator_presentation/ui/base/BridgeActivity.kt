package company.surious.mediator_presentation.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import company.surious.mediator_presentation.core.AppBridge

abstract class BridgeActivity : AppCompatActivity() {
    protected lateinit var bridge: AppBridge

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bridge = application as AppBridge
    }
}