package company.surious.mediator_presentation.ui.components.view_models

import android.content.Intent
import androidx.lifecycle.ViewModel
import company.surious.mediator_domain.Logger
import company.surious.mediator_domain.entities.location.Location
import company.surious.mediator_domain.entities.registration.DoctorRegistrationRequest
import company.surious.mediator_domain.entities.registration.HospitalRegistrationRequest
import company.surious.mediator_domain.entities.registration.RegistrationRequestStatus
import company.surious.mediator_domain.entities.users.doctors.Doctor
import company.surious.mediator_domain.entities.users.doctors.DoctorType
import company.surious.mediator_domain.entities.users.doctors.Hospital
import company.surious.mediator_domain.entities.users.doctors.Specialization
import company.surious.mediator_domain.managers.SignInManager
import company.surious.mediator_domain.use_cases.registration.SendHospitalRegistrationRequestUseCase
import javax.inject.Inject

class SignInButtonViewModel @Inject constructor(
    private val signInManager: SignInManager,
    private val sendHospitalRegistrationRequestUseCase: SendHospitalRegistrationRequestUseCase
) : ViewModel() {

    var signedInCallback: (() -> Unit)? = null
    var showSignInActivityFunction: ((intent: Intent) -> Unit)? = null
    var showErrorMessagesFunction: ((error: Throwable) -> Unit)? = null

    init {
        if (signInManager.isSigned()) {
            signedInCallback?.invoke()
        }
    }

    fun onSignInButtonClick() {
        showSignInActivityFunction?.invoke(signInManager.getSignInIntent())
    }

    fun onSignedInWithGoogle() {
        //TODO make server request
    }

    private fun sendRegistrationRequest() {
        val doctor = generateDoctor()
        val hospitalRegistrationRequest =
            HospitalRegistrationRequest(
                System.currentTimeMillis(),
                RegistrationRequestStatus.CREATED,
                doctor.id,
                DoctorRegistrationRequest(
                    System.currentTimeMillis(),
                    RegistrationRequestStatus.CREATED,
                    doctor.id,
                    doctor,
                    "nophoto",
                    "nopassport"
                ),
                doctor.hospital!!,
                "+3802281488"
            )
        val disposable = sendHospitalRegistrationRequestUseCase.execute(
            hospitalRegistrationRequest
        ).subscribe(
            {
                //showHospitalRegisteredToastFunction?.invoke(hospitalRegistrationRequest)
                Logger.logThread("view model")
            },
            {
                showErrorMessagesFunction?.invoke(it)
            }
        )
    }

    private fun generateDoctor() = Doctor(
        System.currentTimeMillis(),
        "authId",
        "Olya",
        "olya@popa.com",
        "Tkachenko",
        "Olga",
        "popkaPhoto.com",
        false,
        "Best category",
        generateSpecialization(),
        "No education at all",
        ArrayList(),
        DoctorType.HOSPITAL,
        5f,
        generateHospital(),
        null,
        "Олёнок",
        "Олха",
        "Ткаченко",
        "Ткаченко",
        "Ольга",
        "Олга",
        "Та какое там образование)",
        "Та яка там освiта)",
        "I'm Olya and I'm a little butt",
        "Я Оля и я - маленькая попка",
        "Я Оля i я - маленька дупка",
        8,
        "Sichevalnya",
        "Сычевальня",
        "Сичевальня",
        "Under the table",
        "Под столиком",
        "Пiд столом",
        "Scientific cock sucking",
        "Научное сосание бибы",
        "Наукове смоктання качана"
    )

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

    private fun generateSpecialization() =
        Specialization(
            System.currentTimeMillis(),
            "Ass doctor",
            "Проктолог",
            "Проктолог",
            "Ass diver",
            "Жопный врач",
            "Дупний лiкар"
        )
}