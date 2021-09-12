package com.immortalalexsan.githubusers.di

import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import com.immortalalexsan.core.converters.OneWayConverter
import com.immortalalexsan.core.resources.ResourceManager
import com.immortalalexsan.githubusers.domain.models.UserMetadataModel
import com.immortalalexsan.githubusers.domain.models.UserModel
import com.immortalalexsan.githubusers.presentation.converters.UserMetadataModelItemConverter
import com.immortalalexsan.githubusers.presentation.converters.UserModelItemConverter
import com.immortalalexsan.githubusers.presentation.fragments.DefaultFragmentFactory
import com.immortalalexsan.githubusers.presentation.models.UserMetadataModelItem
import com.immortalalexsan.githubusers.presentation.models.UserModelItem
import com.immortalalexsan.githubusers.presentation.viewmodels.ViewModelProviderFactory
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        PresentationModule.PresentationModuleStatic::class
    ]
)
internal class PresentationModule {

    @Provides
    fun provideUserModelItemConverter(): OneWayConverter<UserModel, UserModelItem> =
        UserModelItemConverter()

    @Provides
    fun provideUserMetadataModelItemConverter(
        resourceManager: ResourceManager,
    ): OneWayConverter<UserMetadataModel, UserMetadataModelItem> =
        UserMetadataModelItemConverter(resourceManager)

    @Module
    interface PresentationModuleStatic {
        @Binds
        fun bindViewModelProviderFactory(viewModelProviderFactory: ViewModelProviderFactory): ViewModelProvider.Factory

        @Binds
        fun bindDefaultFragmentFactory(fragmentFactory: DefaultFragmentFactory): FragmentFactory
    }
}
