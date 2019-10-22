package company.surious.mediator_presentation.ui.components.activities.specializations

import company.surious.mediator_domain.entities.users.doctors.Specialization

class SelectableSpecialization(
    var specialization: Specialization,
    var selected: Boolean = false
)