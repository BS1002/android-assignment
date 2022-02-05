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
import com.mahfuznow.android_assignment.model.Country

class CompositeActivityRVAdapter(
    private val context: Context,
    private val countries: List<Country>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_ITEM = 0
        private const val VIEW_TYPE_AD = 1
        private const val VIEW_INTERVALS = 5
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_AD)
            return AdViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_country_large, parent, false)
            )
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val country = countries[position]
        when (getItemViewType(position)) {
            VIEW_TYPE_ITEM -> {
                holder as ItemViewHolder
                holder.textView.text = country.name
                Glide.with(holder.itemView.context)
                    .load(country.flags.png)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(holder.imageView)
                holder.itemView.setOnClickListener() {
                    Toast.makeText(
                        context,
                        "You have clicked ${country.name} from viewType: ITEM",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            VIEW_TYPE_AD -> {
                holder as AdViewHolder
                holder.textView.text = country.name
                Glide.with(holder.itemView.context)
                    .load(country.flags.png)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(holder.imageView)
                holder.itemView.setOnClickListener() {
                    Toast.makeText(
                        context,
                        "You have clicked ${country.name} from viewType: AD",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    }

    override fun getItemCount() = countries.size

    override fun getItemViewType(position: Int): Int {
        if (position % VIEW_INTERVALS == 0 && position > 0)
            return VIEW_TYPE_AD
        return VIEW_TYPE_ITEM
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textView)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }

    class AdViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textView)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }
}