package com.example.mangalist.read_manga_item

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mangalist.R
import com.example.mangalist.databinding.FragmentMangaItemBinding
import com.example.mangalist.databinding.FragmentMyMangaBinding
import com.example.mangalist.databinding.FragmentReadMangaBinding
import com.example.mangalist.databinding.FragmentReadMangaItemBinding
import com.example.mangalist.model.Chapter
import com.example.mangalist.model.MyManga


class ReadMangaItemFragment : Fragment() {

    private val chapterList = mutableListOf<Chapter>()
    private lateinit var binding: FragmentReadMangaItemBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReadMangaItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var i = 1
        chapterList.clear()
        resources.getStringArray(R.array.rv_array).forEach {

            chapterList.add(Chapter(i, it))
            i++
        }

        binding.rvReadManga.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvReadManga.adapter = ReadMangaItemAdapter(chapterList)

    }

}