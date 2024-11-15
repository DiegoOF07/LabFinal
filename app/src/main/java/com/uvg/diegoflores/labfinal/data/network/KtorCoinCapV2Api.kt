package com.uvg.diegoflores.labfinal.data.network

import com.uvg.diegoflores.labfinal.data.network.dto.AssetDetailsDto
import com.uvg.diegoflores.labfinal.data.network.dto.AssetsListDto
import com.uvg.diegoflores.labfinal.data.network.util.safeCall
import com.uvg.diegoflores.labfinal.domain.network.CoinCapV2Api
import com.uvg.diegoflores.labfinal.domain.network.util.NetworkError
import com.uvg.diegoflores.labfinal.domain.network.util.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class KtorCoinCapV2Api (
    private val httpClient: HttpClient
): CoinCapV2Api {
    override suspend fun getAllAssets(): Result<AssetsListDto, NetworkError> {
        return safeCall<AssetsListDto>{
            httpClient.get(
                "https://api.coincap.io/v2/assets"
            )
        }
    }

    override suspend fun getAssetDetails(id: String): Result<AssetDetailsDto, NetworkError> {
        return safeCall<AssetDetailsDto>{
            httpClient.get(
                "https://api.coincap.io/v2/assets/$id"
            )
        }
    }


}