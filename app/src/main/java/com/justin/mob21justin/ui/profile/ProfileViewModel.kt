package com.justin.mob21justin.ui.profile

import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.justin.mob21justin.core.service.AuthService
import com.justin.mob21justin.core.service.StorageService
import com.justin.mob21justin.data.model.User
import com.justin.mob21justin.data.repo.UserRepo
import com.justin.mob21justin.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authService: AuthService,
    private val storageService: StorageService,
    private val userRepo: UserRepo
) : BaseViewModel() {
    private val _user =  MutableStateFlow ( User(name = "Unknown", email = "Unknown"))
    val user: StateFlow<User> = _user

    private val _profileUri = MutableStateFlow<Uri?>(null)
    val profileUri: StateFlow<Uri?> = _profileUri

    private val _finish = MutableSharedFlow<Unit>()
    val finish : SharedFlow<Unit> = _finish

    init {
        getCurrentUser()
        getProfilePicUri()
    }

    fun logout() {
        authService.logout()
        viewModelScope.launch{
            _finish.emit(Unit)
        }
    }

    fun updateProfilePic(uri: Uri) {
        user.value.id?.let{
            viewModelScope.launch (Dispatchers.IO) {
                val name = "$it.jpg"
                storageService.addImage(name,uri)
                getProfilePicUri()
            }
        }
    }

    fun getProfilePicUri() {
        viewModelScope.launch (Dispatchers.IO) {
            authService.getCurrentUser()?.uid.let{
                _profileUri.value = storageService.getImage("$it.jpg")
            }

        }
    }

    fun getCurrentUser() {
        val firebaseUser = authService.getCurrentUser()
        firebaseUser?.let{
            viewModelScope.launch(Dispatchers.IO) {
                safeApiCall { userRepo.getUser(it.uid) }?.let{user ->
                    _user.value = user
                }
            }
        }
    }
}