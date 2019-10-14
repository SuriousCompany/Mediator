package company.surious.mediator_data

import com.google.firebase.firestore.FirebaseFirestore
import company.surious.mediator_data.indexes.RegistrationRequestIndex
import company.surious.mediator_data.indexes.RegistrationRequestType
import company.surious.mediator_domain.Logger
import company.surious.mediator_domain.entities.registration.DoctorRegistrationRequest
import company.surious.mediator_domain.entities.registration.HospitalRegistrationRequest
import company.surious.mediator_domain.entities.users.doctors.Specialization
import company.surious.mediator_domain.managers.RegistrationManager
import io.reactivex.Completable
import javax.inject.Inject

class FirestoreRegistrationManager @Inject constructor(private var firebaseFirestore: FirebaseFirestore) :
    RegistrationManager {

    override fun sendHospitalRegistrationRequest(request: HospitalRegistrationRequest): Completable =
        Completable.create { emitter ->
            Logger.logThread("request")
            val requestsReference = firebaseFirestore.collection(References.REGISTRATION_REQUESTS)
            val indexReference =
                requestsReference
                    .document(References.INDEXES)
                    .collection(References.INDEXES)
                    .document(request.id.toString())
            val hospitalsReference =
                requestsReference
                    .document(References.HOSPITALS)
                    .collection(References.HOSPITALS)
                    .document(request.id.toString())
            val index = RegistrationRequestIndex(
                request.id,
                request.userId,
                RegistrationRequestType.HOSPITAL
            )
            firebaseFirestore.runBatch { batch ->
                batch.set(
                    hospitalsReference, request
                )
                batch.set(indexReference, index)
            }.addOnCompleteListener {
                if (!emitter.isDisposed) {
                    emitter.onComplete()
                }
            }.addOnFailureListener {
                if (!emitter.isDisposed) {
                    emitter.onError(it)
                }
            }

        }


    override fun sendDoctorRegistrationRequest(request: DoctorRegistrationRequest): Completable =
        Completable.create { emitter ->
            val requestsReference = firebaseFirestore.collection(References.REGISTRATION_REQUESTS)
            val indexReference =
                requestsReference
                    .document(References.INDEXES)
                    .collection(References.INDEXES)
                    .document(request.id.toString())
            val doctorsReference =
                requestsReference
                    .document(References.DOCTORS)
                    .collection(References.DOCTORS)
                    .document(request.id.toString())
            val index = RegistrationRequestIndex(
                request.id,
                request.userId,
                RegistrationRequestType.DOCTOR
            )
            firebaseFirestore.runBatch { batch ->
                batch.set(
                    doctorsReference, request
                )
                batch.set(indexReference, index)
            }.addOnCompleteListener {
                if (!emitter.isDisposed) {
                    emitter.onComplete()
                }
            }.addOnFailureListener {
                if (!emitter.isDisposed) {
                    emitter.onError(it)
                }
            }
        }

    override fun registerSpecification(specialization: Specialization): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}