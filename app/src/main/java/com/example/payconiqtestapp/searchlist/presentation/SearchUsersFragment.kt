package com.example.payconiqtestapp.searchlist.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.payconiqtestapp.R
import com.example.payconiqtestapp.core.ProvidersHolder
import com.example.payconiqtestapp.databinding.FragmentFirstBinding
import com.example.payconiqtestapp.searchlist.di.SearchUsersComponent
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class SearchUsersFragment : Fragment() {

    @Inject
    lateinit var coreViewModelFactory: AbstractSavedStateViewModelFactory
    private val viewModel by viewModels<SearchUsersViewModel> { coreViewModelFactory }

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        observeViewModelEvents()
    }

    private fun observeViewModelEvents() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.uiState.collect {
                        Log.e("DD", "get $it")
                        // todo handle data
                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val accumulator =
            (requireActivity().application as ProvidersHolder).getProvidersAccumulator()
        SearchUsersComponent.create(accumulator, this)
            .inject(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}