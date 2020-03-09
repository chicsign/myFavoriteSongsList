package com.lonnie.chicsign.myfavoritesongs.model

import android.view.View
import android.widget.Toast

data class Info(
    val postId: String = "",
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
        Toast.makeText(view.context, "Clicked: $songTitle", Toast.LENGTH_SHORT).show()
    }
}