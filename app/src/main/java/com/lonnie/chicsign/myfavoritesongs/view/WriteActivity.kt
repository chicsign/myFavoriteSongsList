package com.lonnie.chicsign.myfavoritesongs.view

import android.os.Bundle
import android.provider.Settings
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ServerValue
import com.lonnie.chicsign.myfavoritesongs.R
import kotlinx.android.synthetic.main.activity_write.*

class WriteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write)

        btnSave.setOnClickListener {
            if (TextUtils.isEmpty(edtSongNumber.text)) {
                Toast.makeText(applicationContext, "노래의 번호를 입력하세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (TextUtils.isEmpty(edtSongTitle.text)) {
                Toast.makeText(applicationContext, "노래의 제목을 입력하세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (TextUtils.isEmpty(edtSinger.text)) {
                Toast.makeText(applicationContext, "노래의 가수를 입력하세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (TextUtils.isEmpty(edtSongWriter.text)) {
                Toast.makeText(applicationContext, "노래의 작곡가를 입력하세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (TextUtils.isEmpty(edtLyricWriter.text)) {
                Toast.makeText(applicationContext, "노래의 작사가를 입력하세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (TextUtils.isEmpty(edtCompany.text)) {
                Toast.makeText(applicationContext, "반주 제공 업체명을 입력하세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val post = Post()

            val newRef = FirebaseDatabase.getInstance().getReference("Posts").push()

            post.postId = newRef.key
            post.writeId = getMyId()
            post.songNumber = edtSongNumber.text.toString()
            post.songTitle = edtSongTitle.text.toString()
            post.singer = edtSinger.text.toString()
            post.songWriter = edtSongWriter.text.toString()
            post.lyricWriter = edtLyricWriter.text.toString()
            post.company = edtCompany.text.toString()
            post.writeTime = ServerValue.TIMESTAMP
            newRef.setValue(post)

            Toast.makeText(applicationContext, "저장 되었습니다.", Toast.LENGTH_SHORT).show()
            finish()

        }


    }

    fun getMyId(): String {
        return Settings.Secure.getString(this.contentResolver, Settings.Secure.ANDROID_ID)

    }
}
