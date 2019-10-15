package company.surious.mediator_data.repositories

import com.google.firebase.firestore.FirebaseFirestore
import company.surious.mediator_domain.entities.users.SignedUser
import company.surious.mediator_domain.managers.UsersRepository
import io.reactivex.Maybe
import javax.inject.Inject

class FirestoreUsersRepository @Inject constructor(firebaseFirestore: FirebaseFirestore) :
    UsersRepository {

    override fun getSignedUser(uId: String): Maybe<SignedUser> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}