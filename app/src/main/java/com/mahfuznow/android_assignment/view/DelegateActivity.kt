package com.mahfuznow.android_assignment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.mahfuznow.android_assignment.R

class DelegateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delegate)

        val actionBar = supportActionBar
        actionBar!!.title = getString(R.string.delegate)
        actionBar.setDisplayHomeAsUpEnabled(true)

    }


    //for back arrow functionality
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }
}