package com.example.mangalist.my_manga

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mangalist.R
import com.example.mangalist.databinding.FragmentMyMangaBinding
import com.example.mangalist.model.MyManga
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MyMangaFragment : Fragment() {

    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private lateinit var adapter: MyMangaAdapter
    val mangaList = mutableListOf<MyManga>()

    private lateinit var binding: FragmentMyMangaBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyMangaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        auth = FirebaseAuth.getInstance()
        database = Firebase.database.reference

        val myMangaList = mutableListOf<MyManga>()
        val user = auth.currentUser?.email.toString().substringBefore("@")
        fun deleteData(id: Int) {
            database = FirebaseDatabase.getInstance().reference
            database.child(user).child(id.toString()).removeValue()
        }
        database.child(auth.currentUser?.email.toString().substringBefore("@")).get()
            .addOnSuccessListener { user ->
                user.children.forEach { manga ->
                    val id = manga.child("id").value
                    val image = manga.child("images").value
                    val score = manga.child("rank").value
                    val title = manga.child("title").value
                    val status = manga.child("watchStatus").value
                    val ratingUser = manga.child("userRating").value

                    mangaList.add(
                        MyManga(
                            id.toString().toInt(),
                            image.toString(),
                            score.toString(),
                            title.toString(),
                            status.toString(),
                            ratingUser.toString()
                        )
                    )
                }

                val swipeToDeleteCallback = object : SwipeTodeleteCallback() {
                    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                        val position = viewHolder.adapterPosition
                        val item = adapter.getItemInPosition(position)
                        adapter.notifyItemRemoved(position)
                        mangaList.remove(item)
                        deleteData(item.id)
//                        val sad = mangaList.removeAt(position)
//                        val idDeleted = sad.id
//                        binding.rvMyManga.adapter?.notifyItemRemoved(position)
//                        var id = binding.rvMyManga.id
//                        deleteData(idDeleted)
                    }
                }

                val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
                itemTouchHelper.attachToRecyclerView(binding.rvMyManga)

                binding.toggleGroup.addOnButtonCheckedListener { _, checkedId, isChecked ->
                    if (isChecked) {
                        when (checkedId) {
                            R.id.reading -> {
                                val list = mangaList.filter { it.watchStatus == "0" }
                                if (list.isEmpty()) setDataToAdapter(emptyList())
                                else setDataToAdapter(list)
                            }
                            R.id.finished -> {
                                val list = mangaList.filter { it.watchStatus == "1" }
                                if (list.isEmpty()) setDataToAdapter(emptyList())
                                else setDataToAdapter(list)
                            }
                            R.id.dropped -> {
                                val list = mangaList.filter { it.watchStatus == "2" }
                                if (list.isEmpty()) setDataToAdapter(emptyList())
                                else setDataToAdapter(list)
                            }
                            R.id.inPlan -> {
                                val list = mangaList.filter { it.watchStatus == "3" }
                                if (list.isEmpty()) setDataToAdapter(emptyList())
                                else setDataToAdapter(list)
                            }
                        }
                    }
                }
            }


    }

    private fun setDataToAdapter(list: List<MyManga>) {
        adapter = MyMangaAdapter(list.toMutableList())
        binding.rvMyManga.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvMyManga.adapter = adapter
    }

    override fun onStop() {
        super.onStop()
        mangaList.clear()
    }


}