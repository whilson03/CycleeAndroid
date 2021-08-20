package com.olabode.wilson.cyclee.di

import com.olabode.wilson.cyclee.session.SessionManager
import com.olabode.wilson.cyclee.session.SessionManagerImpl
import com.olabode.wilson.cyclee.store.DataStoreManager
import com.olabode.wilson.cyclee.store.DataStoreManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.InternalCoroutinesApi

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @InternalCoroutinesApi
    @Binds
    abstract fun bindSessionManager(sessionManagerImpl: SessionManagerImpl): SessionManager

    @InternalCoroutinesApi
    @Binds
    abstract fun bindDataStoreManager(dataStoreManagerImpl: DataStoreManagerImpl): DataStoreManager
}
