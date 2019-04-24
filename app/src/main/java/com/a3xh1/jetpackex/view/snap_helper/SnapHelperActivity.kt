package com.a3xh1.jetpackex.view.snap_helper

import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.PagerSnapHelper
import com.a3xh1.basecore.custom.view.recyclerview.DividerItemDecoration
import com.a3xh1.jetpackex.R
import com.a3xh1.jetpackex.base.view.activity.BaseActivity
import com.a3xh1.jetpackex.databinding.ActivitySnapHelperBinding
import javax.inject.Inject


/**
 * Author: GIndoc on 2019/4/22.
 * FOR   :
 */

class SnapHelperActivity : BaseActivity<ActivitySnapHelperBinding>() {

    override val layoutId = R.layout.activity_snap_helper

    @Inject
    lateinit var mAdapter: SnapHelperAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.rvLinear.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvLinear.adapter = mAdapter
        val dividerLinear = DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL)
        dividerLinear.setDivider(ResourcesCompat.getDrawable(resources, R.drawable.divider_tranparent_10, null))
        binding.rvLinear.addItemDecoration(dividerLinear)
        val linearHelper = LinearSnapHelper()
        linearHelper.attachToRecyclerView(binding.rvLinear)

        binding.rvPager.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvPager.adapter = mAdapter
        val dividerPager = DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL)
        dividerPager.setDivider(ResourcesCompat.getDrawable(resources, R.drawable.divider_tranparent_10, null))
        binding.rvPager.addItemDecoration(dividerPager)
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.rvPager)


        binding.rvStart.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvStart.adapter = mAdapter
        val dividerStart = DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL)
        dividerStart.setDivider(ResourcesCompat.getDrawable(resources, R.drawable.divider_tranparent_10, null))
        binding.rvStart.addItemDecoration(dividerStart)
        val startHelper = StartSnapHelper()
        startHelper.attachToRecyclerView(binding.rvStart)

        mAdapter.data = listOf(
            "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2153937626,1074119156&fm=27&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3300305952,1328708913&fm=27&gp=0.jpg",
            "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1326432249,230208234&fm=27&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1933860291,3496197037&fm=27&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1403936986,4231638334&fm=27&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2960797825,927139978&fm=26&gp=0.jpg",

            "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2153937626,1074119156&fm=27&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3300305952,1328708913&fm=27&gp=0.jpg",
            "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1326432249,230208234&fm=27&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1933860291,3496197037&fm=27&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1403936986,4231638334&fm=27&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2960797825,927139978&fm=26&gp=0.jpg"
        )
    }
}