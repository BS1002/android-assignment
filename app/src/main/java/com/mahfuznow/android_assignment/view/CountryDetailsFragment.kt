package com.mahfuznow.android_assignment.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.mahfuznow.android_assignment.R
import com.mahfuznow.android_assignment.databinding.FragmentCountryDetailsBinding

class CountryDetailsFragment : Fragment() {

    private lateinit var binding: FragmentCountryDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCountryDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            with(binding) {
                val country = CountryDetailsFragmentArgs.fromBundle(it).country
                country.flags?.let {
                    Glide.with(requireContext())
                        .load(it.png)
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .into(flag)
                }
                name.text = country.name
                nativeName.text = country.nativeName
                capital.text = country.capital
                region.text = country.region
                population.text = country.population
                area.text = country.area
                independent.text = country.independent
            }

        }

    }
}