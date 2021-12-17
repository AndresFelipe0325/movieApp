package com.andrew.movieapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.andrew.movieapp.R
import com.andrew.movieapp.databinding.ActivityLoginBinding
import com.andrew.movieapp.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
    }

    private fun setupView(){
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.loginVM = viewModel
        binding.btnLogin.setOnClickListener{
            viewModel.email.value = binding.etUser.text.toString()
            viewModel.password.value = binding.etPassword.text.toString()
            viewModel.logIn()
        }

        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.loading.observe(this, { isLoading ->
            isLoading?.let{
                pb_loading.visibility = if(isLoading) View.VISIBLE else View.GONE
            }
        })

        viewModel.authError.observe(this, { authError ->
            authError?.let{
                tv_error.visibility = if(authError) View.VISIBLE else View.GONE
            }
        })
    }
}