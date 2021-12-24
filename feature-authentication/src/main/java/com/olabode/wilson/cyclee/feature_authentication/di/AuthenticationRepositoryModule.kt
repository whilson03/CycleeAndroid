package com.olabode.wilson.cyclee.feature_authentication.di

import com.olabode.wilson.cyclee.feature_authentication.data.repository.AuthenticationRepositoryImpl
import com.olabode.wilson.cyclee.feature_authentication.domain.repository.AuthenticationRepository
import com.olabode.wilson.cyclee.feature_authentication.domain.usecase.register.RegisterUseCase
import com.olabode.wilson.cyclee.feature_authentication.domain.usecase.register.RegisterUseCaseImpl
import com.olabode.wilson.cyclee.feature_authentication.domain.usecase.verification.TokenVerificationUseCase
import com.olabode.wilson.cyclee.feature_authentication.domain.usecase.verification.TokenVerificationUseCaseImpl
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

    @Binds
    abstract fun bindRegisterUseCase(
        registerUseCaseImpl: RegisterUseCaseImpl
    ): RegisterUseCase

    @Binds
    abstract fun bindTokenVerificationUseCase(
        tokenVerificationUseCaseImpl: TokenVerificationUseCaseImpl
    ): TokenVerificationUseCase
}
