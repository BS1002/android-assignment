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
import com.bumptech.glide.request.RequestOptions
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.mahfuznow.android_assignment.R
import com.mahfuznow.android_assignment.model.user.Result
import com.mahfuznow.android_assignment.view.ListFragmentDirections
import javax.inject.Inject

class UserResultAdapterDelegate @Inject constructor(private val application: Application) :
    AdapterDelegate<ArrayList<Any>>() {

    public override fun isForViewType(items: ArrayList<Any>, position: Int) =
        items[position] is Result

    public override fun onCreateViewHolder(parent: ViewGroup) =
        UserResultViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        )

    public override fun onBindViewHolder(
        items: ArrayList<Any>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val userResult = items[position]
        userResult as Result
        holder as UserResultViewHolder
        val name = userResult.name
        val fullName = name.title + " " + name.first + " " + name.last
        holder.textView.text = fullName
        Glide.with(holder.itemView.context)
            .load(userResult.picture.medium)
            .apply(RequestOptions.circleCropTransform())
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.imageView)

        holder.itemView.setOnClickListener {
            //Toast.makeText(application, "You have clicked user: $fullName", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it)
                .navigate(ListFragmentDirections.actionListFragmentToUserDetailsFragment(userResult))
        }
    }

    class UserResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textView)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }
}