package com.example.roomdb.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdb.R
import com.example.roomdb.data.User
import kotlinx.android.synthetic.main.custom_layout.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
private var userList = emptyList<User>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var name = itemView.findViewById<TextView>(R.id.name_tv)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_layout,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.itemView.id_tv.text= currentItem.id.toString()
        holder.name.text= currentItem.name
        holder.itemView.state_tv.text= currentItem.state.toString()
        holder.itemView.mobile_tv.text= currentItem.mobileNo.toString()

        holder.itemView.rowlayout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
         return userList.size
    }

    fun setData(user:List<User>){
        this.userList = user
        notifyDataSetChanged()
    }


}