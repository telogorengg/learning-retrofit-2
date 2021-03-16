package com.example.retrofitlearning.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitlearning.R
import com.example.retrofitlearning.data.api.ApiHelper
import com.example.retrofitlearning.data.api.RetrofitBuilder
import com.example.retrofitlearning.data.model.User
import com.example.retrofitlearning.ui.base.ViewModelFactory
import com.example.retrofitlearning.ui.main.adapter.MainAdapter
import com.example.retrofitlearning.ui.main.viewmodel.MainViewModel
import com.example.retrofitlearning.utils.Status
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewModel()
        setupUI()
        setupObservers()
    }

    private fun setupViewModel()
    {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)
    }

    private fun setupUI()
    {
        userListRv.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        userListRv.addItemDecoration(
            DividerItemDecoration(
                userListRv.context,
                (userListRv.layoutManager as LinearLayoutManager).orientation
            )
        )

        userListRv.adapter = adapter
    }

    private fun setupObservers()
    {
        viewModel.getUsers().observe(this, Observer {
            it?.let { resource ->
                when(resource.status)
                {
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        userListRv.visibility = View.GONE
                    }

                    Status.SUCCESS -> {
                        userListRv.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        resource.data?.let { users ->
                            getList(users)
                        }
                    }

                    Status.ERROR -> {
                        userListRv.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun getList(users: List<User>)
    {
        adapter.apply {
            addUsers(users)
            notifyDataSetChanged()
        }
    }
}
