package com.uvg.diegoflores.labfinal.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class AssetsListDto(
    val data: List<AssetDto>,
)