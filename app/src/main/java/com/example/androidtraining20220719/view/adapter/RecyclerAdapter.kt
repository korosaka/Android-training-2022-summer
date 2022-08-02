package com.example.androidtraining20220719.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtraining20220719.databinding.CharacterRecyclerViewItemBinding
import com.example.androidtraining20220719.model.CharacterHeaderData

class RecyclerAdapter(private val characters: List<CharacterHeaderData>) :
    RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: CharacterRecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    var listener: RecyclerAdapterInterface? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MyViewHolder(
            CharacterRecyclerViewItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val characterData = characters[position]
        holder.binding.characterDataOnRecycler = characterData
        holder.itemView.setOnClickListener {
            listener?.onClickItemView(characterData.id)
        }

    }

    override fun getItemCount(): Int {
        return characters.size
    }

    interface RecyclerAdapterInterface {
        fun onClickItemView(characterId: String)
    }
}