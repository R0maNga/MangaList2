package com.example.mangalist.search_screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mangalist.R
import com.example.mangalist.databinding.FragmentSearchBinding
import com.example.mangalist.main_screen.HomeAdapter
import com.example.mangalist.manga_info.MangaItemViewModel
import kotlin.math.log

class searchFragment : Fragment() {

    private val searchViewModel = SearchViewModel()
    private lateinit var binding: FragmentSearchBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        binding.buttonSearch.setOnClickListener {
            val inputText = binding.editText.text.toString()
            searchViewModel.getMangaByName(inputText)
        }
        searchViewModel.liveMangaData.observe(viewLifecycleOwner) {
            val adapter = SearchAdapter(it.mangaList)
            binding.rvManga.layoutManager = GridLayoutManager(context, 2)
            binding.rvManga.adapter = adapter
        }
    }


}