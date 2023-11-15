package com.justin.mob21justin.ui.login

import androidx.lifecycle.viewModelScope
import com.justin.mob21justin.core.service.AuthService
import com.justin.mob21justin.data.repo.TodosRepo
import com.justin.mob21justin.data.repo.UserRepo

import com.justin.mob21justin.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authService: AuthService,
    private val userRepo: UserRepo,
    private val todosRepo: TodosRepo
): BaseViewModel() {
    private  val _greet = MutableStateFlow("")
    val greet: StateFlow<String> = _greet

    init {
        viewModelScope.launch(Dispatchers.IO) {
            safeApiCall { todosRepo.greet() }?.let {
                _greet.value = it
            }
        }
    }

    fun login(email: String, pass: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val res = safeApiCall { authService.login(email, pass) }

            if(res == null) {
                _error.emit("Email or password is wrong")
            } else {
                _success.emit("Login successful")
            }
        }
    }


}