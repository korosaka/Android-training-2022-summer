package com.example.androidtraining20220719.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidtraining20220719.R
import com.example.androidtraining20220719.model.CharacterHeaderData
import com.example.androidtraining20220719.model.MockData
import com.example.androidtraining20220719.view.adapter.RecyclerAdapter
import kotlinx.android.synthetic.main.fragment_recycler_view.*


class RecyclerViewFragment : Fragment() {

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recycler_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context?.let {
//            characters_recycler.layoutManager =
//                LinearLayoutManager(it, LinearLayoutManager.VERTICAL, false)
            characters_recycler.layoutManager = GridLayoutManager(it, 2)
            val adapter = RecyclerAdapter(characters)
            characters_recycler.adapter = adapter
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RecyclerViewFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}