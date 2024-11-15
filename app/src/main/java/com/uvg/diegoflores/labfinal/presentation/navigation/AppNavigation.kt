package com.uvg.diegoflores.labfinal.presentation.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.uvg.diegoflores.labfinal.presentation.assetDetails.AssetDetailsDestination
import com.uvg.diegoflores.labfinal.presentation.assetDetails.assetDetailsScreen
import com.uvg.diegoflores.labfinal.presentation.assetDetails.toAssetDetailsScreen
import com.uvg.diegoflores.labfinal.presentation.assetsList.AssetsListDestination
import com.uvg.diegoflores.labfinal.presentation.assetsList.assetsListScreen
import com.uvg.diegoflores.labfinal.presentation.assetsList.toAssetsListScreen

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier
){
    val navController = rememberNavController()
    Scaffold { innerPadding->
        NavHost(
            navController = navController,
            startDestination = AssetsListDestination,
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ){
            assetsListScreen (
                onNavigateAsset = {id: String->
                    navController.toAssetDetailsScreen(
                        destination = AssetDetailsDestination(id)
                    )
                }
            )

            assetDetailsScreen(
                onNavigateBack = {
                    navController.toAssetsListScreen(
                        destination = AssetsListDestination
                    )
                }
            )
        }
    }
}