package com.luizafmartinez.m99_projetoifoodandroid.di

import com.google.android.gms.common.moduleinstall.ModuleInstallStatusUpdate.InstallState
import com.google.firebase.auth.FirebaseAuth
import com.luizafmartinez.m99_projetoifoodandroid.data.remote.firebase.repository.AutenticacaoRepositoryImpl
import com.luizafmartinez.m99_projetoifoodandroid.data.remote.firebase.repository.IAutenticacaoRepository
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

    @Provides
    fun provideAutenticacaoRepository(
        firebaseAuth: FirebaseAuth
    ) : IAutenticacaoRepository {
        return AutenticacaoRepositoryImpl( firebaseAuth )
    }

    @Provides
    fun provideFirebaseAuth() : FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

}