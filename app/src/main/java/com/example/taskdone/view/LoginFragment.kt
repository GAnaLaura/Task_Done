package com.example.taskdone.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.fragment.app.viewModels
import com.example.taskdone.R
import androidx.core.widget.addTextChangedListener
import com.example.taskdone.databinding.FragmentLoginBinding
import com.example.taskdone.utils.FragmentCommunicator
import com.example.taskdone.view.MainActivity
import com.example.taskdone.viewModel.LoginViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel by viewModels<LoginViewModel>()
    var isValid: Boolean = false

    private lateinit var communicator: FragmentCommunicator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        communicator = requireActivity() as OnboardingActivity
        setupView()
        setupObservers()
        return binding.root

    }

    private fun setupView() {
        binding.registrarse.setOnClickListener {
            findNavController().navigate(R.id.action_LoginFragment_to_RegisterFragment)
        }

        binding.ingresar.setOnClickListener {
            if (isValid) {
                requestLogin()
            } else {
                Toast.makeText(activity, "Ingreso invalido", Toast.LENGTH_SHORT).show()
            }
        }

        binding.emailTextField.editText?.addTextChangedListener {
            if (binding.emailTextField.editText?.text.toString().isEmpty()) {
                binding.emailTextField.error = "Por favor introduce un correo"
                isValid = false
            } else {
                isValid = true
            }
        }
        binding.passwordTextField.editText?.addTextChangedListener {
            if (binding.passwordTextField.editText?.text.toString().isEmpty()) {
                binding.passwordTextField.error = "Por favor introduce una contraseña"
                isValid = false
            } else {
                isValid = true
            }
        }
    }

    private fun setupObservers(){
        viewModel.loaderState.observe(viewLifecycleOwner) { loaderState ->
            communicator.showLoader(loaderState)
        }
        viewModel.sessionValid.observe(viewLifecycleOwner) {validSession ->
            if (validSession) {
                val intent = Intent(activity, MainActivity::class.java)
                startActivity(intent)
                activity?.finish()
            } else  {
                Toast.makeText(activity, "Ingreso invalido", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun requestLogin() {
        viewModel.requestSignIn(binding.emailTextField.editText?.text.toString(),
            binding.passwordTextField.editText?.text.toString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}