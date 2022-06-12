package com.example.payconiqtestapp.searchlist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.payconiqtestapp.R
import com.example.payconiqtestapp.core.ProvidersHolder
import com.example.payconiqtestapp.databinding.FragmentSearchUserBinding
import com.example.payconiqtestapp.searchlist.di.SearchUsersComponent
import com.example.payconiqtestapp.searchlist.presentation.adapter.UserAdapter
import com.example.payconiqtestapp.searchlist.presentation.model.ViewModelState
import com.example.payconiqtestapp.searchlist.presentation.model.ViewState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchUsersFragment : Fragment() {

    @Inject
    lateinit var coreViewModelFactory: AbstractSavedStateViewModelFactory
    private val viewModel by viewModels<SearchUsersViewModel> { coreViewModelFactory }

    private var adapter: UserAdapter? = null

    private var _binding: FragmentSearchUserBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = UserAdapter(requireContext())
        binding.usersRecycler.adapter = adapter
        binding.searchQuery.doAfterTextChanged { text ->
            viewModel.setQuery(text?.toString() ?: "")
        }
        adapter?.addLoadStateListener { state ->
            val viewState = ViewState(
                binding.searchQuery.text.toString(),
                state.refresh,
                adapter?.itemCount ?: 0
            )
            viewModel.onViewStateChanged(viewState)
        }
        binding.searchQuery.requestFocus()
        observeOnLifecycle()
//        binding.buttonFirst.setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }
    }


    private fun observeOnLifecycle() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.pagerState.collectLatest { adapter?.submitData(it) }
                }
                launch {
                    viewModel.viewModelState.collectLatest { handleViewModelState(it) }
                }
            }
        }
    }

    private fun handleViewModelState(viewModelState: ViewModelState) {
        when (viewModelState) {
            is ViewModelState.ErrorState -> {
                binding.usersRecycler.isVisible = false
                binding.infoView.isVisible = true
                binding.infoView.text = getText(R.string.error_happened)
            }
            ViewModelState.EnterNameState -> {
                binding.usersRecycler.isVisible = true
                binding.infoView.isVisible = true
                binding.infoView.text = getText(R.string.enter_name)
            }
            ViewModelState.NotFoundState -> {
                binding.usersRecycler.isVisible = true
                binding.infoView.isVisible = true
                binding.infoView.text = getText(R.string.nothing_found)
            }
            ViewModelState.LoadingState -> {
                binding.usersRecycler.isVisible = true
                binding.infoView.isVisible = true
                binding.infoView.text = getText(R.string.loading)
            }
            ViewModelState.LoadedState -> {
                binding.usersRecycler.isVisible = true
                binding.infoView.isVisible = false
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