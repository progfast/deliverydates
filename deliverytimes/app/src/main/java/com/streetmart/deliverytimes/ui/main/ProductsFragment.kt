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
import com.streetmart.deliverytimes.databinding.MainFragmentBinding
import com.streetmart.deliverytimes.model.Product
import com.streetmart.deliverytimes.util.ItemClickListener
import com.streetmart.deliverytimes.viewModel.ProductsViewModel
import kotlinx.android.synthetic.main.main_fragment.*

class ProductsFragment : Fragment() {

    private lateinit var productsViewModel: ProductsViewModel
    lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.main_fragment,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productsViewModel = ViewModelProviders.of(this).get(ProductsViewModel::class.java)
        productsViewModel.loadProducts().observe(viewLifecycleOwner, Observer { apiResponse ->

            apiResponse?.products?.let {
                initAndSetData(it)
            }
        })
    }

    private fun initAndSetData(products: List<Product>) {
        binding.rvProducts.apply {
            val productsAdapter = ProductsAdapter(products)
            productsAdapter.setOnClickListener(clickListener)
            adapter = productsAdapter
        }
    }

    private val clickListener = object : ItemClickListener {
        override fun onItemClick(product: Product) {
            val action = ProductsFragmentDirections.actionOpenTimeSlots(product)
            rv_products.findNavController().navigate(action)
        }
    }

}
