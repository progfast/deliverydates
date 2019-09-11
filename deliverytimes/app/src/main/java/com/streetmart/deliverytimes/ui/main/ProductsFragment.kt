package com.streetmart.deliverytimes.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.streetmart.deliverytimes.R
import com.streetmart.deliverytimes.adapter.ProductsAdapter
import com.streetmart.deliverytimes.databinding.ProductsFragmentBinding
import com.streetmart.deliverytimes.model.Product
import com.streetmart.deliverytimes.model.Products
import com.streetmart.deliverytimes.util.ItemClickListener
import com.streetmart.deliverytimes.util.RetryCallback
import com.streetmart.deliverytimes.util.Status
import com.streetmart.deliverytimes.viewModel.ProductsViewModel
import kotlinx.android.synthetic.main.products_fragment.*

class ProductsFragment : Fragment() {

    private lateinit var productsViewModel: ProductsViewModel
    lateinit var binding: ProductsFragmentBinding
    lateinit var products: List<Product>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.products_fragment,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateBindings()
    }

    private fun updateBindings() {
        productsViewModel = ViewModelProviders.of(this).get(ProductsViewModel::class.java)
        binding.retryCallback = object : RetryCallback {
            override fun retry() {
                reloadData()
            }
        }
        loadData()
        btn_all_time_slots.setOnClickListener {
            if (::products.isInitialized) navigateToTimes(
                products,
                getString(R.string.all_deliveries)
            )
        }

    }

    private fun loadData() {
        binding.status = Status.LOADING
        productsViewModel.loadProducts().observe(viewLifecycleOwner, Observer { apiResponse ->

            apiResponse.products?.let {
                products = it
                initAndSetData(it)
            } ?: run {
                showError(apiResponse.errorMessage)
            }
        })

        swipeRefresh.setOnRefreshListener {
            reloadData()
            swipeRefresh.isRefreshing = false
        }
    }

    private fun reloadData() {
        loadData()
    }

    private fun showError(error: String?) {
        binding.status = Status.ERROR
        binding.errorMessage = error
    }

    private fun initAndSetData(products: List<Product>) {
        when (products.size) {
            0 -> showError(getString(R.string.no_items))
            else -> {
                binding.status = Status.SUCCESS
                binding.rvProducts.apply {
                    val productsAdapter = ProductsAdapter(products)
                    productsAdapter.setOnClickListener(clickListener)
                    adapter = productsAdapter
                }
            }
        }
    }

    private val clickListener = object : ItemClickListener {
        override fun onItemClick(product: Product) {
            navigateToTimes(listOf(product), product.name)
        }
    }

    private fun navigateToTimes(products: List<Product>, title: String) {
        val action = ProductsFragmentDirections.actionOpenTimeSlots(
            Products(products),
            title
        )
        rv_products.findNavController().navigate(action)
    }

}
