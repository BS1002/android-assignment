package com.mahfuznow.android_assignment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mahfuznow.android_assignment.R
import com.mahfuznow.android_assignment.model.country.Country

class SimpleActivityRVAdapter(
    private val context: Context,
    private val countries: ArrayList<Country>
) :
    RecyclerView.Adapter<SimpleActivityRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_country, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val country = countries[position]
        viewHolder.textView.text = country.name

        Glide.with(viewHolder.itemView.context)
            .load(country.flags.png)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(viewHolder.imageView)

        viewHolder.itemView.setOnClickListener() {
            Toast.makeText(context, "You have clicked " + country.name, Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount() = countries.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.textView)
        val imageView: ImageView = view.findViewById(R.id.imageView)
    }

}
