package company.surious.mediator_domain.use_cases.specializations

import company.surious.mediator_domain.Language


class ObserveSpecializationsParams(
    var language: Language,
    var excludeParentModels: Boolean
)