package com.a3xh1.jetpackex.view.auth

import androidx.navigation.findNavController
import com.a3xh1.jetpackex.base.view.activity.BaseActivity
import com.a3xh1.jetpackex.databinding.ActivityAuthBinding
import com.a3xh1.jetpackex.leak.IMMLeaks
import com.a3xh1.jetpackex.R


/**
 * Author: GIndoc on 2019/3/5.
 * FOR   :
 */
class AuthActivity : BaseActivity<ActivityAuthBinding>() {

    override val layoutId: Int = R.layout.activity_auth

    override fun onSupportNavigateUp() = findNavController(R.id.auth_fragment).navigateUp()

    override fun onDestroy() {
        super.onDestroy()
        IMMLeaks.fixInputMethod(this)
    }
}