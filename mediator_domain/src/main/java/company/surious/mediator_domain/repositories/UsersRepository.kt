package company.surious.mediator_domain.repositories

import company.surious.mediator_domain.entities.users.SignedUser
import io.reactivex.Maybe

interface UsersRepository {
    fun getSignedUser(uId: String): Maybe<SignedUser>
}