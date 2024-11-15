package com.uvg.diegoflores.labfinal.data.network.dto

import com.uvg.diegoflores.labfinal.domain.model.Asset
import kotlinx.serialization.Serializable

@Serializable
data class AssetDto(
    val id: String,
    val symbol: String,
    val name: String,
    val priceUsd: String,
    val changePercent24Hr: String,
    val supply: String,
    val maxSupply: String?,
    val marketCapUsd: String
)

fun AssetDto.mapToAssetModel(): Asset {
    return Asset(
        id = id,
        symbol = symbol,
        name = name,
        priceUsd = priceUsd,
        changePercent24Hr = changePercent24Hr,
        supply = supply,
        maxSupply = maxSupply,
        marketCapUsd = marketCapUsd
    )
}