package com.uvg.diegoflores.labfinal.di

import android.content.Context
import androidx.room.Room
import com.uvg.diegoflores.labfinal.data.localDb.AppDatabase
import com.uvg.diegoflores.labfinal.data.network.HttpClientFactory
import io.ktor.client.HttpClient

object Dependencies {
    private var database: AppDatabase? = null
    private var httpClient: HttpClient? = null

    private fun buildDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "final_lab.db"
        ).build()
    }

    private fun buildHttpClient(): HttpClient = HttpClientFactory.create()

    fun provideHttpClient(): HttpClient {
        return httpClient ?: synchronized(this) {
            httpClient ?: buildHttpClient().also { httpClient = it }
        }
    }

    fun provideDatabase(context: Context): AppDatabase {
        return database ?: synchronized(this) {
            database ?: buildDatabase(context).also { database = it }
        }
    }


}