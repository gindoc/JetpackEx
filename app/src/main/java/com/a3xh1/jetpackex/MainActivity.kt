package com.a3xh1.jetpackex

import android.os.Bundle
import android.widget.Toast
import com.a3xh1.jetpackex.base.view.activity.BaseActivity
import com.a3xh1.jetpackex.databinding.ActivityMainBinding
import com.a3xh1.jetpackex.view.main.HomeFragment
import javax.inject.Inject
import javax.inject.Named

/**
 * <sourceFolder url="file://$MODULE_DIR$/build/generated/not_namespaced_r_class_sources/debug/processDebugResources/r"
          isTestSource="false"
          generated="true" />
 */
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutId: Int = R.layout.activity_main

    @field:Named("main")
    @Inject
    lateinit var className:String

    // 不加field会报错，因为编译为 Java 字节码的时候会对应三个目标元素，一个是变量本身、还有 getter 和 setter，
    // Kotlin 不知道这个变量的注解应该使用到那个目标上
    @field:Named("home")
    @Inject
    lateinit var homeClassName:String

    @Inject
    lateinit var homeFragment: HomeFragment

    @Inject
    lateinit var homeFragment2: HomeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Toast.makeText(this, "$className  $homeClassName", Toast.LENGTH_SHORT).show()

        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.add(R.id.cl_container, homeFragment)
        beginTransaction.commit()

    }
}
