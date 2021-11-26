package com.mahfuznow.android_assignment.view

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.mahfuznow.android_assignment.R
import com.mahfuznow.android_assignment.model.Country
import com.mahfuznow.android_assignment.viewmodel.SimpleActivityViewModel
import java.util.ArrayList


class SimpleActivity : AppCompatActivity() {

    private lateinit var listValues: ArrayList<String>
    private lateinit var arrayAdapter: ArrayAdapter<String>
    private lateinit var listView: ListView
    private lateinit var progressBar: ProgressBar
    private lateinit var viewModel: SimpleActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple)

        val actionBar = supportActionBar
        actionBar!!.title = getString(R.string.simple)
        actionBar.setDisplayHomeAsUpEnabled(true)

        listView = findViewById(R.id.listView)
        progressBar = findViewById(R.id.progress_bar)

        viewModel = ViewModelProvider(this)[SimpleActivityViewModel::class.java]
        listValues = ArrayList<String>()
        arrayAdapter = ArrayAdapter(this, R.layout.listview_row_layout, R.id.textView, listValues)
        listView.adapter = arrayAdapter

        listView.onItemClickListener =
            OnItemClickListener { parent, view, position, id ->
                Toast.makeText(this, "You clicked on " + listValues[position], Toast.LENGTH_SHORT)
                    .show()
            }
        observeLiveData()
    }


    private fun observeLiveData() {
        viewModel.getCountriesLiveData().observe(this,
            { countries -> setValues(countries) })
        viewModel.getIsErrorLiveData().observe(this,
            { isError ->
                if (isError) {
                    onError()
                }
            })
    }

    private fun setValues(countries: List<Country>) {
        listValues.clear()
        for (country in countries) {
            listValues.add(country.name)
            progressBar.visibility = View.INVISIBLE
        }
        arrayAdapter.notifyDataSetChanged()
    }

    private fun onError() {
        progressBar.visibility = View.INVISIBLE
        Toast.makeText(this, "Failed to load data", Toast.LENGTH_SHORT).show()
    }


    //for back arrow functionality
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }

}