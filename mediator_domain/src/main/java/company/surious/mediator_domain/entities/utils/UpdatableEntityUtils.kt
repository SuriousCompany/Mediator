package company.surious.mediator_domain.entities.utils

import company.surious.mediator_domain.entities.interfaces.UpdatableEntity

object UpdatableEntityUtils {
    fun checkSameEntity(entity: UpdatableEntity<*>, anotherEntityId: Long) {
        require(entity.id == anotherEntityId) {
            ErrorTextUtils.notTheSameEntityText(
                entity.javaClass,
                entity.id,
                anotherEntityId
            )
        }
    }

    fun <T : UpdatableEntity<T>> isListContentChanged(
        currentList: List<T>,
        anotherVersionList: List<T>
    ): Boolean {
        if (currentList.size == anotherVersionList.size) {
            currentList.forEachIndexed { index, item ->
                val anotherItem = anotherVersionList[index]
                if (item.id != anotherItem.id || item.isChanged(anotherItem)) {
                    return true
                }
            }
            return false
        } else {
            return false
        }
    }
}