package com.streetmart.deliverytimes.di

import com.streetmart.deliverytimes.DeliveryTimesApp
import com.streetmart.deliverytimes.viewModel.ProductsViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(target: DeliveryTimesApp)
    fun inject(target: ProductsViewModel)
}