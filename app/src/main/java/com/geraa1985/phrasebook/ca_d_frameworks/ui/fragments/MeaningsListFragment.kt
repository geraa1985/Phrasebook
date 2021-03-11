package com.geraa1985.phrasebook.ca_d_frameworks.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.geraa1985.model.DataModel
import com.geraa1985.phrasebook.ca_c_adapters.viewmodels.ListFragmentViewModel
import com.geraa1985.phrasebook.ca_d_frameworks.ui.cicerone_navigation.BackButtonListener
import com.geraa1985.phrasebook.ca_d_frameworks.ui.rv_adapters.MeaningsListAdapter
import com.geraa1985.phrasebook.databinding.FragmentListBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MeaningsListFragment : Fragment(), BackButtonListener {

    companion object {
        private const val WORD_KEY = "word"
        fun startWithWord(word: String) = MeaningsListFragment().apply {
            arguments = Bundle().apply { putString(WORD_KEY, word) }
        }
    }

    private lateinit var binding: FragmentListBinding

    private var adapter: MeaningsListAdapter? = null

    private val viewModel: ListFragmentViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        creatAdapter()

        arguments?.getString(WORD_KEY)?.let {
            viewModel.getData(it)
            arguments = null
        }

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
                if (it) {
                    onFabSearchClicked()
                }
            }
            getNoSuchWordLiveData().observe(viewLifecycleOwner) {
                noSuchWord(it)
            }
        }
    }

    private fun onFabSearchClicked() {
        val dialogSearch = SearchDialogFragment()
        dialogSearch.setOnSearchClickListener(object : SearchDialogFragment.OnSearchClickListener {
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

    private fun creatAdapter() {
        binding.rvMeanings.layoutManager = LinearLayoutManager(requireContext())
        adapter = MeaningsListAdapter()
        binding.rvMeanings.adapter = adapter
        adapter?.setOnItemClickListener(object : MeaningsListAdapter.OnItemClickListener {
            override fun onClick(word: String, translation: String?, imgUrl: String) {
                viewModel.itemClicked(word, translation, imgUrl)
            }
        })
    }

    private fun showData(meaningsList: List<com.geraa1985.model.DataModel>) {
        adapter?.setData(meaningsList)
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