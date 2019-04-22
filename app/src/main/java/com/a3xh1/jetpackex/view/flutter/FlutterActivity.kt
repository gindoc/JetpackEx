package com.a3xh1.jetpackex.view.flutter

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.a3xh1.jetpackex.R
import io.flutter.facade.Flutter

/**
 * Author: GIndoc on 2019/3/14.
 * FOR   :
 */
class FlutterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flutter)
        val flutterFragment = Flutter.createFragment("route1")
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fl_container, flutterFragment)
            .commit()

    }
}