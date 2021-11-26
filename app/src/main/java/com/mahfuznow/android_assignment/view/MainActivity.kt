package com.mahfuznow.android_assignment.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mahfuznow.android_assignment.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionBar = supportActionBar
        actionBar!!.title = getString(R.string.app_name)
        actionBar.subtitle = getString(R.string.app_subtitle)
        actionBar.setIcon(R.drawable.ic_launcher_foreground)
        actionBar.setDisplayUseLogoEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)

    }

    fun onClickSimple(view: View) {
        startActivity(Intent(this,SimpleActivity::class.java))
    }

    fun onClickComposite(view: View) {
        startActivity(Intent(this, CompositeActivity::class.java))
    }

    fun onClickDelegate(view: View) {
        startActivity(Intent(this, DelegateActivity::class.java))
    }
}