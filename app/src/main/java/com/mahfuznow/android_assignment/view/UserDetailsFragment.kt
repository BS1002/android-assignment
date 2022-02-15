package com.mahfuznow.android_assignment.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mahfuznow.android_assignment.R
import com.mahfuznow.android_assignment.databinding.FragmentUserDetailsBinding

class UserDetailsFragment : Fragment() {

    private lateinit var binding: FragmentUserDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val userResult = UserDetailsFragmentArgs.fromBundle(it).userResult
            Glide.with(requireContext())
                .load(userResult.picture.large)
                .apply(RequestOptions.circleCropTransform())
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(binding.image)

            val name = userResult.name
            val fullName = name.title + " " + name.first + " " + name.last
            binding.name.text = fullName
            binding.gender.text = userResult.gender
            binding.dob.text = userResult.dob.date
            binding.email.text = userResult.email
            binding.cell.text = userResult.cell
            binding.phone.text = userResult.phone
            val location = userResult.location
            val address = location.city + ", " + location.state + ", " + location.country
            binding.address.text = address

        }

    }
}