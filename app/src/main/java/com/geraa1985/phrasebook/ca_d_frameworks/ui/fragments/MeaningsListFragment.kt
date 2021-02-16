package com.geraa1985.phrasebook.ca_d_frameworks.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.geraa1985.phrasebook.ca_a_entities.DataModel
import com.geraa1985.phrasebook.ca_c_adapters.viewmodels.list_fragment_viewmodel.ListFragmentViewModel
import com.geraa1985.phrasebook.ca_d_frameworks.ui.cicerone_navigation.BackButtonListener
import com.geraa1985.phrasebook.ca_d_frameworks.ui.rv_adapters.MeaningsListAdapter
import com.geraa1985.phrasebook.databinding.FragmentListBinding
import moxy.MvpAppCompatFragment

class MeaningsListFragment : MvpAppCompatFragment(), BackButtonListener {

    private lateinit var binding: FragmentListBinding

    private var adapter: MeaningsListAdapter? = null

    private val viewModel = ViewModelProvider.NewInstanceFactory().create(ListFragmentViewModel::class.java)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        binding.fabSearch.setOnClickListener {
            viewModel.fabSearchClicked()
        }

        viewModel.apply {
            getShowProgressLiveData().observe(viewLifecycleOwner) {
                if (it) showProgress() else hideProgress()
            }
            getShowDataLiveData().observe(viewLifecycleOwner) {
                showData(it)
            }
            getShowErrorLiveData().observe(viewLifecycleOwner) {
                showError(it)
            }
            getFabSearchClickedLiveData().observe(viewLifecycleOwner) {
                onFabSearchClicked()
            }
            getNoSuchWordLiveData().observe(viewLifecycleOwner) {
                noSuchWord(it)
            }
        }

    }

    private fun onFabSearchClicked() {
        val dialogSearch = SearchDialogFragment()
        dialogSearch.setOnSearchClickListener(object : SearchDialogFragment.OnSearchClickListener{
            override fun onClick(searchWord: String) {
                viewModel.getData(searchWord)
            }
        })
        fragmentManager?.let { dialogSearch.show(it, "DIALOG_SEARCH") }
    }

    private fun showProgress() {
        binding.progressCircular.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        binding.progressCircular.visibility = View.GONE
    }

    private fun showData(meaningsList: List<DataModel>) {
        adapter?.setData(meaningsList) ?: run {
            binding.rvMeanings.layoutManager = LinearLayoutManager(requireContext())
            adapter = MeaningsListAdapter(meaningsList)
            binding.rvMeanings.adapter = adapter
        }
    }

    private fun noSuchWord(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    private fun showError(errorMessage: String) {
        binding.groupError.visibility = View.VISIBLE
        binding.textError.text = errorMessage
        binding.buttonReload.setOnClickListener {
            viewModel.reload()
            binding.groupError.visibility = View.GONE
        }
    }

    override fun backClicked(): Boolean {
        return viewModel.backClicked()
    }

}