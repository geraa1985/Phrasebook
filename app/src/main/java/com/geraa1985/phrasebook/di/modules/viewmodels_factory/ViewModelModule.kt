package com.geraa1985.phrasebook.di.modules.viewmodels_factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.geraa1985.phrasebook.ca_c_adapters.viewmodels.list_fragment_viewmodel.ListFragmentViewModel
import com.geraa1985.phrasebook.ca_c_adapters.viewmodels.main_activity_viewmodel.MainActivityViewModel
import com.geraa1985.phrasebook.di.modules.InteractorModule
import com.geraa1985.phrasebook.di.modules.NavigationModule
import com.geraa1985.phrasebook.di.modules.RxModule
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(
    includes = [
        InteractorModule::class,
        NavigationModule::class,
        RxModule::class
    ]
)
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ListFragmentViewModel::class)
    protected abstract fun listFragmentViewModel(listViewModel: ListFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    protected abstract fun mainViewModel(mainViewModel: MainActivityViewModel): ViewModel
}
