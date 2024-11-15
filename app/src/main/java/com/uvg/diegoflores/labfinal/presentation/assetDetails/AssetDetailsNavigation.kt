package com.uvg.diegoflores.labfinal.presentation.assetDetails

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data class AssetDetailsDestination(
    val assetId : String
)

fun NavController.toAssetDetailsScreen(
    destination: AssetDetailsDestination,
    navOptions: NavOptions? = null
) {
    this.navigate(destination, navOptions)
}

fun NavGraphBuilder.assetDetailsScreen(
    onNavigateBack: () -> Unit
) {
    composable<AssetDetailsDestination> {
        AssetDetailsRoute(
            onNavigateBack = onNavigateBack
        )
    }
}