package com.luizafmartinez.m99_projetoifoodandroid.di

import com.google.android.gms.common.moduleinstall.ModuleInstallStatusUpdate.InstallState
import dagger.Module
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallState( ViewModelComponent::class )

object AppModule {
}