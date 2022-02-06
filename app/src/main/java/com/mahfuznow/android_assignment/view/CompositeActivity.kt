package com.mahfuznow.android_assignment.view

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mahfuznow.android_assignment.R
import com.mahfuznow.android_assignment.adapter.CompositeActivityRVAdapter
import com.mahfuznow.android_assignment.model.country.Country
import com.mahfuznow.android_assignment.viewmodel.SimpleActivityViewModel
import java.util.ArrayList


class CompositeActivity : AppCompatActivity() {

    private lateinit var listValues: ArrayList<Country>
    private lateinit var rvAdapter: CompositeActivityRVAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    //as we are using same data as "SimpleActivity" here, we can reuse the "SimpleActivityViewModel" here
    private lateinit var viewModel: SimpleActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_composite)

        val actionBar = supportActionBar
        actionBar!!.title = getString(R.string.composite)
        actionBar.setDisplayHomeAsUpEnabled(true)

        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progress_bar)

        viewModel = ViewModelProvider(this)[SimpleActivityViewModel::class.java]

        listValues = ArrayList<Country>()
        rvAdapter = CompositeActivityRVAdapter(this,listValues)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = rvAdapter

        observeLiveData()
    }


    private fun observeLiveData() {
        viewModel.getCountriesLiveData().observe(this
        ) { countries -> setValues(countries) }
        viewModel.getIsErrorLiveData().observe(this
        ) { isError ->
            if (isError) {
                onError()
            }
        }
    }

    private fun setValues(countries: List<Country>) {
        listValues.clear()
        for (country in countries) {
            listValues.add(country)
            progressBar.visibility = View.INVISIBLE
        }
        rvAdapter.notifyDataSetChanged()
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