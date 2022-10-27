package com.example.room

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.room.databinding.FragmentUpdateBinding
import com.example.room.model.User
import com.example.room.viewmodel.UserViewModel


class UpdateFragment : Fragment() {
    private lateinit var updateBinding : FragmentUpdateBinding
    private val userViewModel : UserViewModel by viewModels()
    private val args by navArgs<UpdateFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding()
        initEvents()
        initObserve()
    }

    private fun initObserve() {
        userViewModel.updateData.observe(viewLifecycleOwner) {result ->
            when (result) {
                is UserViewModel.UpdateData.ResultOk -> {
                    Toast.makeText(context, result.successMessage, Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_updateFragment_to_listFragment)
                }
                is UserViewModel.UpdateData.ResultError -> {
                    Toast.makeText(context, result.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initEvents() {
        updateUser()
    }

    private fun updateUser() {
        updateBinding.update.setOnClickListener {
            val firstName = updateBinding.firstName.text.toString()
            val lastName = updateBinding.lastName.text.toString()
            val age = Integer.parseInt(updateBinding.age.text.toString())
            if (inputCheck(firstName, lastName, updateBinding.age.text)) {
                val updateUser = User(args.currentUser.id, firstName, lastName, age)
                userViewModel.updateUser(updateUser)
            }
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

    private fun binding() {
        with(updateBinding) {
            firstName.setText(args.currentUser.firstName)
            lastName.setText(args.currentUser.lastName)
            age.setText(args.currentUser.age.toString())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        updateBinding = FragmentUpdateBinding.inflate(inflater, container, false)
        return updateBinding.root
    }
}