package ru.shtykin.testappchat.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.shtykin.bbs_mobile.domain.Repository
import ru.shtykin.bbs_mobile.domain.usecase.GetCamerasFromDbUseCase
import ru.shtykin.bbs_mobile.domain.usecase.GetCamerasUseCase
import ru.shtykin.bbs_mobile.domain.usecase.GetDoorsFromDbUseCase
import ru.shtykin.bbs_mobile.domain.usecase.GetDoorsUseCase
import ru.shtykin.bbs_mobile.domain.usecase.ToggleFavoriteCameraInDbUseCase
import ru.shtykin.bbs_mobile.domain.usecase.UpdateAllCamerasToDbUseCase
import ru.shtykin.bbs_mobile.domain.usecase.UpdateAllDoorsToDbUseCase

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideGetCamerasUseCase(repository: Repository): GetCamerasUseCase =
        GetCamerasUseCase(repository)

    @Provides
    fun provideGetDoorsUseCase(repository: Repository): GetDoorsUseCase =
        GetDoorsUseCase(repository)

    @Provides
    fun provideUpdateAllCamerasToDbUseCase(repository: Repository): UpdateAllCamerasToDbUseCase =
        UpdateAllCamerasToDbUseCase(repository)

    @Provides
    fun provideGetCamerasFromDbUseCase(repository: Repository): GetCamerasFromDbUseCase =
        GetCamerasFromDbUseCase(repository)

    @Provides
    fun provideToggleFavoriteCamerasInDbUseCase(repository: Repository): ToggleFavoriteCameraInDbUseCase =
        ToggleFavoriteCameraInDbUseCase(repository)

    @Provides
    fun provideGetDoorsFromDbUseCase(repository: Repository): GetDoorsFromDbUseCase =
        GetDoorsFromDbUseCase(repository)

    @Provides
    fun provideUpdateAllDoorsToDbUseCase(repository: Repository): UpdateAllDoorsToDbUseCase =
        UpdateAllDoorsToDbUseCase(repository)
}