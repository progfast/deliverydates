package com.streetmart.deliverytimes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.streetmart.deliverytimes.R
import com.streetmart.deliverytimes.databinding.ProductItemBinding
import com.streetmart.deliverytimes.model.Product
import com.streetmart.deliverytimes.util.ItemClickListener

class ProductsAdapter(private var dataList: List<Product>) :
    RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    private var clickListener: ItemClickListener? = null

    fun setOnClickListener(clickListener: ItemClickListener) {
        this.clickListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ProductItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.product_item, parent, false
        )

        return ViewHolder(binding, clickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ViewHolder(
        private var binding: ProductItemBinding,
        private val listener: ItemClickListener?
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(product: Product) {
            binding.product = product
            listener?.let {
                binding.clickListener = listener
            }
        }
    }
}