package com.example.room.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.room.R
import com.example.room.`interface`.OnItemClickListerner
import com.example.room.adapter.ListAdapter
import com.example.room.viewmodel.UserViewModel
import com.example.room.databinding.FragmentListBinding
import com.example.room.model.User

class ListFragment : Fragment() {

//    private lateinit var listBinding : FragmentListBinding
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private lateinit var listAdapter: ListAdapter
    private val userViewModel : UserViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
        initList()
        initObserve()
    }

    private fun initObserve() {
        userViewModel.readAllData.observe(viewLifecycleOwner) {result ->
            listAdapter.addNewData(result)
        }
    }

    private fun initList() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            listAdapter = ListAdapter(arrayListOf())
            adapter = listAdapter

            listAdapter.setOnItemClickListerner(object :
            OnItemClickListerner {
                override fun onItemClick(data: User) {
                    val action = ListFragmentDirections.actionListFragmentToUpdateFragment(data)
                    findNavController().navigate(action)
                }
            })
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}