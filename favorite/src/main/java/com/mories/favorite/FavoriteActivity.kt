package com.mories.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mories.core.di.CoreComponent
import com.mories.core.di.DaggerCoreComponent
import com.mories.core.domain.model.MovieDomainModel
import com.mories.core.ui.MovieAdapter
import com.mories.core.ui.ViewModelFactory
import com.mories.deo.ui.DetailActivity
import com.mories.favorite.di.DaggerFavoriteComponent
import com.mories.favorite.di.Favorite2ViewModel
import com.mories.favorite.di.FavoriteComponent
import kotlinx.android.synthetic.main.activity_favorite.*
import javax.inject.Inject

class FavoriteActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: ViewModelFactory
    private val favoriteViewModel: Favorite2ViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        val coreComponent: CoreComponent by lazy {
            DaggerCoreComponent.factory().create(applicationContext)
        }

        val favoriteComponent: FavoriteComponent by lazy {
            DaggerFavoriteComponent.factory().create(coreComponent)
        }
        favoriteComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        val movieAdapter = MovieAdapter()
        movieAdapter.onItemClick = {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("data", it)
            startActivity(intent)
        }
        val fav = arrayListOf<MovieDomainModel>()
        favoriteViewModel.movie.observe(this, { data ->
            progress.visibility = View.GONE
            data?.forEach {
                if (it.isFavorite) {
                    fav.add(it)
                    movieAdapter.setData(fav)
                }
            }
        })
        with(rvFavorite) {
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }
}