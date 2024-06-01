package com.example.androidsubmision1.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidsubmision1.GithubAdapter
import com.example.androidsubmision1.ItemsItem
import com.example.androidsubmision1.R
import com.example.androidsubmision1.ViewModel.FavoriteViewModel
//import com.example.androidsubmision1.ViewModel.ViewModelFactory
import com.example.androidsubmision1.databinding.ActivityFavoriteBinding
import org.koin.android.viewmodel.ext.android.viewModel


class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    private lateinit var dataCount: String

//    private val favoriteViewModel by viewModels<FavoriteViewModel> {
//        ViewModelFactory.getInstance(application)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.user_favorite)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        binding.rvFavorite.layoutManager = LinearLayoutManager(this)



        favoriteViewModel.getFavorite().observe(this) { data ->
            val items = arrayListOf<ItemsItem>()
            data.map {
                val item = ItemsItem(login = it.username, avatarUrl = it.avatarUrl ?: "")
                items.add(item)
            }
            dataCount = "${items.size}"
            Log.d("FavoriteViewModel", "${items.size}")
            val adapter = GithubAdapter(items, this@FavoriteActivity)
            binding.rvFavorite.adapter = adapter
            adapter.setOnItemClickCallback(object : GithubAdapter.OnItemClickCallback {
                override fun onItemClicked(data: String) {
                    startActivity(
                        Intent(
                            this@FavoriteActivity,
                            ProfileActivity::class.java
                        ).putExtra("USER_EXTRA", data)
                    )
                }
            })
        }

        binding.fab.setOnClickListener {
            try {
                startActivity(
                    Intent(
                        this,
                        Class.forName("com.example.androidsubmision1.countfavorite.CountFavoriteActivities")
                    ).putExtra("dataCountFavorite", dataCount)
                )
            } catch (e: Exception) {
                Toast.makeText(this, "Module not found", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

}