package com.streetmart.deliverytimes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.streetmart.deliverytimes.R
import com.streetmart.deliverytimes.databinding.DeliveryTimeItemBinding
import com.streetmart.deliverytimes.model.DeliveryTime

class DeliveryTimeAdapter(private var dataList: List<DeliveryTime>) :
    RecyclerView.Adapter<DeliveryTimeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<DeliveryTimeItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.delivery_time_item, parent, false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ViewHolder(
        private var binding: DeliveryTimeItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(product: DeliveryTime) {
            binding.deliveryTime = product
        }
    }
}