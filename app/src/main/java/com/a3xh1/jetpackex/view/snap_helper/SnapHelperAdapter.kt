package com.a3xh1.jetpackex.view.snap_helper

import android.view.LayoutInflater
import android.view.ViewGroup
import com.a3xh1.basecore.custom.view.recyclerview.BaseRecyclerViewAdapter
import com.a3xh1.basecore.custom.view.recyclerview.DataBindingViewHolder
import com.a3xh1.jetpackex.databinding.ItemSnapHelperBinding
import com.bumptech.glide.Glide
import javax.inject.Inject

/**
 * Author: GIndoc on 2019/4/22.
 * FOR   :
 */
class SnapHelperAdapter @Inject constructor(): BaseRecyclerViewAdapter<String>() {

    var inflater: LayoutInflater? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBindingViewHolder {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.context)
        }
        val binding = ItemSnapHelperBinding.inflate(inflater!!, parent, false)
        return DataBindingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataBindingViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val binding = holder.binding as ItemSnapHelperBinding
        Glide.with(holder.view.context).load(mData[position]).into(binding.image)
    }
}