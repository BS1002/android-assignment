package com.mahfuznow.android_assignment.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.mahfuznow.android_assignment.R
import com.mahfuznow.android_assignment.adapter.DelegateActivityRVAdapter
import com.mahfuznow.android_assignment.databinding.ActivityMainBinding
import com.mahfuznow.android_assignment.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    private var listItems: ArrayList<Any> = ArrayList()

    private lateinit var adapter: DelegateActivityRVAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var swipeRefreshListener: SwipeRefreshLayout.OnRefreshListener

    private var isLoadCountry = true
    private var isLoadUser = true

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = supportActionBar
        actionBar!!.title = getString(R.string.dagger)
        actionBar.setDisplayHomeAsUpEnabled(true)

        //viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        viewModel.fetchData(isLoadCountry, isLoadUser)

        val recyclerView: RecyclerView = binding.recyclerView
        progressBar = binding.progressBar

        adapter = DelegateActivityRVAdapter(this)
        //items is a field defined in super class of the adapter
        adapter.items = listItems

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        observeLiveData()

        swipeRefreshLayout = binding.swipeRefreshLayout
        swipeRefreshListener = SwipeRefreshLayout.OnRefreshListener {
            viewModel.reFetchData(isLoadCountry, isLoadUser)
        }
        swipeRefreshLayout.setOnRefreshListener(swipeRefreshListener)
    }


    private fun observeLiveData() {
        viewModel.listItems.observe(
            this
        ) { listItems ->
            this.listItems = listItems
            //Toast.makeText(this, "Data loaded successfully", Toast.LENGTH_SHORT).show()
            setValues()
        }
        viewModel.isErrorCountryLiveData.observe(
            this
        ) { isError ->
            if (isError)
                onError("Country")
        }
        viewModel.isErrorUserLiveData.observe(
            this
        ) { isError ->
            if (isError)
                onError("User")
        }
    }

    private fun onError(msg: String) {
        progressBar.visibility = View.INVISIBLE
        swipeRefreshLayout.isRefreshing = false
        Toast.makeText(this, "Failed to load data $msg's data", Toast.LENGTH_SHORT).show()
    }

    private fun setValues() {
        progressBar.visibility = View.INVISIBLE
        swipeRefreshLayout.isRefreshing = false
        adapter.items = listItems //IMPORTANT
        adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.delegate_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
            R.id.menu_item_countries -> {
                item.isChecked = !item.isChecked
                isLoadCountry = item.isChecked
                swipeRefreshLayout.isRefreshing = true
                swipeRefreshListener.onRefresh()
            }
            R.id.menu_item_users -> {
                item.isChecked = !item.isChecked
                isLoadUser = item.isChecked
                swipeRefreshLayout.isRefreshing = true
                swipeRefreshListener.onRefresh()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

