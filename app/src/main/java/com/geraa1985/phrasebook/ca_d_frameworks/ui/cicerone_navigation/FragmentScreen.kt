package com.geraa1985.phrasebook.ca_d_frameworks.ui.cicerone_navigation

import androidx.fragment.app.Fragment
import com.geraa1985.phrasebook.ca_d_frameworks.ui.fragments.MeaningsListFragment
import moxy.MvpAppCompatFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class FragmentScreen(private val fragment: MvpAppCompatFragment): SupportAppScreen() {

    override fun getFragment(): Fragment {
        return fragment
    }

    companion object{
        fun meaningsListScreen() = FragmentScreen(MeaningsListFragment())
    }

}