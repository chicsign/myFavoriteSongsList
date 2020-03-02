package com.lonnie.chicsign.myfavoritesongs.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.size
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.lonnie.chicsign.myfavoritesongs.R
import com.lonnie.chicsign.myfavoritesongs.model.Post
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_song_list.view.*

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    val posts: MutableList<Post> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        floatingActionButton.setOnClickListener {
            val intent = Intent(this, WriteActivity::class.java)
            startActivity(intent)
        }

        val layoutManager = LinearLayoutManager(this)
        layoutManager.reverseLayout = true
        layoutManager.stackFromEnd = true

        songList.layoutManager = layoutManager
        songList.adapter = SongListAdapter()

        FirebaseDatabase.getInstance().getReference("Posts").orderByChild("writeTime")
            .addChildEventListener(
                object : ChildEventListener {
                    override fun onCancelled(databaseError: DatabaseError?) {
                        databaseError?.toException()?.printStackTrace()
                    }

                    override fun onChildMoved(snapshot: DataSnapshot?, prevChildKey: String?) {
                        snapshot?.let { snapshot ->
                            val post = snapshot.getValue(Post::class.java)
                            post?.let { post ->
                                val existIndex = posts.map { it.postId }.indexOf(post.postId)

                                posts.removeAt(existIndex)
                                songList.adapter?.notifyItemRemoved(existIndex)

                                if (prevChildKey == null) {
                                    posts.add(post)
                                    songList.adapter?.notifyItemChanged(posts.size - 1)
                                } else {
                                    val prevIndex = posts.map { it.postId }.indexOf(prevChildKey)
                                    posts.add(prevIndex + 1, post)
                                    songList.adapter?.notifyItemChanged(prevIndex + 1)
                                }
                            }
                        }


                    }

                    override fun onChildChanged(snapshot: DataSnapshot?, prevChildKey: String?) {
                        snapshot?.let { snapshot ->
                            val post = snapshot.getValue(Post::class.java)
                            post?.let { post ->
                                val prevIndex = posts.map { it.postId }.indexOf(prevChildKey)
                                posts[prevIndex + 1] = post
                                songList.adapter?.notifyItemChanged(prevIndex + 1)
                            }
                        }
                    }

                    override fun onChildAdded(snapshot: DataSnapshot?, prevChildKey: String?) {
                        snapshot?.let { snapshot ->
                            val post = snapshot.getValue(Post::class.java)
                            post?.let {
                                if (prevChildKey == null) {
                                    posts.add(it)
                                    songList.adapter?.notifyItemInserted(posts.size - 1)
                                } else {
                                    val prevIndex = posts.map { it.postId }.indexOf(prevChildKey)
                                    posts.add(prevIndex + 1, post)
                                    songList.adapter?.notifyItemInserted(prevIndex + 1)
                                }
                            }
                        }
                    }

                    override fun onChildRemoved(snapshot: DataSnapshot?) {

                        snapshot?.let {
                            val post = snapshot.getValue(Post::class.java)

                            post?.let { post ->
                                val existIndex = posts.map { it.postId }.indexOf(post.postId)
                                posts.removeAt(existIndex)
                                songList.adapter?.notifyItemRemoved(existIndex)
                            }
                        }
                    }
                })
    }

    inner class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val songNumberTxt = itemView.songNumber

        val songTitleTxt = itemView.songTitle

        val singerTxt = itemView.singer

        val songWriterTxt = itemView.songWriter

        val lyricWriterTxt = itemView.lyricWriter

        val companyTxt = itemView.company

    }

    inner class SongListAdapter : RecyclerView.Adapter<myViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
            return myViewHolder(
                LayoutInflater.from(this@MainActivity).inflate(
                    R.layout.item_song_list,
                    parent,
                    false
                )
            )
        }

        override fun getItemCount(): Int {
            return posts.size
        }

        override fun onBindViewHolder(holder: myViewHolder, position: Int) {
            val post = posts[position]

            holder.songNumberTxt.text = post.songNumber
            holder.songTitleTxt.text = post.songTitle
            holder.singerTxt.text = post.singer
            holder.songWriterTxt.text = post.songWriter
            holder.lyricWriterTxt.text = post.lyricWriter
            holder.companyTxt.text = post.company

        }

    }
}

