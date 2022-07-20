package com.example.androidtraining20220719.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.androidtraining20220719.R
import com.example.androidtraining20220719.model.CharacterHeaderData
import kotlinx.android.synthetic.main.character_list_view_item.view.*

class ListViewAdapter(
    private val myContext: Context,
    private val characterList: List<CharacterHeaderData>
) : ArrayAdapter<CharacterHeaderData>(myContext, 0, characterList) {

    private val layoutInflater = LayoutInflater.from(myContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view =
            convertView ?: layoutInflater.inflate(R.layout.character_list_view_item, parent, false)

        val characterData = characterList[position]
        view.character_list_name.text = characterData.name
        characterData.image?.apply {
            view.character_list_iv.setImageDrawable(this)
        }

        return view
    }


}