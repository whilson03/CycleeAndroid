package com.olabode.wilson.cyclee.feature_authentication.di

import com.olabode.wilson.cyclee.feature_authentication.data.repository.AuthenticationRepositoryImpl
import com.olabode.wilson.cyclee.feature_authentication.domain.repository.AuthenticationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 10/11/2021
 * EMAIL: whilson03@gmail.com
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthenticationRepositoryModule {

    @Binds
    abstract fun bindRegisterRepository(
        authenticationRepositoryImpl: AuthenticationRepositoryImpl
    ): AuthenticationRepository
}
