package com.uvg.diegoflores.labfinal.presentation.assetsList

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object AssetsListDestination

fun NavController.toAssetsListScreen(
    destination: AssetsListDestination,
    navOptions: NavOptions? = null
) {
    this.navigate(destination, navOptions)
}

fun NavGraphBuilder.assetsListScreen(
    onNavigateAsset: (String) -> Unit
){
    composable<AssetsListDestination> {
        AssetsListRoute (
            onNavigateAsset = onNavigateAsset
        )
    }
}