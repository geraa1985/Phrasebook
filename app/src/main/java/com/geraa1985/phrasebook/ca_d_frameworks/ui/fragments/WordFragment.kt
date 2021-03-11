package com.geraa1985.phrasebook.ca_d_frameworks.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.request.RequestOptions
import com.geraa1985.phrasebook.ca_c_adapters.viewmodels.ILoadImage
import com.geraa1985.phrasebook.databinding.FragmentWordBinding
import org.koin.android.ext.android.getKoin
import ru.terrakok.cicerone.Router

class WordFragment : Fragment(), com.geraa1985.phrasebook.ca_d_frameworks.cicerone_navigation.BackButtonListener {

    companion object {
        private const val WORD_KEY = "WORD_KEY"
        private const val TRANSLATION_KEY = "TRANSLATION_KEY"
        private const val IMAGE_KEY = "IMAGE_KEY"
        fun newInstance(word: String, translation: String?, imgUrl: String) = WordFragment().apply {
            arguments = Bundle().apply {
                putString(WORD_KEY, word)
                putString(TRANSLATION_KEY, translation)
                putString(IMAGE_KEY, imgUrl)
            }
        }
    }

    private lateinit var binding: FragmentWordBinding

    private val imgLoader: ILoadImage<ImageView, RequestOptions> by lazy {
        getKoin().get()
    }

    private val router: Router by lazy {
        getKoin().get()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWordBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        setData()

        binding.swipeToRefresh.setOnRefreshListener {
            setData()
            binding.swipeToRefresh.isRefreshing = false
        }
    }

    private fun setData() {
        binding.word.text = arguments?.getString(WORD_KEY)
        binding.translation.text = arguments?.getString(TRANSLATION_KEY)
        arguments?.getString(IMAGE_KEY)?.let {
            imgLoader.loadInto(it, binding.image, null) {
                binding.progressCircular.visibility = View.GONE
            }
        }
    }


    override fun backClicked(): Boolean {
        router.exit()
        return true
    }

}