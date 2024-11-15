package com.uvg.diegoflores.labfinal.presentation.assetsList

import com.uvg.diegoflores.labfinal.domain.model.Asset

data class AssetsListScreenState(
    val assets: List<Asset> = emptyList(),
    val hasError: Boolean = false,
    val isLoading: Boolean = false,
    val isOffline: Boolean = false,
    val savedDataDate: String = "",
    val errorMsg: String = ""
)