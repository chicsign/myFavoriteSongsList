package com.lonnie.chicsign.myfavoritesongs.data

import android.view.View
import android.widget.Toast

class Post {

    var postId = ""

    var writeId = ""

    var songNumber = ""

    var songTitle = ""

    var singer = ""

    var songWriter = ""

    var lyricWriter = ""

    var company = ""

    var writeTime: Any = Any()

    fun onClickListener(view: View) {
        Toast.makeText(view.context, "Clicked: $songTitle", Toast.LENGTH_SHORT).show()
    }

}
