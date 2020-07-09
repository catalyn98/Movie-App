package com.mobiversal.movieapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.mobiversal.movieapplication.actors_list.Actors
import com.mobiversal.movieapplication.genres_list.Genres

class MainActivity : AppCompatActivity() {
//private val val REQUEST_COD = 111
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actorsButton : Button = findViewById(R.id.actors)
        actorsButton.setOnClickListener{
            val intent = Intent (this, Actors::class.java)
            startActivity(intent)
        }

        val genresButton : Button = findViewById(R.id.genres)
        genresButton.setOnClickListener {
            val intent = Intent(this, Genres::class.java)
            startActivity(intent)
        }

    val saveButton : Button = findViewById(R.id.Save)
    saveButton.setOnClickListener {
        val intent = Intent(this, MenuHmaburger::class.java)
        startActivity(intent)
    }



//        fun iop(){
//            val REQUEST_COD = 111 //cand se porneste activitatea de Actori, cu genuri altul
//            var startIntent: Intent = Intent(this, Actors::class.java)
//            startActivityForResults(startIntent, REQUEST_COD)
//        }
//
//        override fun onActivityResult()
       }
    }