package com.geraa1985.phrasebook.ca_d_frameworks.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.geraa1985.phrasebook.ca_c_adapters.viewmodels.HistoryListFragmentViewModel
import com.geraa1985.phrasebook.ca_d_frameworks.ui.cicerone_navigation.BackButtonListener
import com.geraa1985.phrasebook.ca_d_frameworks.ui.rv_adapters.HistoryListAdapter
import com.geraa1985.phrasebook.databinding.FragmentHistoryListBinding
import org.koin.android.viewmodel.ext.android.viewModel

class HistoryListFragment : Fragment(), BackButtonListener {

    private lateinit var binding: FragmentHistoryListBinding

    private var adapter: HistoryListAdapter? = null

    private val viewModel: HistoryListFragmentViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        creatAdapter()

        viewModel.apply {
            getShowDataLiveData().observe(viewLifecycleOwner) {
                showData(it)
            }
            getNoSuchWordLiveData().observe(viewLifecycleOwner) {
                noSuchWord(it)
            }
        }

        arguments?.getString(WORD_KEY)?.let {
            viewModel.getData(it)
        }
    }

    private fun creatAdapter() {
        binding.rvHistoryList.layoutManager = LinearLayoutManager(requireContext())
        adapter = HistoryListAdapter()
        binding.rvHistoryList.adapter = adapter
        adapter?.setOnItemClickListener(object : HistoryListAdapter.OnItemClickListener {
            override fun onClick(word: String) {
//                viewModel.itemClicked(word)
            }
        })
    }

    private fun showData(wordList: List<String>) {
        adapter?.setData(wordList)
    }

    private fun noSuchWord(message: String) {
        binding.noWords.text = message
        binding.noWords.visibility = View.VISIBLE
    }

    override fun backClicked(): Boolean {
        return viewModel.backClicked()
    }

    companion object {
        private const val WORD_KEY = "WORD_KEY"
        fun newInstance(word: String) = HistoryListFragment().apply {
            arguments = Bundle().apply {
                putString(WORD_KEY, word)
            }
        }
    }

}