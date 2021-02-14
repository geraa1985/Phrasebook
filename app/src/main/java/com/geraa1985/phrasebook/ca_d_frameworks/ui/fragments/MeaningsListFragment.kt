package com.geraa1985.phrasebook.ca_d_frameworks.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.geraa1985.phrasebook.MyApp
import com.geraa1985.phrasebook.ca_a_entities.DataModel
import com.geraa1985.phrasebook.ca_c_adapters.presenters.list_fragment_presenter.IListFragmentView
import com.geraa1985.phrasebook.ca_c_adapters.presenters.list_fragment_presenter.ListFragmentPresenter
import com.geraa1985.phrasebook.ca_d_frameworks.ui.cicerone_navigation.BackButtonListener
import com.geraa1985.phrasebook.ca_d_frameworks.ui.rv_adapters.MeaningsListAdapter
import com.geraa1985.phrasebook.databinding.FragmentListBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class MeaningsListFragment : MvpAppCompatFragment(), IListFragmentView, BackButtonListener {

    private lateinit var binding: FragmentListBinding

    private var adapter: MeaningsListAdapter? = null

    private val presenter: ListFragmentPresenter by moxyPresenter {
        ListFragmentPresenter().apply {
            MyApp.instance.mainGraph.inject(this)
        }
    }

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
            presenter.fabSearchClicked()
        }
    }

    override fun fabSearchClicked() {
        val dialogSearch = SearchDialogFragment()
        dialogSearch.setOnSearchClickListener(object : SearchDialogFragment.OnSearchClickListener{
            override fun onClick(searchWord: String) {
                presenter.getData(searchWord)
            }
        })
        fragmentManager?.let { dialogSearch.show(it, "DIALOG_SEARCH") }
    }

    override fun showProgress() {
        binding.progressCircular.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.progressCircular.visibility = View.GONE
    }

    override fun showData(meaningsList: List<DataModel>) {
        adapter?.setData(meaningsList) ?: run {
            binding.rvMeanings.layoutManager = LinearLayoutManager(requireContext())
            adapter = MeaningsListAdapter(meaningsList)
            binding.rvMeanings.adapter = adapter
        }
    }

    override fun noSuchWord(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun showError(errorMessage: String) {
        binding.groupError.visibility = View.VISIBLE
        binding.textError.text = errorMessage
        binding.buttonReload.setOnClickListener {
            presenter.reload()
            binding.groupError.visibility = View.GONE
        }
    }

    override fun backClicked(): Boolean {
        return presenter.backClicked()
    }

}