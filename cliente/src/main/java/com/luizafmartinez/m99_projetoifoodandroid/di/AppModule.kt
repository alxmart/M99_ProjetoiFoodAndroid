package com.luizafmartinez.m99_projetoifoodandroid.di

import com.google.android.gms.common.moduleinstall.ModuleInstallStatusUpdate.InstallState
import com.luizafmartinez.m99_projetoifoodandroid.domain.usecase.AutenticacaoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn( ViewModelComponent::class )

object AppModule {

    @Provides
    fun provideAutenticacaoUseCase() : AutenticacaoUseCase {
        return AutenticacaoUseCase()
    }

}