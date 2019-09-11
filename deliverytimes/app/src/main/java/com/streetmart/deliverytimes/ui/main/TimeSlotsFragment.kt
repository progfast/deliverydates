package com.streetmart.deliverytimes.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.streetmart.deliverytimes.R
import com.streetmart.deliverytimes.databinding.TimeSlotsFragmentBinding
import com.streetmart.deliverytimes.viewModel.TimeSlotsViewModel

class TimeSlotsFragment : Fragment() {

    private lateinit var viewModel: TimeSlotsViewModel
    val args: TimeSlotsFragmentArgs by navArgs()
    lateinit var binding: TimeSlotsFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.time_slots_fragment,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TimeSlotsViewModel::class.java)
        val products = args.products
        val title = args.title
        binding.title = title

    }

}
