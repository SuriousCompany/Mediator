package company.surious.mediator_data.di

import android.content.Context
import com.google.firebase.firestore.FirebaseFirestore
import company.surious.mediator_data.FirebaseSignInManager
import company.surious.mediator_data.FirestoreRegistrationManager
import company.surious.mediator_domain.managers.RegistrationManager
import company.surious.mediator_domain.managers.SignInManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DataManagersModule {
    @Provides
    @Singleton
    @JvmStatic
    fun provideSignInManager(context: Context): SignInManager = FirebaseSignInManager(context)

    @Provides
    @Singleton
    @JvmStatic
    fun provideFirestoreInstance(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    @JvmStatic
    fun provideRegistrationManager(firebaseFirestore: FirebaseFirestore): RegistrationManager =
        FirestoreRegistrationManager(firebaseFirestore)
}