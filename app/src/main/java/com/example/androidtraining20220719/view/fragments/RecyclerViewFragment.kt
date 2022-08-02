package com.example.androidtraining20220719.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.androidtraining20220719.R
import com.example.androidtraining20220719.databinding.FragmentRecyclerViewBinding
import com.example.androidtraining20220719.view.adapter.RecyclerAdapter
import com.example.androidtraining20220719.view_model.TopPageViewModel
import kotlinx.android.synthetic.main.fragment_recycler_view.*


class RecyclerViewFragment :
    Fragment(),
    RecyclerAdapter.RecyclerAdapterInterface {

    private val viewModel: TopPageViewModel by activityViewModels()
    private lateinit var adapter: RecyclerAdapter
    private lateinit var binding: FragmentRecyclerViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = RecyclerAdapter(viewModel.characters.value!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_recycler_view, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModelOnRecyclerViewFrag = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context?.let {
//            characters_recycler.layoutManager =
//                LinearLayoutManager(it, LinearLayoutManager.VERTICAL, false)
            characters_recycler.layoutManager = GridLayoutManager(it, 2)
            adapter.listener = this
            characters_recycler.adapter = adapter
            viewModel.characters.observe(viewLifecycleOwner) {
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun onClickItemView(characterId: String) {
        Toast.makeText(context, "$characterId was tapped!", Toast.LENGTH_SHORT).show()
    }
}