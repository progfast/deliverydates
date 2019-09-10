package com.streetmart.deliverytimes.di

import android.app.Application
import com.streetmart.deliverytimes.api.WebService
import com.streetmart.deliverytimes.repository.ProductRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule(private val app: Application) {
    @Provides
    @Singleton
    fun provideContext() = app

    @Provides
    @Singleton
    fun provideProductRepo() = ProductRepository()

    @Singleton
    @Provides
    fun provideWebService(): WebService {
        return Retrofit.Builder()
            .baseUrl("https://progfast.github.io/")
            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(WebService::class.java)
    }

}