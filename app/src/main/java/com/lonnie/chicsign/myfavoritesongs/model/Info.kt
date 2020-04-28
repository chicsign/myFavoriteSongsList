package com.lonnie.chicsign.myfavoritesongs.model

import android.view.View
import android.widget.Toast
import com.google.firebase.database.*

data class Info(
    var postId: String = "",

    var writeId: String = "",

    var songNumber: String = "",

    var songTitle: String = "",

    var singer: String = "",

    var songWriter: String = "",

    var lyricWriter: String = "",

    var company: String = "",

    var writeTime: Any = Any()
) {
    fun onClickListener(view: View) {
        //TODO: 추후 다른 곳으로 이동할 예정
        Toast.makeText(view.context, "Clicked: $songTitle", Toast.LENGTH_SHORT).show()
        val ref = FirebaseDatabase.getInstance().reference
        val deleteQuery: Query = ref.child("Posts").orderByChild("postId").equalTo("$postId")

        deleteQuery.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snapshot in dataSnapshot.children) {
                    snapshot.ref.removeValue()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
//                Log.e(TAG, "onCancelled", databaseError.toException())
            }
        })
    }
}