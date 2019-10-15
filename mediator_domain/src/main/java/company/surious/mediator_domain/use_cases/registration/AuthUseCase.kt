package company.surious.mediator_domain.use_cases.registration

import company.surious.mediator_domain.entities.users.SignedUser
import company.surious.mediator_domain.entities.users.current_user.CurrentUser
import company.surious.mediator_domain.managers.AuthManager
import company.surious.mediator_domain.managers.UsersRepository
import company.surious.mediator_domain.use_cases.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class AuthUseCase @Inject constructor(
    private var authManager: AuthManager,
    private var usersRepository: UsersRepository
) :
    SingleUseCase<String, CurrentUser>() {

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun createSingle(idToken: String): Single<CurrentUser> =
        authManager.authWithGoogle(idToken).flatMap<SignedUser> {
            usersRepository.getSignedUser(it).toSingle(SignedUser(uId = it))
        }.flatMap<CurrentUser> {
            Single.just(CurrentUser(uId = it.uId))
            //TODO add doctor/patient data request
        }


}