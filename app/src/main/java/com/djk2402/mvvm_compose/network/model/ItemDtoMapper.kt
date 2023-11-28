package com.djk2402.mvvm_compose.network.model

import com.djk2402.mvvm_compose.domain.model.Item
import com.djk2402.mvvm_compose.domain.util.DomainMapper

class RecipeDtoMapper : DomainMapper<RecipeDto, Item> {

    override fun mapToDomainModel(model: RecipeDto): Item {
        return Item(
            id = model.pk,
            title = model.title,
            featuredImage = model.featuredImage,
            rating = model.rating,
            publisher = model.publisher,
            sourceUrl = model.sourceUrl,
            ingredients = model.ingredients,
            dateAdded = model.dateAdded,
            dateUpdated = model.dateUpdated,
        )
    }

    override fun mapFromDomainModel(domainModel: Item): RecipeDto {
        return RecipeDto(
            pk = domainModel.id,
            title = domainModel.title,
            featuredImage = domainModel.featuredImage,
            rating = domainModel.rating,
            publisher = domainModel.publisher,
            sourceUrl = domainModel.sourceUrl,
            ingredients = domainModel.ingredients,
            dateAdded = domainModel.dateAdded,
            dateUpdated = domainModel.dateUpdated,
        )
    }

    fun toDomainList(initial: List<RecipeDto>): List<Item>{
        return initial.map { mapToDomainModel(it) }
    }

    fun fromDomainList(initial: List<Item>): List<RecipeDto>{
        return initial.map { mapFromDomainModel(it) }
    }


}
