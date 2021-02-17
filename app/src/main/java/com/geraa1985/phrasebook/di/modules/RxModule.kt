package com.geraa1985.phrasebook.di.modules

import com.geraa1985.phrasebook.ca_d_frameworks.rx.ISchedulerProvider
import com.geraa1985.phrasebook.ca_d_frameworks.rx.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module
class RxModule {

    @Provides
    fun rxScheduler(): ISchedulerProvider = SchedulerProvider()
}