package com.example.clickstask.features.presentation.ui

import android.os.Bundle
import android.view.View
import  androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clickstask.R
import com.example.clickstask.core.base.BaseFragment
import com.example.clickstask.core.base.UserMessage
import com.example.clickstask.core.network.remote.Resource
import com.example.clickstask.core.utils.showMessage
import com.example.clickstask.databinding.FragmentHomeScreenBinding
import com.example.clickstask.features.domain.models.NewsItems
import com.example.clickstask.features.presentation.NewsRecyclerAdapter
import com.example.clickstask.features.presentation.NewsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*
import kotlin.collections.ArrayList

class HomeScreenFragment :
    BaseFragment<FragmentHomeScreenBinding>(FragmentHomeScreenBinding::inflate),
    NewsRecyclerAdapter.OnNewsItemClickListener {

    private var newsList: List<NewsItems> = ArrayList()
    private val newNewsList: ArrayList<NewsItems> = ArrayList()

    private val viewModel: NewsViewModel by viewModel()

    private val adapter: NewsRecyclerAdapter by lazy {
        NewsRecyclerAdapter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getAllNews()
        handleSearchView()
        setUpRecyclerView()
    }

    private fun getAllNews() {
        viewModel.getNews()
        viewModel.getNewsResponse.observe(viewLifecycleOwner) {

            when (it) {
                is Resource.Success -> {
                    hideLoading()
                    newsList = it.data.orEmpty()

                    if (newsList.isNotEmpty()) {
                        showList(newsList)
                    } else {
                        showEmptyView()
                    }
                }

                is Resource.Error -> {
                    hideLoading()
                    showError(it.errorTypes.errorMessage)
                }

                is Resource.Loading -> {
                    showLoading()
                }
            }
        }
    }

    private fun showList(newsList: List<NewsItems>) {
        adapter.setList(newsList)
    }

    private fun showEmptyView() {
        requireContext().showMessage(getString(R.string.no_data_toshow))
    }

    private fun showLoading() {
        binding.progress.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.progress.visibility = View.GONE
    }

    private fun setUpRecyclerView() {
        binding.newsRv.layoutManager = LinearLayoutManager(requireContext())
        binding.newsRv.adapter = adapter
    }

    private fun showError(errorMessage: UserMessage?) {
        viewModel.showErrorMessage(requireContext(), errorMessage)
    }

    private fun handleSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                handleSearchInput(newText.orEmpty())
                return true
            }
        })
    }

    private fun handleSearchInput(input: String) {
        if (input.isNotEmpty()) {
            newNewsList.clear()
            val search = input.lowercase(Locale.getDefault())
            newsList.forEach {
                if (it.title?.lowercase(Locale.getDefault())?.contains(search) == true) {
                    newNewsList.add(it)
                }
            }

            adapter.setList(newNewsList)
            binding.newsRv.adapter?.notifyDataSetChanged()
        } else {
            newNewsList.clear()
            newNewsList.addAll(newsList)
            binding.newsRv.adapter?.notifyDataSetChanged()
        }
    }

    override fun onNewsItemClick(position: Int) {
        navigateToNewsDetailsFragment(position)
    }

    private fun navigateToNewsDetailsFragment(position: Int) {
        findNavController().navigate(
            HomeScreenFragmentDirections
                .actionToNewsDetailsFragment(
                    sourceName = newsList[position].sourceName.orEmpty(),
                    title = newsList[position].title.orEmpty(),
                    desc = newsList[position].description.orEmpty(),
                    imageUrl = newsList[position].image.orEmpty()
                )
        )
    }


}