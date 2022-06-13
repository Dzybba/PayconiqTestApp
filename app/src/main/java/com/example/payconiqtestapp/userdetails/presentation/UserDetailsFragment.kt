package com.example.payconiqtestapp.userdetails.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.payconiqtestapp.core.ProvidersHolder
import com.example.payconiqtestapp.databinding.FragmentUserDetailsBinding
import com.example.payconiqtestapp.userdetails.di.UserDetailsComponent
import com.example.payconiqtestapp.userdetails.presentation.model.ViewModelState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserDetailsFragment : Fragment() {

    private var _binding: FragmentUserDetailsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var coreViewModelFactory: AbstractSavedStateViewModelFactory
    private val viewModel by viewModels<UserDetailsViewModel> { coreViewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModelEvents()
    }

    private fun observeViewModelEvents() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.uiState.collect { handleViewModelState(it) }
                }
            }
        }
    }

    private fun handleViewModelState(state: ViewModelState) {
        when (state) {
            is ViewModelState.Error -> {
            }
            is ViewModelState.Loaded -> {
                binding.userDetails.populate(state.user)
            }
            is ViewModelState.Loading -> {
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val accumulator =
            (requireActivity().application as ProvidersHolder).getProvidersAccumulator()
        val login = UserDetailsFragmentContract.getLoginFromBundle(requireArguments())
        UserDetailsComponent.create(accumulator, login, this)
            .inject(this)
    }
}