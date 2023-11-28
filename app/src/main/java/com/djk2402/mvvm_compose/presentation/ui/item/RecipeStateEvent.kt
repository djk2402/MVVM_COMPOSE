package com.djk2402.mvvm_compose.presentation.ui.recipe

sealed class RecipeEvent{

    data class GetRecipeEvent(
        val id: Int
    ): RecipeEvent()

}