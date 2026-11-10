package com.example.zd2_taztdinova

import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ChatListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_list)

        val chatLucifer: FrameLayout = findViewById(R.id.item_lucifer)
        val chatWitcher: FrameLayout = findViewById(R.id.item_witcher)
        val chatLost: FrameLayout = findViewById(R.id.item_lost)
        val chatMagicians: FrameLayout = findViewById(R.id.item_magicians)

        chatLucifer.setOnClickListener {
            Toast.makeText(this, "Открыт чат: Lucifer", Toast.LENGTH_SHORT).show()
        }

        chatWitcher.setOnClickListener {
            Toast.makeText(this, "Открыт чат: The Witcher", Toast.LENGTH_SHORT).show()
        }

        chatLost.setOnClickListener {
            Toast.makeText(this, "Открыт чат: LOST", Toast.LENGTH_SHORT).show()
        }

        chatMagicians.setOnClickListener {
            Toast.makeText(this, "Открыт чат: The Magicans", Toast.LENGTH_SHORT).show()
        }
    }
}
