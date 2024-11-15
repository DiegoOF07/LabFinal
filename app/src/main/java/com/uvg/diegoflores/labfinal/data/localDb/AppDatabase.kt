package com.uvg.diegoflores.labfinal.data.localDb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.uvg.diegoflores.labfinal.data.localDb.dao.AssetDao
import com.uvg.diegoflores.labfinal.data.localDb.entity.AssetEntity

@Database(entities = [AssetEntity::class],version = 2)
abstract class AppDatabase(): RoomDatabase(){
    abstract fun AssetDao(): AssetDao
}