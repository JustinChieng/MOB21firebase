package com.justin.mob21justin.ui.addEditTodo.viewModel

import androidx.lifecycle.viewModelScope
import com.justin.mob21justin.data.model.Todo
import com.justin.mob21justin.data.repo.TodosRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddTodoViewModel @Inject constructor(
    private val repo: TodosRepo
): BaseAddEditTodoViewModel() {

    override fun submit (title: String, desc: String) {
        val todo = Todo(title = title, desc = desc)
        viewModelScope.launch(Dispatchers.IO) {
            safeApiCall { repo.insert(todo)  }
            _finish.emit(Unit)
        }
    }
}