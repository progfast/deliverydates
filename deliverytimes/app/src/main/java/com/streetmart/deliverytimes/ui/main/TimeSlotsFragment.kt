package com.streetmart.deliverytimes.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.streetmart.deliverytimes.R

class TimeSlotsFragment : Fragment() {

    companion object {
        fun newInstance() = TimeSlotsFragment()
    }

    private lateinit var viewModel: TimeSlotsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.time_slots_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TimeSlotsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
