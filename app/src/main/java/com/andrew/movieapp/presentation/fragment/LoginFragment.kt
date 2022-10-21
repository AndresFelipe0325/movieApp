package com.andrew.movieapp.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.andrew.movieapp.R
import com.andrew.movieapp.databinding.FragmentLoginBinding
import com.andrew.movieapp.presentation.activity.MainActivity
import com.andrew.movieapp.viewmodel.LoginViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_login.*

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels()
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        setupView()
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun setupView() {
        binding.loginVM = viewModel
        binding.btnLogin.setOnClickListener{
            viewModel.email.value = binding.etUser.text.toString()
            viewModel.password.value = binding.etPassword.text.toString()
            viewModel.logIn()
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            isLoading?.let {
                pb_loading.visibility = if (isLoading) View.VISIBLE else View.GONE
            }
        }

        viewModel.authError.observe(viewLifecycleOwner) { authError ->
            authError?.let {
                tv_error.visibility = if (authError) View.VISIBLE else View.GONE
            }
        }

        viewModel.authSuccess.observe(viewLifecycleOwner) { authSuccess ->
            authSuccess?.let {
                if (authSuccess) {
                    findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToPopularMoviesFragment())
                }
            }

        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() {

        }
    }
}