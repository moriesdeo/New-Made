package com.mories.deo.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.mories.core.domain.model.MovieDomainModel
import com.mories.core.ui.ViewModelFactory
import com.mories.deo.MyApplication
import com.mories.deo.R
import com.mories.deo.ui.viewmodel.FavoriteViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory
    private val favoriteViewModel: FavoriteViewModel by viewModels { factory }
    private lateinit var movieDomainModel: MovieDomainModel
    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        movieDomainModel = intent.getParcelableExtra<MovieDomainModel>("data") as MovieDomainModel

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        toolbar.setNavigationOnClickListener {
            finish()
        }

        findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = movieDomainModel.title
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            if (!isFavorite) {
                fab.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.ic_baseline_favorite_24_favorite
                    )
                )

                Snackbar.make(view, "Add To Favorite", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()

                isFavorite = true
            } else {
                fab.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.ic_outline_favorite_border_24_not_favorite
                    )
                )

                Snackbar.make(view, "Remove From Favorite", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()

                isFavorite = false
            }
            favoriteViewModel.setFavorite(movieDomainModel, isFavorite)
        }

        setUI()
        liveData()
    }

    private fun liveData() {
        favoriteViewModel.movie.observe(this, { data ->
            data.forEach { dataForEach ->
                if (movieDomainModel.id == dataForEach.id) {
                    if (dataForEach.isFavorite) {
                        fab.setImageDrawable(
                            ContextCompat.getDrawable(
                                this,
                                R.drawable.ic_baseline_favorite_24_favorite
                            )
                        )
                        isFavorite = true
                    } else {
                        fab.setImageDrawable(
                            ContextCompat.getDrawable(
                                this,
                                R.drawable.ic_outline_favorite_border_24_not_favorite
                            )
                        )
                        isFavorite = false
                    }
                }
            }
        })
    }

    private fun setUI() {
        Glide.with(this).load("https://image.tmdb.org/t/p/w185" + movieDomainModel.backdropPath)
            .into(imgDetail)
    }
}