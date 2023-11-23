package com.djk2402.mvvmrecipeapp.network.response

import com.djk2402.mvvmrecipeapp.network.model.RecipeDto
import com.google.gson.annotations.SerializedName

data class RecipeSearchResponse(

        @SerializedName("count")
        var count: Int,

        @SerializedName("results")
        var recipes: List<RecipeDto>,
)