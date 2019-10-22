package company.surious.mediator_presentation.ui.components.activities.registration.hospital_doctor

import company.surious.mediator_presentation.di.ViewModelModule
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = [ViewModelModule::class])
interface HospitalDoctorSignUpActivityComponent : AndroidInjector<HospitalDoctorSignUpActivity> {
    @Subcomponent.Factory
    interface Builder : AndroidInjector.Factory<HospitalDoctorSignUpActivity>
}