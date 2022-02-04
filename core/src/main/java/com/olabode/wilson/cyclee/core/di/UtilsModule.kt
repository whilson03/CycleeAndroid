package com.olabode.wilson.cyclee.core.di

import com.olabode.wilson.cyclee.core.utils.snackbar.SnackbarManager
import com.olabode.wilson.cyclee.core.utils.snackbar.SnackbarManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 04/02/2022
 * EMAIL: whilson03@gmail.com
 */

@InstallIn(SingletonComponent::class)
@Module
object UtilsModule {

    @Singleton
    @Provides
    fun provideSnackbarManager(): SnackbarManager = SnackbarManagerImpl()
}
