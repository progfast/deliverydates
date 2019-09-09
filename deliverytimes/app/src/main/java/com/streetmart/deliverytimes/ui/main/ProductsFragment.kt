package com.streetmart.deliverytimes.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.streetmart.deliverytimes.R
import com.streetmart.deliverytimes.databinding.MainFragmentBinding
import com.streetmart.deliverytimes.viewModel.ProductsViewModel
import kotlinx.android.synthetic.main.main_fragment.*

class ProductsFragment : Fragment() {

    companion object {
        fun newInstance() = ProductsFragment()
    }

    private lateinit var productsViewModel: ProductsViewModel
//    var binding:MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<MainFragmentBinding>(
            inflater, R.layout.main_fragment,
            container,
            false
        )
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_see_deliveries.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_openTimeSlots)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        productsViewModel =  ViewModelProviders.of(this).get(ProductsViewModel::class.java)

    }

}
