package com.example.androidtraining20220719.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidtraining20220719.R
import com.example.androidtraining20220719.model.CharacterHeaderData
import com.example.androidtraining20220719.model.MockData
import com.example.androidtraining20220719.view.adapter.ListViewAdapter
import kotlinx.android.synthetic.main.fragment_listview.*


class ListviewFragment : Fragment() {

    lateinit var characters: List<CharacterHeaderData>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
        context?.let { characters = MockData(it).getCharactersData() }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_listview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context?.let {
            val adapter = ListViewAdapter(it, characters)
            characters_on_list_view.adapter = adapter
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListviewFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}