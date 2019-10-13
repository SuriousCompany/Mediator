package company.surious.mediator_presentation.ui.components.view_models

import android.content.Intent
import androidx.lifecycle.ViewModel
import company.surious.mediator_domain.entities.location.Location
import company.surious.mediator_domain.entities.users.doctors.Hospital
import company.surious.mediator_domain.managers.RegistrationManager
import company.surious.mediator_domain.managers.SignInManager
import javax.inject.Inject

class SignInButtonViewModel @Inject constructor(
    private val signInManager: SignInManager,
    private val registrationManager: RegistrationManager
) : ViewModel() {

    var showSignInActivityFunction: ((signInIntent: Intent) -> Unit)? = null
    var showHospitalRegisteredToastFunction: ((hospital: Hospital) -> Unit)? = null
    var showErrorMessagesFunction: ((error: Throwable) -> Unit)? = null

    fun onSignInButtonClick() {
        if (signInManager.isSigned()) {
            registerHospital()
        } else {
            showSignInActivityFunction?.invoke(signInManager.getSignInIntent())
        }
    }

    private fun registerHospital() {
        val hospital = generateHospital()
        val disposable = registrationManager.registerHospital(hospital).subscribe(
            { showHospitalRegisteredToastFunction?.invoke(hospital) },
            { showErrorMessagesFunction?.invoke(it) }
        )
    }

    private fun generateHospital() = Hospital(
        System.currentTimeMillis(),
        1,
        "",
        Location(System.currentTimeMillis(), 123f, 321f),
        "City Children's Clinic №7",
        "Городская детская поликлиника №7",
        "Міська дитяча поліклініка №7",
        "The municipal non-profit enterprise \"City Children's Clinic №7\" of Kharkiv City Council was founded in 1974. Number of employees - 176 people. The number of doctors is 58, of them district pediatricians - 24. Nurses - 85, and their district pediatricians - 24. The polyclinic provides medical assistance to the infant population of the Moscow and Nemishliansky districts in the number of 19443 children.",
        "Коммунальное некоммерческое предприятие \"Городская детская поликлиника №7\" Харьковского городского совета основано в 1974 году. Количество спивробитникив - 176 человек. Количество врачей - 58, из них дильничних педиатров - 24. Медицинский сестер - 85 и них дильничних - 24. Поликлиника оказывает медицинская помощь детскому населению Московского и Немишлянського районов в количестве 19443 ребенка.",
        "Комунальне некомерційне підприємство \"Міська дитяча поліклініка №7\" Харківської міської ради засновано в 1974 году. Кількість співробітніків - 176 чоловік. Кількість лікарів - 58, з них дільнічніх педіатрів - 24. Медичний сестер - 85, і них дільнічніх - 24. Поліклініка надає медична допомога дитячому населенню Московского та Немішлянського районів в кількості 19443 дитини."
    )
}