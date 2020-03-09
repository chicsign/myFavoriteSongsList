package com.lonnie.chicsign.myfavoritesongs.network.data


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.lonnie.chicsign.myfavoritesongs.model.Info

class Repo {

    fun getInfo(): LiveData<MutableList<Info>> {
        val mutableData = MutableLiveData<MutableList<Info>>()

        FirebaseDatabase.getInstance().getReference("Posts").orderByChild("writeTime")
            .addChildEventListener(
                object : ChildEventListener {
                    val posts: MutableList<Info> = mutableListOf()
                    override fun onCancelled(databaseError: DatabaseError?) {
                        databaseError?.toException()?.printStackTrace()
                    }

                    override fun onChildMoved(snapshot: DataSnapshot?, prevChildKey: String?) {
                        snapshot?.let { snapshot ->
                            val post = snapshot.getValue(Info::class.java)
                            post?.let { post ->
                                val existIndex = posts.map { it.postId }.indexOf(post.postId)

                                posts.removeAt(existIndex)

                                if (prevChildKey == null) {
                                    posts.add(post)
                                } else {
                                    val prevIndex = posts.map { it.postId }.indexOf(prevChildKey)
                                    posts.add(prevIndex + 1, post)
                                }
                                mutableData.value = posts
                            }
                        }


                    }

                    override fun onChildChanged(snapshot: DataSnapshot?, prevChildKey: String?) {
                        snapshot?.let { snapshot ->
                            val post = snapshot.getValue(Info::class.java)
                            post?.let { post ->
                                val prevIndex = posts.map { it.postId }.indexOf(prevChildKey)
                                posts[prevIndex + 1] = post
                            }
                            mutableData.value = posts
                        }
                    }

                    override fun onChildAdded(snapshot: DataSnapshot?, prevChildKey: String?) {
                        snapshot?.let { snapshot ->
                            val post = snapshot.getValue(Info::class.java)
                            post?.let {
                                if (prevChildKey == null) {
                                    posts.add(it)
                                } else {
                                    val prevIndex = posts.map { it.postId }.indexOf(prevChildKey)
                                    posts.add(prevIndex + 1, post)
                                }
                            }
                            mutableData.value = posts
                        }
                    }

                    override fun onChildRemoved(snapshot: DataSnapshot?) {

                        snapshot?.let {
                            val post = snapshot.getValue(Info::class.java)

                            post?.let { post ->
                                val existIndex = posts.map { it.postId }.indexOf(post.postId)
                                posts.removeAt(existIndex)
                            }
                            mutableData.value = posts
                        }
                    }

                }
            )


        return mutableData

    }

}