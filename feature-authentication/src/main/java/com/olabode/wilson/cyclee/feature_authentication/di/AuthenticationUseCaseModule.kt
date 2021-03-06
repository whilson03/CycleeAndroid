package com.olabode.wilson.cyclee.feature_authentication.di

import com.olabode.wilson.cyclee.feature_authentication.domain.usecase.login.LoginUseCase
import com.olabode.wilson.cyclee.feature_authentication.domain.usecase.login.LoginUseCaseImpl
import com.olabode.wilson.cyclee.feature_authentication.domain.usecase.register.RegisterUseCase
import com.olabode.wilson.cyclee.feature_authentication.domain.usecase.register.RegisterUseCaseImpl
import com.olabode.wilson.cyclee.feature_authentication.domain.usecase.verification.ResendVerificationTokenUseCase
import com.olabode.wilson.cyclee.feature_authentication.domain.usecase.verification.ResendVerificationTokenUseCaseImpl
import com.olabode.wilson.cyclee.feature_authentication.domain.usecase.verification.TokenVerificationUseCase
import com.olabode.wilson.cyclee.feature_authentication.domain.usecase.verification.TokenVerificationUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * CREATED BY: ADEYORIJU OLABODE WILSON
 * DATE: 29/12/2021
 * EMAIL: whilson03@gmail.com
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthenticationUseCaseModule {

    @Binds
    abstract fun bindRegisterUseCase(
        registerUseCaseImpl: RegisterUseCaseImpl
    ): RegisterUseCase

    @Binds
    abstract fun bindTokenVerificationUseCase(
        tokenVerificationUseCaseImpl: TokenVerificationUseCaseImpl
    ): TokenVerificationUseCase

    @Binds
    abstract fun bindResendVerificationTokenUseCase(
        resendVerificationTokenUseCaseImpl: ResendVerificationTokenUseCaseImpl
    ): ResendVerificationTokenUseCase

    @Binds
    abstract fun bindLoginUseCase(
        loginUseCaseImpl: LoginUseCaseImpl
    ): LoginUseCase
}
