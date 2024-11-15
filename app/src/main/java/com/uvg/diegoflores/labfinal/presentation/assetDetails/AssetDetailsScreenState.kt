package com.uvg.diegoflores.labfinal.presentation.assetDetails

data class AssetDetailsScreenState(
    val symbol: String = "",
    val name: String = "",
    val priceUsd: String = "",
    val changePercent24Hr: String = "",
    val supply: String = "",
    val maxSupply: String = "",
    val marketCapUsd: String = ""
)