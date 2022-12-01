package com.example.mangalist.read_manga

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import com.bumptech.glide.Glide
import com.example.mangalist.R
import com.example.mangalist.databinding.DrawerListItemBinding.inflate
import com.example.mangalist.databinding.FragmentMangaItemBinding
import com.example.mangalist.databinding.FragmentReadMangaBinding
import com.example.mangalist.model.Manga


class ReadMangaFragment : Fragment() {
    private lateinit var binding: FragmentReadMangaBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReadMangaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.Chainsawman.setOnClickListener {
            findNavController(binding.root)
                .navigate(R.id.action_readMangaFragment_to_readMangaItemFragment32)
        }
    }


}