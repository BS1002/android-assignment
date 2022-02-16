package com.mahfuznow.android_assignment.adapter

import android.app.Application
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.mahfuznow.android_assignment.R
import com.mahfuznow.android_assignment.databinding.FragmentListBinding
import com.mahfuznow.android_assignment.databinding.ItemCountryBinding
import com.mahfuznow.android_assignment.model.country.Country
import com.mahfuznow.android_assignment.view.ListFragmentDirections
import javax.inject.Inject

class CountryAdapterDelegate @Inject constructor(private val application: Application) :
    AdapterDelegate<ArrayList<Any>>() {

    public override fun isForViewType(items: ArrayList<Any>, position: Int) =
        items[position] is Country

    public override fun onCreateViewHolder(parent: ViewGroup) =
        CountryViewHolder(
            ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    public override fun onBindViewHolder(
        items: ArrayList<Any>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val country = items[position]
        country as Country
        holder as CountryViewHolder

        holder.binding.country = country

        holder.binding.root.setOnClickListener {
            //Toast.makeText(application, "You have clicked country: " + country.name, Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).navigate(
                ListFragmentDirections.actionListFragmentToCountryDetailsFragment(country)
            )
        }
    }

    class CountryViewHolder(val binding: ItemCountryBinding) : RecyclerView.ViewHolder(binding.root)
}