package company.surious.mediator_presentation.ui.components.activities.specializations

import company.surious.mediator_domain.entities.users.doctors.Specialization

fun SelectableSpecialization.Companion.fromSpecialization(
    specializationNetworkModel: Specialization
): SelectableSpecialization =
    SelectableSpecialization().apply {
        id = specializationNetworkModel.id
        name = specializationNetworkModel.name
        nameRu = specializationNetworkModel.nameRu
        nameUa = specializationNetworkModel.nameUa
        parentSpecialization = specializationNetworkModel.parentSpecialization?.let {
            fromSpecialization(it)
        }
        description = specializationNetworkModel.description
        descriptionRu = specializationNetworkModel.descriptionRu
        descriptionUa = specializationNetworkModel.descriptionUa
    }