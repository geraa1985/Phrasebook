package com.geraa1985.phrasebook.ca_d_frameworks.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.geraa1985.phrasebook.ca_c_adapters.viewmodels.main_activity_viewmodel.MainActivityViewModel
import com.geraa1985.phrasebook.ca_d_frameworks.ui.cicerone_navigation.BackButtonListener
import com.geraa1985.phrasebook.databinding.ActivityMainBinding
import org.koin.android.ext.android.getKoin
import org.koin.android.viewmodel.ext.android.viewModel
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val navigatorHolder: NavigatorHolder by lazy {
        getKoin().get()
    }

    private val navigator: Navigator by lazy {
        SupportAppNavigator(this, supportFragmentManager, binding.hostForFragments.id)
    }

    private val viewModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.start()
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
        viewModel.backClicked()
    }
}