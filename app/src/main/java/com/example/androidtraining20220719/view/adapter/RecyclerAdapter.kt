package com.example.androidtraining20220719.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtraining20220719.R
import com.example.androidtraining20220719.model.CharacterHeaderData
import kotlinx.android.synthetic.main.character_recycler_view_item.view.*

class RecyclerAdapter(private val characters: List<CharacterHeaderData>) :
    RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.character_recycler_view_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val characterData = characters[position]

        holder.itemView.recycler_character_tv.text = characterData.name
        holder.itemView.recycler_character_iv.setImageDrawable(characterData.image)
    }

    override fun getItemCount(): Int {
        return characters.size
    }
}