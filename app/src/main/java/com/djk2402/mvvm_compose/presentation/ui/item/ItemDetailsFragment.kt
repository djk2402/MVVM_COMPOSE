package com.djk2402.mvvm_compose.presentation.ui.item

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.djk2402.mvvm_compose.presentation.BaseApplication
import com.djk2402.mvvm_compose.presentation.components.CircularIndeterminateProgressBar
import com.djk2402.mvvm_compose.presentation.components.DefaultSnackbar
import com.djk2402.mvvm_compose.presentation.components.LoadingItemShimmer
import com.djk2402.mvvm_compose.presentation.components.ItemView
import com.djk2402.mvvm_compose.presentation.components.util.SnackbarController
import com.djk2402.mvvm_compose.presentation.theme.AppTheme
import com.djk2402.mvvm_compose.presentation.ui.item.RecipeEvent.GetRecipeEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

const val IMAGE_HEIGHT = 260

@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class RecipeFragment: Fragment() {

    @Inject
    lateinit var application: BaseApplication

    private val snackbarController = SnackbarController(lifecycleScope)

    private val viewModel: RecipeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getInt("recipeId")?.let { recipeId ->
            viewModel.onTriggerEvent(GetRecipeEvent(recipeId))
        }
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply{
            setContent {

                val loading = viewModel.loading.value

                val recipe = viewModel.recipe.value

                val scaffoldState = rememberScaffoldState()

                AppTheme(
                    displayProgressBar = loading,
                    scaffoldState = scaffoldState,
                    darkTheme = application.isDark.value,
                ){
                    Scaffold(
                        scaffoldState = scaffoldState,
                        snackbarHost = {
                            scaffoldState.snackbarHostState
                        }
                    ) {
                        Box (
                            modifier = Modifier.fillMaxSize()
                        ){
                            if (loading && recipe == null) LoadingItemShimmer(imageHeight = IMAGE_HEIGHT.dp)
                            else recipe?.let {
                                if(it.id == 1) { // force an error to demo snackbar
                                    snackbarController.getScope().launch {
                                        snackbarController.showSnackbar(
                                            scaffoldState = scaffoldState,
                                            message = "An error occurred with this recipe",
                                            actionLabel = "Ok"
                                        )
                                    }
                                }
                                else{
                                    ItemView(
                                        recipe = it,
                                    )
                                }
                            }
                            CircularIndeterminateProgressBar(isDisplayed = loading, verticalBias = 0.3f)
                            DefaultSnackbar(
                                snackbarHostState = scaffoldState.snackbarHostState,
                                onDismiss = {
                                    scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
                                },
                                modifier = Modifier.align(Alignment.BottomCenter)
                            )
                        }
                    }
                }
            }
        }
    }
}








