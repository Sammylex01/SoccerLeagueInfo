package com.samoye.soccerleagueinfo.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.samoye.soccerleagueinfo.R
import com.samoye.soccerleagueinfo.data.model.Competition

val textViewLeagueName: TextView = findViewById(R.id.textViewLeagueName)
val textViewCountry: TextView = findViewById(R.id.textViewCountry)
val textViewStartDate: TextView = findViewById(R.id.textViewStartDate)

class MainAdapter (private val users: ArrayList<Competition>) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(user: Competition) {
            itemView.apply {
                textViewLeagueName.text = user.name
                textViewCountry.text = user.countryName
                textViewStartDate.text = user.startDate
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false))

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(users[position])
    }

    fun addUsers(users: List<Competition>) {
        this.users.apply {
            clear()
            addAll(users)
        }

    }
}