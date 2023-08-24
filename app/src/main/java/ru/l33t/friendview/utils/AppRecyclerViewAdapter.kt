package ru.l33t.friendview.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.l33t.friendview.R
import ru.l33t.friendview.models.User

class AppRecyclerViewAdapter(private val friendsList: List<User>) :
    RecyclerView.Adapter<AppRecyclerViewAdapter.ItemHolder>() {
    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val friendName: TextView = itemView.findViewById(R.id.friend_name)
        fun bind(friend: User) {
            friendName.text = friend.username
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.friend_item, parent, false)
        return ItemHolder(view)
    }

    override fun getItemCount(): Int = friendsList.size

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val friend = friendsList[position]
        holder.bind(friend)
    }


}