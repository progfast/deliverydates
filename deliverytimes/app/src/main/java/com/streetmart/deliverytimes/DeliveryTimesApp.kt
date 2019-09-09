package com.streetmart.deliverytimes

import android.app.Application
import com.streetmart.deliverytimes.di.AppComponent
import com.streetmart.deliverytimes.di.AppModule
import com.streetmart.deliverytimes.di.DaggerAppComponent

class DeliveryTimesApp: Application() {
    companion object {
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()


        // Dagger dependency injection
        setupDagger()
    }

    private fun setupDagger() {
        component = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
        component.inject(this)

    }

}