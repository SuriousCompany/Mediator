package company.surious.mediator_device

import android.content.Context
import android.content.Context.MODE_PRIVATE
import androidx.core.content.edit
import com.google.gson.Gson
import company.surious.mediator_domain.entities.users.current_user.CurrentUser
import company.surious.mediator_domain.managers.PreferencesManager
import javax.inject.Inject

class SharedPreferencesManager @Inject constructor(context: Context) : PreferencesManager {
    private companion object {
        private const val CURRENT_USER = "currentUser"
    }

    private val preferences = context.getSharedPreferences("prefs", MODE_PRIVATE)
    private val gson = Gson()

    override var currentUser: CurrentUser?
        get() =
            preferences.getString(CURRENT_USER, null)?.let {
                gson.fromJson(it, CurrentUser::class.java)
            }
        set(value) {
            preferences.edit {
                putString(CURRENT_USER, gson.toJson(value))
            }
        }
}