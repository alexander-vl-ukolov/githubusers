package com.immortalalexsan.githubusers.presentation.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import com.immortalalexsan.githubusers.presentation.usermetadata.UserMetadataFragment
import com.immortalalexsan.githubusers.presentation.users.UsersFragment
import javax.inject.Inject

/**
 * Фабрика фрагментов.
 *
 * @property viewModelProviderFactory Фабрика вью моделей.
 */
internal class DefaultFragmentFactory @Inject constructor(
    private val viewModelProviderFactory: ViewModelProvider.Factory,
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (loadFragmentClass(classLoader, className)) {
            UsersFragment::class.java -> UsersFragment.newInstance(viewModelProviderFactory)
            UserMetadataFragment::class.java -> UserMetadataFragment.newInstance(viewModelProviderFactory)
            else -> super.instantiate(classLoader, className)
        }
    }
}
