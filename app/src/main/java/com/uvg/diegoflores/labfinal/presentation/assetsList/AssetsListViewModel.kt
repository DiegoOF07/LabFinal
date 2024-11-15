package com.uvg.diegoflores.labfinal.presentation.assetsList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.uvg.diegoflores.labfinal.data.network.KtorCoinCapV2Api
import com.uvg.diegoflores.labfinal.data.network.dto.mapToAssetModel
import com.uvg.diegoflores.labfinal.data.repository.AssetRepository
import com.uvg.diegoflores.labfinal.di.Dependencies
import com.uvg.diegoflores.labfinal.domain.network.CoinCapV2Api
import com.uvg.diegoflores.labfinal.domain.network.util.map
import com.uvg.diegoflores.labfinal.domain.network.util.onError
import com.uvg.diegoflores.labfinal.domain.network.util.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AssetsListViewModel(
    private val assetRepository: AssetRepository,
    private val coinCapV2Api: CoinCapV2Api
): ViewModel() {

    private val _state = MutableStateFlow(AssetsListScreenState())
    val state = _state.asStateFlow()

    init {
        loadDataFromApi()
    }

    fun loadDataFromApi(){
        viewModelScope.launch {
            coinCapV2Api
                .getAllAssets()
                .map { response ->
                    _state.update {
                        it.copy(
                            isLoading = true,
                            hasError = false,
                        )
                    }
                    response.data.map { it.mapToAssetModel() }
                }
                .onSuccess { assets ->
                    _state.update {
                        it.copy(
                            assets = assets,
                            isLoading = false,
                            hasError = false
                        )
                    }
                }
                .onError { error ->
                    _state.update {
                        it.copy(
                            hasError = true,
                            isLoading = false,
                            errorMsg = error.toString()
                        )
                    }

                    loadDataFromRoom()
                }
        }
    }

    private fun loadDataFromRoom(){
        viewModelScope.launch {
            _state.update { it.copy(
                hasError = false,
                isLoading = true
            ) }
            val assets = assetRepository.getAllAssets()
            if(assets.isEmpty()){
                _state.update { it.copy(
                    isLoading = false,
                    hasError = true
                )}
            }
            _state.update { it.copy(
                isLoading = false,
                assets = assets
            ) }
        }
    }

    fun toggleSetOfflineState(){
        _state.update { it.copy(
            isOffline = !it.isOffline
        ) }
    }

    fun getCurrentDate(){
        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
        _state.update { it.copy(
            savedDataDate = dateFormat.format(Date())
        ) }
    }

    fun offlineMode(){
        viewModelScope.launch {
            assetRepository.insertAll(_state.value.assets)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = checkNotNull(this[APPLICATION_KEY])
                val db = Dependencies.provideDatabase(application)
                val api = KtorCoinCapV2Api(Dependencies.provideHttpClient())
                AssetsListViewModel(
                    assetRepository = AssetRepository(db.AssetDao()),
                    coinCapV2Api = api
                )
            }
        }
    }

}