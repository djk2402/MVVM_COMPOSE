package com.djk2402.mvvm_compose.repository

import com.djk2402.mvvm_compose.domain.model.Item


interface RecipeRepository {

    suspend fun search(token: String, page: Int, query: String): List<Item>

    suspend fun get(token: String, id: Int): Item

}
