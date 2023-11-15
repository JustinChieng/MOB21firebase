package com.justin.mob21justin.ui.addEditTodo

import androidx.fragment.app.viewModels
import com.justin.mob21justin.ui.addEditTodo.viewModel.AddTodoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTodoFragment:BaseAddEditTodoFragment() {
    override val viewModel: AddTodoViewModel by viewModels()

}