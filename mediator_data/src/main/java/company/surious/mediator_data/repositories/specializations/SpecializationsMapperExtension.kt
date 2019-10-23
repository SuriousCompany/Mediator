package company.surious.mediator_data.repositories.specializations

import company.surious.mediator_domain.entities.users.doctors.Specialization
import company.surious.mediator_domain.entities.utils.IdentifiableUtils

fun SpecializationNetworkModel.mapToSpecialization(): Specialization =
    Specialization(
        id, name, nameRu, nameUa, null, description, descriptionRu, descriptionUa
    )

fun SpecializationNetworkModel.Companion.mapToSpecializations(
    networkModels: List<SpecializationNetworkModel>, excludeParentModels: Boolean
): List<Specialization> {
    val parentSpecializationIds = HashSet<Long>()
    networkModels.forEach {
        if (it.parentSpecializationId != -1L) {
            parentSpecializationIds.add(it.parentSpecializationId)
        }
    }
    val parentNetworkModels =
        networkModels.filter { parentSpecializationIds.contains(it.id) }
    val parentSpecializations = ArrayList<Specialization>()
    val mappedSpecializations = ArrayList<Specialization>(
        networkModels.map { networkModel ->
            val res = networkModel.mapToSpecialization()
            if (networkModel.parentSpecializationId != -1L) {
                val parent =
                    IdentifiableUtils.getById(
                        networkModel.parentSpecializationId,
                        parentNetworkModels
                    )?.mapToSpecialization()
                res.parentSpecialization = parent
                parent?.let { parentSpecializations.add(it) }
            }
            res
        }
    )
    if (excludeParentModels) {
        mappedSpecializations.removeAll(parentSpecializations)
    }
    return mappedSpecializations
}