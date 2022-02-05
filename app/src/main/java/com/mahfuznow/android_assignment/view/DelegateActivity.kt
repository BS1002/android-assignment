package com.mahfuznow.android_assignment.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.mahfuznow.android_assignment.R
import com.mahfuznow.android_assignment.adapter.DelegateActivityRVAdapter
import com.mahfuznow.android_assignment.model.Country
import com.mahfuznow.android_assignment.model.User
import com.mahfuznow.android_assignment.model.userdata.Result
import com.mahfuznow.android_assignment.viewmodel.DelegateActivityViewModel
import kotlin.collections.ArrayList


class DelegateActivity : AppCompatActivity() {

    private var isErrorCountry: Boolean = true
    private var isErrorUser: Boolean = true
    private val isValueSet: Boolean = false

    private var countries: List<Country> = ArrayList()
    private var user: User = User()
    private var mergedList: ArrayList<Any> = ArrayList()

    private lateinit var viewModel: DelegateActivityViewModel
    private lateinit var adapter: DelegateActivityRVAdapter
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delegate)

        val actionBar = supportActionBar
        actionBar!!.title = getString(R.string.delegate)
        actionBar.setDisplayHomeAsUpEnabled(true)

        viewModel = ViewModelProvider(this)[DelegateActivityViewModel::class.java]

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progress_bar)

        adapter = DelegateActivityRVAdapter(this)
        //items is a field defined in super class of the adapter
        adapter.items = mergedList

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        observeLiveData()

        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout)
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.reloadData()
        }
    }


    private fun observeLiveData() {
        viewModel.countriesLiveData.observe(
            this
        ) { countries ->
            this.countries = countries
            onSuccess("Country")
        }
        viewModel.isErrorCountryLiveData.observe(
            this
        ) { isError ->
            if (isError)
                onError("Country")
            else {
                isErrorCountry = false
                setValues()
            }
        }

        viewModel.userLiveData.observe(
            this
        ) { user ->
            this.user = user
            onSuccess("User")
        }
        viewModel.isErrorUserLiveData.observe(
            this
        ) { isError ->
            if (isError)
                onError("User")
            else {
                isErrorUser = false
                setValues()
            }

        }
    }

    private fun onSuccess(msg: String) {
        Toast.makeText(this, "$msg's data loaded successfully", Toast.LENGTH_SHORT).show()
        setValues()
    }

    private fun onError(msg: String) {
        progressBar.visibility = View.INVISIBLE
        swipeRefreshLayout.isRefreshing = false
        Toast.makeText(this, "Failed to load data $msg's data", Toast.LENGTH_SHORT).show()
    }

    private fun setValues() {
        if (!isValueSet) { //IMPORTANT
            if (!isErrorCountry && !isErrorUser) {
                progressBar.visibility = View.INVISIBLE
                swipeRefreshLayout.isRefreshing = false
                mergedList = mergeList(countries, user.results!!)
                mergedList.shuffle()
                adapter.items = mergedList //IMPORTANT
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun mergeList(list1: List<Country>, list2: List<Result>): ArrayList<Any> {
        val mergedList: ArrayList<Any> = ArrayList()
        mergedList.addAll(list1)
        mergedList.addAll(list2)
        return mergedList
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.delegate_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {
                finish()
            }
            R.id.menu_item_countries -> {
               item.isChecked = !item.isChecked
            }
            R.id.menu_item_users -> {
                item.isChecked = !item.isChecked
            }
        }
        return super.onOptionsItemSelected(item)
    }
}