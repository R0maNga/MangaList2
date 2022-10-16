
package com.example.mangalist.manga_info

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.mangalist.R
import com.example.mangalist.databinding.FragmentMangaItemBinding

class MangaItemFragment: Fragment() {
    private val mangaItemViewModel =  MangaItemViewModel()

    private lateinit var binding: FragmentMangaItemBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMangaItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val id = arguments?.getInt("ID")
        mangaItemViewModel.getMangaById(id!!)
        mangaItemViewModel.liveMangaData.observe(viewLifecycleOwner) {
            try{
                Glide.with(view)
                    .load(it.data.images?.jpg?.imageUrl)
                    .placeholder(R.drawable.ic_baseline_home_24)
                    .into(binding.ivPoster)
                binding.tvTitle.text = it.data.title
                binding.tvDescription.text = it.data.synopsis

            }catch (e:Exception){
                Log.e("+", e.message.toString() )
            }
        }

    }
}