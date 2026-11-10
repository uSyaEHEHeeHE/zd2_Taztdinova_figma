package com.example.zd2_taztdinova

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layoutDiscussions: LinearLayout = findViewById(R.id.btn_discussions)
        val layoutCollections: LinearLayout = findViewById(R.id.btn_collections)
        val layoutFavorites: LinearLayout = findViewById(R.id.btn_favorites)

        layoutDiscussions.setOnClickListener {
            val intent = Intent(this, ChatListActivity::class.java)
            startActivity(intent)
        }

        layoutCollections.setOnClickListener {
            val intent = Intent(this, MoviesActivity::class.java)
            startActivity(intent)
        }

        layoutFavorites.setOnClickListener {
            val intent = Intent(this, MoviesActivity::class.java)
            startActivity(intent)
        }
    }
}
