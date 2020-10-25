package com.mories.deo

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mories.core.data.Resource
import com.mories.core.ui.MovieAdapter
import com.mories.core.ui.ViewModelFactory
import com.mories.deo.ui.DetailActivity
import com.mories.deo.ui.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: ViewModelFactory
    private val movieViewModel: MovieViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val movieAdapter = MovieAdapter()
        movieAdapter.onItemClick = {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("data", it)
            startActivity(intent)
        }

        movieViewModel.movie.observe(this) { dataMovie ->
            if (dataMovie != null) {
                when (dataMovie) {
                    is Resource.Loading<*> -> progressMovie.visibility = View.VISIBLE
                    is Resource.Success<*> -> {
                        progressMovie.visibility = View.GONE
                        movieAdapter.setData(dataMovie.data)
                    }
                    is Resource.Error<*> -> {
                        progressMovie.visibility = View.GONE
                        tv_error.text = dataMovie.message ?: getString(R.string.something_wrong)
                    }
                }
            }
        }

        with(rvMovie) {
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_favorite -> {
                try {
                    startActivity(
                        Intent(
                            this,
                            Class.forName("com.mories.favorite.FavoriteActivity")
                        )
                    )
                } catch (e: Exception) {
                    Toast.makeText(this, "Module not found", Toast.LENGTH_SHORT).show()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}