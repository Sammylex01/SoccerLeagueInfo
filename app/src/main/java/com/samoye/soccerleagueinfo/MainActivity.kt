package com.samoye.soccerleagueinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.samoye.soccerleagueinfo.data.api.ApiHelper
import com.samoye.soccerleagueinfo.data.api.RetrofitBuilder
import com.samoye.soccerleagueinfo.data.model.Competition
import com.samoye.soccerleagueinfo.ui.base.ViewModelFactory
import com.samoye.soccerleagueinfo.ui.main.adapter.MainAdapter
import com.samoye.soccerleagueinfo.ui.main.viewmodel.MainViewModel
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.samoye.soccerleagueinfo.utils.Status.SUCCESS
import com.samoye.soccerleagueinfo.utils.Status.ERROR
import com.samoye.soccerleagueinfo.utils.Status.LOADING

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter

    val progressBar: ProgressBar = findViewById(R.id.progressBar)
    val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
        setupUI()
        setupObservers()
    }


    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.getUsers().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    SUCCESS -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        resource.data?.let { users -> retrieveList(users) }
                    }
                    ERROR -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun retrieveList(users: List<Competition>) {
        adapter.apply {
            addUsers(users)
            notifyDataSetChanged()
        }
    }
}