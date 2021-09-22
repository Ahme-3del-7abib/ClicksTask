package com.example.clickstask.features.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.clickstask.core.base.BaseFragment
import com.example.clickstask.databinding.FragmentNewsDetailsBinding

class NewsDetailsFragment :
    BaseFragment<FragmentNewsDetailsBinding>(FragmentNewsDetailsBinding::inflate) {

    private val args: NewsDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setData()
        clickListeners()
    }

    private fun setData() {
        binding.titleTv.text = args.title
        binding.descriptionTv.text = args.desc
        binding.sourceNameTv.text = args.sourceName

        if (args.imageUrl.isNotEmpty()) {
            Glide.with(requireContext())
                .load(args.imageUrl)
                .into(binding.imageNew)
        }
    }

    private fun clickListeners() {
        binding.backBtn.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }
}