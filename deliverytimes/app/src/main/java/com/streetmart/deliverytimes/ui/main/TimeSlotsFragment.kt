package com.streetmart.deliverytimes.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs

import com.streetmart.deliverytimes.R
import com.streetmart.deliverytimes.viewModel.TimeSlotsViewModel
import kotlinx.android.synthetic.main.time_slots_fragment.*

class TimeSlotsFragment : Fragment() {

    private lateinit var viewModel: TimeSlotsViewModel
    val args: TimeSlotsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.time_slots_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TimeSlotsViewModel::class.java)
        val product = args.product
        tv_name.text = product.name

    }

}
