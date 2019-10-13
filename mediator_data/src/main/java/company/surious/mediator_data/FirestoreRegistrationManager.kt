package company.surious.mediator_data

import com.google.firebase.firestore.FirebaseFirestore
import company.surious.mediator_domain.entities.registration.DoctorRegistrationRequest
import company.surious.mediator_domain.entities.users.doctors.Hospital
import company.surious.mediator_domain.entities.users.doctors.Specialization
import company.surious.mediator_domain.managers.RegistrationManager
import io.reactivex.Completable
import javax.inject.Inject

class FirestoreRegistrationManager @Inject constructor(private var firebaseFirestore: FirebaseFirestore) :
    RegistrationManager {
    override fun sendDoctorRegistrationRequest(request: DoctorRegistrationRequest): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun registerHospital(hospital: Hospital): Completable {
        return Completable.create { emitter ->
            firebaseFirestore.runTransaction { transaction ->
                transaction.set(
                    firebaseFirestore.collection(References.HOSPITALS).document(hospital.cityId.toString()),
                    hospital
                )
            }.addOnSuccessListener {
                if (!emitter.isDisposed) {
                    emitter.onComplete()
                }
            }.addOnFailureListener {
                if (!emitter.isDisposed) {
                    emitter.onError(it)
                }
            }
        }
    }

    override fun registerSpecification(specialization: Specialization): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}