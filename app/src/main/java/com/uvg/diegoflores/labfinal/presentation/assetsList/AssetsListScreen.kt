package com.uvg.diegoflores.labfinal.presentation.assetsList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.uvg.diegoflores.labfinal.domain.model.Asset
import com.uvg.diegoflores.labfinal.presentation.common.ErrorScreen
import com.uvg.diegoflores.labfinal.presentation.common.LoadingScreen
import com.uvg.diegoflores.labfinal.ui.theme.LabFinalTheme

@Composable
fun AssetsListRoute(
    viewModel: AssetsListViewModel = viewModel(factory = AssetsListViewModel.Factory),
    onNavigateAsset: (String) -> Unit){

    val state by viewModel.state.collectAsStateWithLifecycle()

    AssetsListScreen(
        state = state,
        setOffline = {
            viewModel.getCurrentDate()
            viewModel.toggleSetOfflineState()
            viewModel.offlineMode()},
        onNavigateAsset = onNavigateAsset
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AssetsListScreen(
    state: AssetsListScreenState,
    setOffline: ()->Unit,
    onNavigateAsset: (String) -> Unit){
    if(state.hasError){
        ErrorScreen(message = "Error al conseguir los Assets error ${state.errorMsg}") {
            
        }
    }else if(state.isLoading){
        LoadingScreen()
    }else if(!state.isLoading && !state.hasError){
        Column (modifier = Modifier.fillMaxSize()){
            TopAppBar(
                title = {
                    Text("Assets")
                },
                windowInsets = WindowInsets(0.dp, 12.dp, 0.dp,0.dp),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.primary,
                )
            )

            LazyColumn (
                modifier = Modifier.padding(horizontal = 20.dp),
            ){

//                item {
//                    Row (modifier = Modifier
//                        .fillMaxWidth()
//                        .height(120.dp)
//                        .padding(vertical = 20.dp)){
//                        Button(onClick = setOffline) {
//                            Text(text = "Offline: ${state.isOffline}")
//                        }
//                        if(state.isOffline){
//                            Text(text = "Fecha de guardado: ${state.savedDataDate}")
//                        }
//
//                    }
//                }

                items(state.assets) { asset ->
                    AssetItem(asset, onPressed = { onNavigateAsset(asset.id) })
                }
            }
        }
    }
}

@Composable
fun AssetItem(asset: Asset, onPressed: () -> Unit) {
    Spacer(modifier = Modifier.height(25.dp))
    Row (modifier = Modifier
        .fillMaxWidth()
        .height(80.dp)
        .clip(RoundedCornerShape(20))
        .clickable { onPressed() },
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Column(modifier = Modifier.padding(start = 12.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(text = "${asset.name} ${asset.symbol}", style = MaterialTheme.typography.titleMedium)
            Text(text = asset.priceUsd, style = MaterialTheme.typography.bodyMedium)
            Text(text = asset.changePercent24Hr, style = MaterialTheme.typography.bodyMedium)

        }
    }
}

@Preview
@Composable
private fun PreviewAssetsListScreen(){
    LabFinalTheme{
        Surface {
            AssetsListScreen(
                onNavigateAsset = {},
                setOffline = {},
                state = AssetsListScreenState(listOf(
                    Asset(
                        name = "dfsd",
                        id = "pfdsf",
                        changePercent24Hr = "fgdsfs",
                        maxSupply = "fdsfs",
                        supply = "dsfds",
                        symbol = "dfads",
                        marketCapUsd = "gsdfs",
                        priceUsd = "fsdfs"
                    ),
                    Asset(
                        name = "dfsd",
                        id = "pfdsf",
                        changePercent24Hr = "fgdsfs",
                        maxSupply = "fdsfs",
                        supply = "dsfds",
                        symbol = "dfads",
                        marketCapUsd = "gsdfs",
                        priceUsd = "fsdfs"
                    )
                ))
            )
        }
    }
}