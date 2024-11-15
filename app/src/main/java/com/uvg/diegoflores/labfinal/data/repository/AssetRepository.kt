package com.uvg.diegoflores.labfinal.data.repository

import com.uvg.diegoflores.labfinal.data.localDb.dao.AssetDao
import com.uvg.diegoflores.labfinal.data.localDb.entity.mapToEntity
import com.uvg.diegoflores.labfinal.data.localDb.entity.mapToModel
import com.uvg.diegoflores.labfinal.domain.model.Asset

class AssetRepository(
    private val assetDao: AssetDao
){
    suspend fun getAllAssets(): List<Asset> {
        val assets = assetDao.getAllAssets()
        return assets.map { it.mapToModel() }
    }

    suspend fun getAssetById(id: String): Asset{
        val asset = assetDao.getAssetById(id)
        return asset.mapToModel()
    }

    suspend fun insertAll(assets: List<Asset>){
        val assetEntities = assets.map{ it.mapToEntity()}
        assetDao.insertAll(assetEntities)
    }
}