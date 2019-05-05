package com.a3xh1.jetpackex.view.main

import android.content.Intent
import android.widget.Toast
import com.a3xh1.jetpackex.base.view.fragment.BaseFragment
import com.a3xh1.jetpackex.databinding.FragmentHomeBinding
import javax.inject.Inject
import javax.inject.Named
import com.a3xh1.jetpackex.R
import com.a3xh1.jetpackex.view.arc_seek_bar.ArcSeekBarActivity
import com.a3xh1.jetpackex.view.snap_helper.SnapHelperActivity

/**
 * Author: GIndoc on 2019/3/4.
 * FOR   :
 */
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override val layoutId: Int = R.layout.fragment_home

    // 如果需要注入其他数据，则应该在MainActivityModule中提供provideHomeFragment()方法，
    // 否则用@Inject constructor的方式编译通不过，会提示找不到要Inject的数据
    @field:Named("homeString")
    @Inject
    lateinit var className: String

    @field:Named("homeString")
    @Inject
    lateinit var className2: String

    @Inject
    @JvmField
    var num: Int = 0

    @Inject
    lateinit var childFragment: ChildFragment

    @Inject
    lateinit var childFragment2: ChildFragment

    override fun initView() {
        super.initView()
        Toast.makeText(context, "$className  $num", Toast.LENGTH_SHORT).show()
        val beginTransaction = childFragmentManager.beginTransaction()
        beginTransaction.add(R.id.fl_container, childFragment)
        beginTransaction.commit()

        binding.tvSnapHelper.setOnClickListener { startActivity(Intent(context, SnapHelperActivity::class.java)) }

        binding.tvArcSeekBar.setOnClickListener { startActivity(Intent(context, ArcSeekBarActivity::class.java)) }
    }


}