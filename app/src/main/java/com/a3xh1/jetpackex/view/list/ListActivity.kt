package com.a3xh1.jetpackex.view.list

import android.os.Bundle
import androidx.lifecycle.Observer
import com.a3xh1.basecore.common.adapter.BasePagingDataBindingAdapter
import com.a3xh1.jetpackex.base.view.activity.BaseActivity
import com.a3xh1.jetpackex.databinding.ActivityListBinding
import com.a3xh1.jetpackex.databinding.ItemMedicineBinding
import com.a3xh1.jetpackex.pojo.Medicine
import com.a3xh1.jetpackex.utils.inflateTitle
import com.a3xh1.jetpackex.R


/**
 * Author: GIndoc on 2019/3/9.
 * FOR   :
 */
class ListActivity : BaseActivity<ActivityListBinding>() {

    private val viewModel by lazy { getInjectViewModel(ListViewModel::class.java) }

    override val layoutId: Int = R.layout.activity_list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inflateTitle(binding.title.root, "列表", this)

        viewModel.pagedList.observe(this, Observer {
            adapter.submitList(it)
        })
        binding.recyclerView.adapter = adapter


    }

    val adapter: BasePagingDataBindingAdapter<Medicine, ItemMedicineBinding> =
        BasePagingDataBindingAdapter(
            layoutId = R.layout.item_medicine,
            bindBinding = { ItemMedicineBinding.bind(it) },
            callback = { medicine, binding, position ->
                    binding.item = medicine
            }
        )
}