package company.surious.mediator_data.repositories.specializations

import com.google.firebase.firestore.FirebaseFirestore
import company.surious.mediator_data.References
import company.surious.mediator_domain.Language
import company.surious.mediator_domain.entities.users.doctors.Specialization
import company.surious.mediator_domain.repositories.SpecializationsRepository
import io.reactivex.Observable
import javax.inject.Inject

class FirestoreSpecializationsRepository @Inject constructor(private val firebaseFirestore: FirebaseFirestore) :
    SpecializationsRepository {

    override fun observeSpecializations(
        language: Language,
        excludeParentModels: Boolean
    ): Observable<List<Specialization>> =
        Observable.create { emitter ->
            val specializationsReference =
                firebaseFirestore.collection(References.SPECIALIZATIONS)
                    .orderBy(getNameFieldByLanguage(language))
            specializationsReference.addSnapshotListener { snapshot, exception ->
                if (exception == null) {
                    if (snapshot != null) {
                        val networkModels =
                            snapshot.documents.map { it.toObject(SpecializationNetworkModel::class.java)!! }
                        val specializations =
                            SpecializationNetworkModel.mapToSpecializations(
                                networkModels,
                                excludeParentModels
                            )
                        if (!emitter.isDisposed) {
                            emitter.onNext(specializations)
                        }
                    }
                } else {
                    if (!emitter.isDisposed) {
                        emitter.onError(exception)
                    }
                }
            }
        }

    //todo move it to the device module
    private fun getNameFieldByLanguage(language: Language): String =
        when (language) {
            Language.EN -> "name"
            Language.RU -> "nameRu"
            Language.UA -> "nameUa"
        }
}