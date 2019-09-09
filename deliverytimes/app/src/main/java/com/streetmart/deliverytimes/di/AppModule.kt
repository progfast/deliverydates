package com.streetmart.deliverytimes.di

import android.app.Application
import android.content.Context
import com.streetmart.deliverytimes.repository.ProductRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: Application) {
    @Provides
    @Singleton
    fun provideContext() = app

    @Provides
    @Singleton
    fun provideProductRepo() = ProductRepository()

}