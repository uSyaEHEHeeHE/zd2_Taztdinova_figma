package com.example.zd2_taztdinova

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import org.json.JSONObject

class MoviesActivity : AppCompatActivity() {

    private val omdbApiKey = "d9b892d1"
    private lateinit var queue: RequestQueue

    private var infoWitcher: Film? = null
    private var infoJessica: Film? = null
    private var infoMagicians: Film? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        queue = Volley.newRequestQueue(this)

        val imageWitcher: ImageView = findViewById(R.id.img_witcher)
        val imageJessica: ImageView = findViewById(R.id.img_jessica)
        val imageMagicians: ImageView = findViewById(R.id.img_magicians)

        val textWitcher: AppCompatTextView = findViewById(R.id.tv_witcher)
        val textJessica: AppCompatTextView = findViewById(R.id.tv_jessica)
        val textMagicians: AppCompatTextView = findViewById(R.id.tv_magicians)

        val cardViewWitcher: CardView = findViewById(R.id.card_witcher)
        val cardViewJessica: CardView = findViewById(R.id.card_jessica)
        val cardViewMagicians: CardView = findViewById(R.id.card_magicians)

        requestFilm("The Witcher", R.drawable.poster_witcher, imageWitcher, textWitcher, "Witcher") { film ->
            infoWitcher = film
        }

        requestFilm("Jessica Jones", R.drawable.poster_jessica, imageJessica, textJessica, "Jessica Jones") { film ->
            infoJessica = film
        }

        requestFilm("The Magicians", R.drawable.poster_magicians, imageMagicians, textMagicians, "Magicians") { film ->
            infoMagicians = film
        }

        cardViewWitcher.setOnClickListener {
            showFilmInfo(infoWitcher, "The Witcher")
        }

        cardViewJessica.setOnClickListener {
            showFilmInfo(infoJessica, "Jessica Jones")
        }

        cardViewMagicians.setOnClickListener {
            showFilmInfo(infoMagicians, "The Magicians")
        }
    }

    private fun requestFilm(
        movieTitle: String,
        placeholderRes: Int,
        targetImageView: ImageView,
        targetTextView: AppCompatTextView,
        label: String,
        onSuccess: (Film) -> Unit
    ) {
        val formattedTitle = movieTitle.replace(" ", "+")
        val url = "http://www.omdbapi.com/?t=$formattedTitle&apikey=$omdbApiKey"

        val request = StringRequest(
            Request.Method.GET,
            url,
            { response ->
                try {
                    val json = JSONObject(response)
                    val filmTitle = json.optString("Title", movieTitle)
                    val releaseYear = json.optString("Year", "")
                    val posterLink = json.optString("Poster", "")
                    val imdbScore = json.optString("imdbRating", "")

                    val film = Film(filmTitle, releaseYear, posterLink, imdbScore)
                    onSuccess(film)

                    targetTextView.text = label

                    if (posterLink.isNotEmpty() && posterLink != "N/A") {
                        Picasso.get()
                            .load(posterLink)
                            .placeholder(placeholderRes)
                            .error(placeholderRes)
                            .into(targetImageView)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            },
            { error ->
                error.printStackTrace()
            }
        )

        queue.add(request)
    }

    private fun showFilmInfo(film: Film?, fallbackTitle: String) {
        if (film != null && film.title.isNotEmpty()) {
            Toast.makeText(
                this,
                "${film.title} (${film.year}), рейтинг IMDb: ${film.rating}",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            Toast.makeText(this, "Фильм: $fallbackTitle", Toast.LENGTH_SHORT).show()
        }
    }
}
