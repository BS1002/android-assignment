package com.mahfuznow.android_assignment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.mahfuznow.android_assignment.R

class CompositeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_composite)

        val actionBar = supportActionBar
        actionBar!!.title = getString(R.string.composite)
        actionBar.setDisplayHomeAsUpEnabled(true)
    }


    //for back arrow functionality
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }
}