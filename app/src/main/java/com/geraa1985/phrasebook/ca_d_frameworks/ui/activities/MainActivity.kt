package com.geraa1985.phrasebook.ca_d_frameworks.ui.activities

import android.os.Bundle
import com.geraa1985.phrasebook.MyApp
import com.geraa1985.phrasebook.ca_c_adapters.presenters.main_activity_presenter.IMainActivityView
import com.geraa1985.phrasebook.ca_c_adapters.presenters.main_activity_presenter.MainActivityPresenter
import com.geraa1985.phrasebook.ca_d_frameworks.ui.cicerone_navigation.BackButtonListener
import com.geraa1985.phrasebook.databinding.ActivityMainBinding
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(),IMainActivityView {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator: Navigator by lazy {
        SupportAppNavigator(this, supportFragmentManager, binding.hostForFragments.id)
    }

    private val presenter: MainActivityPresenter by moxyPresenter {
        MainActivityPresenter().apply { MyApp.instance.mainGraph.inject(this) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MyApp.instance.mainGraph.inject(this)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backClicked()) {
                return
            }
        }
        presenter.backClicked()
    }
}