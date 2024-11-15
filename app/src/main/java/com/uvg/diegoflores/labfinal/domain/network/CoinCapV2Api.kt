package com.uvg.diegoflores.labfinal.domain.network

import com.uvg.diegoflores.labfinal.data.network.dto.AssetDetailsDto
import com.uvg.diegoflores.labfinal.data.network.dto.AssetsListDto
import com.uvg.diegoflores.labfinal.domain.network.util.NetworkError
import com.uvg.diegoflores.labfinal.domain.network.util.Result

interface CoinCapV2Api {
    suspend fun getAllAssets(): Result<AssetsListDto, NetworkError>
    suspend fun getAssetDetails(id: String): Result<AssetDetailsDto, NetworkError>
}