package com.lonnie.chicsign.myfavoritesongs.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.firebase.database.*
import com.lonnie.chicsign.myfavoritesongs.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import com.lonnie.chicsign.myfavoritesongs.R
import com.lonnie.chicsign.myfavoritesongs.data.Post

val posts = ArrayList<Post>()

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
        floatingActionButton.setOnClickListener {
            val intent = Intent(this, WriteActivity::class.java)
            startActivity(intent)
        }



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
                                recyclerView.adapter?.notifyItemRemoved(existIndex)

                                if (prevChildKey == null) {
                                    posts.add(post)
                                    recyclerView.adapter?.notifyItemChanged(posts.size - 1)
                                } else {
                                    val prevIndex = posts.map { it.postId }.indexOf(prevChildKey)
                                    posts.add(prevIndex + 1, post)
                                    recyclerView.adapter?.notifyItemChanged(prevIndex + 1)
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
                                recyclerView.adapter?.notifyItemChanged(prevIndex + 1)
                            }
                        }
                    }

                    override fun onChildAdded(snapshot: DataSnapshot?, prevChildKey: String?) {
                        snapshot?.let { snapshot ->
                            val post = snapshot.getValue(Post::class.java)
                            post?.let {
                                if (prevChildKey == null) {
                                    posts.add(it)
                                    recyclerView.adapter?.notifyItemInserted(posts.size - 1)
                                } else {
                                    val prevIndex = posts.map { it.postId }.indexOf(prevChildKey)
                                    posts.add(prevIndex + 1, post)
                                    recyclerView.adapter?.notifyItemInserted(prevIndex + 1)
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
                                recyclerView.adapter?.notifyItemRemoved(existIndex)
                            }
                        }
                    }
                })

        val adapter = RecyclerAdapter(posts)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )
    }
}

