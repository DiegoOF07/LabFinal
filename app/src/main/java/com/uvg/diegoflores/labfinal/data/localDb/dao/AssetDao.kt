package com.uvg.diegoflores.labfinal.data.localDb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.uvg.diegoflores.labfinal.data.localDb.entity.AssetEntity

@Dao
interface AssetDao {
    @Query("SELECT * FROM AssetEntity")
    suspend fun getAllAssets(): List<AssetEntity>

    @Query("SELECT * FROM AssetEntity WHERE id = :id")
    suspend fun getAssetById(id: String): AssetEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(assets: List<AssetEntity>)
}