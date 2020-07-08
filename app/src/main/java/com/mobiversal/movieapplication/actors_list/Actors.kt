package com.mobiversal.movieapplication.actors_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobiversal.movieapplication.R
import com.mobiversal.movieapplication.actor.ActorsRepository
import com.mobiversal.movieapplication.actor.FavouriteActor
import kotlinx.android.synthetic.main.activity_actors.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Actors : AppCompatActivity() {
    val list: List<FavouriteActor> = ArrayList()
    private var actors: List<FavouriteActor>? = null
    private val adapter = ActorsAdapter(getActors())

    companion object {
        val TAG = Actors::class.java.simpleName
    }

    private fun setupRecycleView() {
        val llm = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_actors.layoutManager = llm
        rv_actors.adapter = ActorsAdapter(actors)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actors)
        getActors()
        Log.d(TAG, "onCreate()")
    }

        fun getActors(): List<FavouriteActor>? {

            GlobalScope.launch(Dispatchers.IO) {
                actors = ActorsRepository.getAllRemote()
                actors?.let { actors ->
                    ActorsRepository.replaceAll(actors)
                    withContext(Dispatchers.Main) {
                        setupRecycleView()
                    }
                }
            }
            return actors
        }
    }