package company.surious.mediator_data.di

import android.content.Context
import com.google.firebase.firestore.FirebaseFirestore
import company.surious.mediator_data.FirebaseAuthManager
import company.surious.mediator_data.FirestoreRegistrationManager
import company.surious.mediator_data.repositories.FirestoreUsersRepository
import company.surious.mediator_domain.managers.AuthManager
import company.surious.mediator_domain.managers.RegistrationManager
import company.surious.mediator_domain.managers.UsersRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DataManagersModule {
    @Provides
    @Singleton
    @JvmStatic
    fun provideSignInManager(context: Context): AuthManager = FirebaseAuthManager(context)

    @Provides
    @Singleton
    @JvmStatic
    fun provideFirestoreInstance(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    @JvmStatic
    fun provideRegistrationManager(firebaseFirestore: FirebaseFirestore): RegistrationManager =
        FirestoreRegistrationManager(firebaseFirestore)

    @Provides
    @Singleton
    @JvmStatic
    fun provideUsersRepository(firebaseFirestore: FirebaseFirestore): UsersRepository =
        FirestoreUsersRepository(firebaseFirestore)
}