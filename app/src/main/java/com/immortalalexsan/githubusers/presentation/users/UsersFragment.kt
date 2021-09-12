package com.immortalalexsan.githubusers.presentation.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.immortalalexsan.core.delegates.BaseDelegateItem
import com.immortalalexsan.core.extensions.gone
import com.immortalalexsan.githubusers.databinding.FragmentUsersBinding
import com.immortalalexsan.githubusers.databinding.ItemUserBinding
import com.immortalalexsan.githubusers.presentation.models.UserModelItem
import com.squareup.picasso.Picasso
import dagger.android.support.DaggerFragment

/**
 * Фрагмент для отображения списка пользователей Github.
 *
 * @property viewModelProviderFactory Фабрика втю моделей.
 */
internal class UsersFragment(private val viewModelProviderFactory: ViewModelProvider.Factory) : DaggerFragment() {

    private val viewModel: UsersViewModel by viewModels(
        factoryProducer = {
            viewModelProviderFactory
        }
    )

    private var _binding: FragmentUsersBinding? = null
    private val binding: FragmentUsersBinding
        get(): FragmentUsersBinding = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupObservers()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUsers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupObservers() {
        viewModel.userModelItems.observe(this) { userModelItems: List<UserModelItem> ->
            setupRecyclerView(userModelItems)
            binding.progressBarLayout.progressBar.gone()
        }

        viewModel.onUserModelItemClick.observe(this) { userModelItem: UserModelItem ->
            val action = UsersFragmentDirections.actionUsersFragmentToUserMetadataFragment(userModelItem.login)
            requireView().findNavController().navigate(action)
        }

        viewModel.onAsyncProcess.observe(this) { asyncProcessInProgress: Boolean ->
            if (asyncProcessInProgress) {
                binding.progressBarLayout.progressBar.startShimmer()
            } else {
                binding.progressBarLayout.progressBar.stopShimmer()
            }
        }

        viewModel.onError.observe(this) { throwable: Throwable ->
            throwable.printStackTrace()
        }
    }

    private fun setupRecyclerView(dataSource: List<UserModelItem>) {
        binding.recyclerView.adapter = ListDelegationAdapter(getUserModelItemsAdapterDelegate()).apply {
            items = dataSource
        }
    }

    private fun getUserModelItemsAdapterDelegate(): AdapterDelegate<List<BaseDelegateItem>> =
        adapterDelegateViewBinding<UserModelItem, BaseDelegateItem, ItemUserBinding>(
            { layoutInflater: LayoutInflater, root: ViewGroup ->
                ItemUserBinding.inflate(layoutInflater, root, false)
            }
        ) {
            binding.root.setOnClickListener {
                item.onBaseDelegateItemClickListener?.onBaseDelegateItemClick(item)
            }

            bind {
                binding.loginTextView.text = item.login
                Picasso.get()
                    .load(item.avatarUrl)
                    .into(binding.avatarImageView)
            }
        }

    companion object {
        /**
         * Создает экземпляр этого фрагмента.
         *
         * @param viewModelProviderFactory Фабрика вью моделей.
         * @return Экземпляр этого фрагмента.
         */
        fun newInstance(viewModelProviderFactory: ViewModelProvider.Factory): UsersFragment =
            UsersFragment(viewModelProviderFactory)
    }
}
