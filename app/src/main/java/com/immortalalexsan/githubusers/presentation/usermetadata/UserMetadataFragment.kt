package com.immortalalexsan.githubusers.presentation.usermetadata

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.immortalalexsan.core.extensions.gone
import com.immortalalexsan.githubusers.databinding.FragmentUserMetadataBinding
import com.immortalalexsan.githubusers.presentation.models.UserMetadataModelItem
import com.squareup.picasso.Picasso
import dagger.android.support.DaggerFragment

/**
 * Фрагмент для отображения метаданных пользователя Github.
 *
 * @property viewModelProviderFactory Фабрика втю моделей.
 */
internal class UserMetadataFragment(private val viewModelProviderFactory: ViewModelProvider.Factory) :
    DaggerFragment() {

    private val args: UserMetadataFragmentArgs by navArgs()

    private val viewModel: UserMetadataViewModel by viewModels(
        factoryProducer = {
            viewModelProviderFactory
        }
    )

    private var _binding: FragmentUserMetadataBinding? = null
    private val binding: FragmentUserMetadataBinding
        get(): FragmentUserMetadataBinding = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupObservers()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentUserMetadataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getUserMetadata()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupObservers() {
        viewModel.userMetadataModelItem.observe(this) { item: UserMetadataModelItem ->
            Picasso.get()
                .load(item.avatarUrl)
                .into(binding.avatarImageView)

            with(binding) {
                loginTextView.text = item.loginFormatted
                nameTextView.text = item.nameFormatted
                companyTextView.text = item.companyFormatted
                blogTextView.text = item.blogFormatted
                locationTextView.text = item.locationFormatted
                emailTextView.text = item.emailFormatted
            }

            binding.progressBarLayout.progressBar.gone()
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

    private fun getUserMetadata() {
        viewModel.getUserMetadata(args.userLogin)
    }

    companion object {
        /**
         * Создает экземпляр этого фрагмента.
         *
         * @param viewModelProviderFactory Фабрика вью моделей.
         * @return Экземпляр этого фрагмента.
         */
        fun newInstance(viewModelProviderFactory: ViewModelProvider.Factory): UserMetadataFragment =
            UserMetadataFragment(viewModelProviderFactory)
    }
}
