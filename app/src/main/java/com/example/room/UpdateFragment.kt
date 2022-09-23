package com.example.room

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.room.databinding.FragmentUpdateBinding


class UpdateFragment : Fragment() {
    private lateinit var updateBinding : FragmentUpdateBinding
    private val args by navArgs<UpdateFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding()
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