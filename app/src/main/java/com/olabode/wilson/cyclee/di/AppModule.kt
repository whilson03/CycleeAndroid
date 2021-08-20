package com.olabode.wilson.cyclee.di

import android.content.Context
import com.olabode.wilson.cyclee.store.DataStoreManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @InternalCoroutinesApi
    @Provides
    @Singleton
    fun provideDatastore(@ApplicationContext context: Context): DataStoreManagerImpl {
        return DataStoreManagerImpl(context = context)
    }
}
