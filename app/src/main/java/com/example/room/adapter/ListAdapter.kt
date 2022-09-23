package com.example.room.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.room.`interface`.OnItemClickListerner
import com.example.room.model.User
import com.example.room.databinding.CustomRowBinding

class ListAdapter(private var list : List<User>) : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private lateinit var listerner: OnItemClickListerner

    fun setOnItemClickListerner(listerner: OnItemClickListerner) {
        this.listerner = listerner
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addNewData(userList : List<User>) {
        list = userList
        notifyDataSetChanged()
    }

    class MyViewHolder(
        private val customRowBinding: CustomRowBinding,
        private val listerner: OnItemClickListerner
        ) : RecyclerView.ViewHolder(customRowBinding.root) {
        fun bind(list: User) {
            with(customRowBinding) {
                number.text = list.id.toString()
                firstName.text = list.firstName
                lastName.text = list.lastName
                age.text = list.age.toString()
                items.setOnClickListener {
                    listerner.onItemClick(list)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val items = CustomRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(items, listerner)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}