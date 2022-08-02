package com.example.androidtraining20220719.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.example.androidtraining20220719.R
import com.example.androidtraining20220719.databinding.CharacterListViewItemBinding
import com.example.androidtraining20220719.model.CharacterHeaderData
import kotlinx.android.synthetic.main.character_list_view_item.view.*

class ListViewAdapter(
    private val myContext: Context,
    private val characterList: List<CharacterHeaderData>
) : ArrayAdapter<CharacterHeaderData>(myContext, 0, characterList) {

    private val layoutInflater = LayoutInflater.from(myContext)
    private lateinit var binding: CharacterListViewItemBinding

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        binding =
            if (convertView == null) {
                val itemBinding: CharacterListViewItemBinding =
                    DataBindingUtil.inflate(layoutInflater, R.layout.character_list_view_item, parent, false)
                itemBinding.root.tag = itemBinding
                itemBinding
            } else {
                // when convertView is not null, the view was recycled and binding object is saved in tha tag
                convertView.tag as CharacterListViewItemBinding
            }

        binding.characterData = characterList[position]

        return  binding.root
    }


}