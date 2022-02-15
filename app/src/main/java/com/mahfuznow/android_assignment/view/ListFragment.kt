package com.mahfuznow.android_assignment.view

import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.mahfuznow.android_assignment.R
import com.mahfuznow.android_assignment.adapter.DelegateActivityRVAdapter
import com.mahfuznow.android_assignment.databinding.FragmentListBinding
import com.mahfuznow.android_assignment.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ListFragment : Fragment() {

    private val viewModel: MainActivityViewModel by viewModels()

    private var listItems: ArrayList<Any> = ArrayList()

    @Inject
    lateinit var adapter: DelegateActivityRVAdapter

    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var swipeRefreshListener: SwipeRefreshLayout.OnRefreshListener

    private var isLoadCountry = true
    private var isLoadUser = true

    private lateinit var binding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //viewModel.fetchData(isLoadCountry, isLoadUser)
        // moved into viewModel's init to prevent fetching data everytime while returning from another fragment

        val recyclerView: RecyclerView = binding.recyclerView
        progressBar = binding.progressBar

        //items is a field defined in super class of the adapter
        adapter.items = listItems

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
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
            viewLifecycleOwner
        ) { listItems ->
            this.listItems = listItems
            //Toast.makeText(this, "Data loaded successfully", Toast.LENGTH_SHORT).show()
            setValues()
        }
        viewModel.isErrorCountryLiveData.observe(
            viewLifecycleOwner
        ) { isError ->
            if (isError)
                onError("Country")
        }
        viewModel.isErrorUserLiveData.observe(
            viewLifecycleOwner
        ) { isError ->
            if (isError)
                onError("User")
        }
    }

    private fun onError(msg: String) {
        progressBar.visibility = View.INVISIBLE
        swipeRefreshLayout.isRefreshing = false
        Toast.makeText(context, "Failed to load data $msg's data", Toast.LENGTH_SHORT).show()
    }

    private fun setValues() {
        progressBar.visibility = View.INVISIBLE
        swipeRefreshLayout.isRefreshing = false
        adapter.items = listItems //IMPORTANT
        adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delegate_menu, menu)

        //Restoring the checked state of Menu items as Fragment is recreated each time
        //While returning from another fragment
        menu.findItem(R.id.menu_item_countries).isChecked = viewModel.isLoadCountry
        menu.findItem(R.id.menu_item_users).isChecked = viewModel.isLoadUser
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
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