package com.geraa1985.phrasebook.ca_d_frameworks.ui.cicerone_navigation

import androidx.fragment.app.Fragment
import com.geraa1985.phrasebook.ca_d_frameworks.ui.fragments.HistoryListFragment
import com.geraa1985.phrasebook.ca_d_frameworks.ui.fragments.MeaningsListFragment
import com.geraa1985.phrasebook.ca_d_frameworks.ui.fragments.WordFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class FragmentScreen(private val fragment: Fragment) : SupportAppScreen() {

    override fun getFragment(): Fragment {
        return fragment
    }

    companion object {
        fun meaningsListScreen() = FragmentScreen(MeaningsListFragment())
        fun wordScreen(word: String, translation: String?, imgUrl: String) =
            FragmentScreen(WordFragment.newInstance(word, translation, imgUrl))
        fun historyScreen(word: String) = FragmentScreen(HistoryListFragment.newInstance(word))
        fun meaningsWithWord(word: String) = FragmentScreen(MeaningsListFragment.startWithWord(word))
    }

}