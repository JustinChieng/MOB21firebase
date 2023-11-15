package com.justin.mob21justin.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.justin.mob21justin.core.utils.NativeUtils
import com.justin.mob21justin.data.model.Todo

import com.justin.mob21justin.ui.base.BaseFragment
import com.justin.mob21justin.databinding.FragmentHomeBinding
import com.justin.mob21justin.ui.adapter.TodosAdapter
import com.justin.mob21justin.ui.tabContainer.TabContainerFragmentDirections


import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val viewModel: HomeViewModel by viewModels()

    private lateinit var adapter: TodosAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun setupViewModelObserver() {
        super.setupViewModelObserver()

        lifecycleScope.launch {
            viewModel.todos.collect {
                adapter.setTodos(it)
            }
        }
    }

    override fun setupUIComponents() {
        super.setupUIComponents()
        setupAdapter()

        binding.run {
            btnAdd.setOnClickListener {
                val action = TabContainerFragmentDirections.actionHomeToAddTodo()
                navController.navigate(action)
            }

            binding.tvNativeMsg.text = NativeUtils.greet()
            binding.tvNativeMsg2.text = NativeUtils.sayHelloNumbers().toString()
        }
    }

    override fun onFragmentResult() {
        super.onFragmentResult()

        setFragmentResultListener("from_add_or_update") {_, result ->
            val refresh = result.getBoolean("refresh")
            if(refresh) {
                viewModel.refresh()
            }
        }
    }


    private fun setupAdapter() {
        adapter = TodosAdapter(emptyList())
        adapter.listener = object : TodosAdapter.Listener {
            override fun onClick(todo: Todo) {
                val action = TabContainerFragmentDirections.actionHomeToEditTodo(todo.id)
                navController.navigate(action)
            }

            override fun onDelete(todo: Todo) {
                viewModel.delete(todo)
            }

        }
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvTodos.adapter = adapter
        binding.rvTodos.layoutManager = layoutManager
    }

}