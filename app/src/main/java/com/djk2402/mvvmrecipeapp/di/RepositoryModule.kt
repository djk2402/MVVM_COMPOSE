package com.djk2402.mvvmrecipeapp.di

import com.djk2402.mvvmrecipeapp.network.RecipeService
import com.djk2402.mvvmrecipeapp.network.model.RecipeDtoMapper
import com.djk2402.mvvmrecipeapp.repository.RecipeRepository
import com.djk2402.mvvmrecipeapp.repository.RecipeRepository_Impl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRecipeRepository(
            recipeService: RecipeService,
            recipeMapper: RecipeDtoMapper,
    ): RecipeRepository{
        return RecipeRepository_Impl(
            recipeService = recipeService,
            mapper = recipeMapper
        )
    }
}

