package com.djk2402.mvvm_compose.network.response

import com.djk2402.mvvm_compose.network.model.RecipeDto
import com.google.gson.annotations.SerializedName

data class RecipeSearchResponse(

        @SerializedName("count")
        var count: Int,

        @SerializedName("results")
        var recipes: List<RecipeDto>,
)