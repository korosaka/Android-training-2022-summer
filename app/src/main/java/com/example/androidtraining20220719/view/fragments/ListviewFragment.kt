package com.example.androidtraining20220719.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.example.androidtraining20220719.R
import com.example.androidtraining20220719.databinding.FragmentListviewBinding
import com.example.androidtraining20220719.view.adapter.ListViewAdapter
import com.example.androidtraining20220719.view_model.TopPageViewModel
import kotlinx.android.synthetic.main.fragment_listview.*


class ListviewFragment : Fragment() {

    private lateinit var adapter: ListViewAdapter
    private val viewModel: TopPageViewModel by activityViewModels()
    private lateinit var binding: FragmentListviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ListViewAdapter(requireContext(), viewModel.characters.value!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_listview, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModelOnListViewFrag = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        characters_on_list_view.adapter = adapter
        viewModel.characters.observe(viewLifecycleOwner) {
            adapter.notifyDataSetChanged()
        }
    }
}