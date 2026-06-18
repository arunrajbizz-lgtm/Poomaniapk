package com.poomani.tv.di

import android.content.Context
import androidx.room.Room
import com.poomani.tv.data.local.AppDatabase
import com.poomani.tv.data.local.SettingsDataStore
import com.poomani.tv.data.local.TvDao
import com.poomani.tv.data.remote.PoomaniTvApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "poomanitv.db").build()

    @Provides
    fun provideTvDao(database: AppDatabase): TvDao = database.tvDao()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            .build()
    }

    @Provides
    @Singleton
    fun providePoomaniTvApi(okHttpClient: OkHttpClient, dataStore: SettingsDataStore): PoomaniTvApi {
        val baseUrl = runBlocking { dataStore.backendUrl.first() }
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PoomaniTvApi::class.java)
    }
}
