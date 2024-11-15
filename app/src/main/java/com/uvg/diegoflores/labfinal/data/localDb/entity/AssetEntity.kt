package com.uvg.diegoflores.labfinal.data.localDb.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uvg.diegoflores.labfinal.domain.model.Asset

@Entity
data class AssetEntity (
    @PrimaryKey val id: String = "",
    val symbol: String,
    val name: String,
    val priceUsd: String,
    val changePercent24Hr: String,
    val supply: String,
    val maxSupply: String?,
    val marketCapUsd: String
)

fun AssetEntity.mapToModel(): Asset {
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

fun Asset.mapToEntity(): AssetEntity {
    return AssetEntity(
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