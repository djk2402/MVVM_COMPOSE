package com.djk2402.mvvm_compose.repository

import com.djk2402.mvvm_compose.domain.model.Item
import com.djk2402.mvvm_compose.network.RetrofitWebService
import com.djk2402.mvvm_compose.network.model.ItemDtoMapper

class RecipeRepository_Impl (
    private val retrofitWebService: RetrofitWebService,
    private val mapper: ItemDtoMapper,
): RecipeRepository {

    override suspend fun search(token: String, page: Int, query: String): List<Item> {
        return mapper.toDomainList(retrofitWebService.search(token = token, page = page, query = query).recipes)
    }

    override suspend fun get(token: String, id: Int): Item {
        return mapper.mapToDomainModel(retrofitWebService.get(token = token, id))
    }

}