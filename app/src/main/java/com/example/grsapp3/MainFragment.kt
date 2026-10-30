package com.example.grsapp3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.grsapp3.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.submitButton.setOnClickListener {
            val name = binding.nameEditText.text.toString().trim()
            val password = binding.passwordEditText.text.toString().trim()

            if (name.isEmpty()) {
                binding.nameInputLayout.error = getString(R.string.error_name)
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                binding.passwordInputLayout.error = getString(R.string.error_password)
                binding.nameInputLayout.error = null
                return@setOnClickListener
            }

            binding.nameInputLayout.error = null
            binding.passwordInputLayout.error = null

            Toast.makeText(
                requireContext(),
                getString(R.string.result_format, name),
                Toast.LENGTH_SHORT
            ).show()

            findNavController().navigate(
                R.id.action_mainFragment_to_welcomeFragment,
                bundleOf("userName" to name)
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}