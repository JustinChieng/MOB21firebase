package com.justin.mob21justin.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.justin.mob21justin.databinding.FragmentRegisterBinding
import com.justin.mob21justin.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterFragment: BaseFragment<FragmentRegisterBinding>() {
    override val viewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun setupUIComponents() {
        super.setupUIComponents()

        binding.run {
            tvSignIn.setOnClickListener {
                navController.popBackStack()
            }

            btnSignUp.setOnClickListener {
                val email = tvEmail.text.toString()
                val pass = tvPassword.text.toString()
                val pass2 = tvConfirmPassword.text.toString()

                viewModel.register(email, pass, pass2)

            }
        }
    }

    override fun setupViewModelObserver() {
        super.setupViewModelObserver()

        lifecycleScope.launch {
            viewModel.success.collect {
                val action = RegisterFragmentDirections.toHome()
                navController.navigate(action)
            }
        }
    }
}