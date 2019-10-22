package company.surious.mediator_data.repositories

import com.google.firebase.firestore.FirebaseFirestore
import company.surious.mediator_domain.entities.users.SignedUser
import company.surious.mediator_domain.repositories.UsersRepository
import io.reactivex.Maybe
import javax.inject.Inject

class FirestoreUsersRepository @Inject constructor(firebaseFirestore: FirebaseFirestore) :
    UsersRepository {

    override fun getSignedUser(uId: String): Maybe<SignedUser> =
        Maybe.empty()

}