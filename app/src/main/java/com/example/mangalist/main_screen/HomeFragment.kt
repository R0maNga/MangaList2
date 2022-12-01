package com.example.mangalist.main_screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.mangalist.MainActivity
import com.example.mangalist.R
import com.example.mangalist.databinding.FragmentHomeBinding
import com.example.mangalist.databinding.FragmentMyMangaBinding

class HomeFragment : Fragment() {
    private val homeViewModel = HomeViewModel()

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel.getManga()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

//        (activity as? MainActivity)?.
//
        homeViewModel.progressBarLiveData.observe(viewLifecycleOwner) {
            if (it == true) binding.progressBar.visibility = View.VISIBLE
            else binding.progressBar.visibility = View.GONE
        }

        binding.swipe.setOnRefreshListener {
            refresh()
        }
        adapter()


    }
    private fun adapter()
    {
        homeViewModel.liveMangaData.observe(viewLifecycleOwner) {
            val adapter = HomeAdapter(it.mangaList)
            val linearLayoutManager = GridLayoutManager(context, 2)

            binding.rvManga.layoutManager = GridLayoutManager(context, 2)
            binding.rvManga.adapter = adapter
        }
    }
    private fun refresh(){
        homeViewModel.getManga()
        binding.swipe.isRefreshing = false
    }

}