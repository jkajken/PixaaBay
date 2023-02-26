package com.jk.pixaabay

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.jk.pixaabay.databinding.ActivityMainBinding
import com.jk.pixaabay.model.PixaModel
import com.jk.pixaabay.retrofit.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var adapter = PixaAdapter(arrayListOf())
    private var page = 1
    private var per_page = 3
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClickers()
    }

    private fun initClickers() {
        binding.apply {
            nextPageBtn.setOnClickListener {
                per_page = 3
                page++
                doRequest()
            }

            binding.recycler.addOnScrollListener(object : OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recycler, dx, dy)

                    val pastVisibleItems: Int
                    val visibleItemsCount: Int
                    val totalItemCount: Int
                    if (dy > 0) {
                        visibleItemsCount =
                            (recyclerView.layoutManager as LinearLayoutManager).childCount
                        pastVisibleItems =
                            (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                        totalItemCount =
                            (recyclerView.layoutManager as LinearLayoutManager).itemCount
                        if (visibleItemsCount + pastVisibleItems >= totalItemCount) {
                            // method where you get your images
                            doRequest()
                            page++
                        }
                    }
                }
            })
            searchBtn.setOnClickListener {
                per_page=3
                page = 1
                doRequest()
            }
            updateBtn.setOnClickListener {
                per_page += 3
                doRequest()
            }
        }

    }

    private fun ActivityMainBinding.doRequest() {
        RetrofitService().api.searchImage(
            searchWord = searchEditText.text.toString(),
            page = page,
            per_page = per_page
        ).enqueue(object : Callback<PixaModel> {
            override fun onResponse(call: Call<PixaModel>, response: Response<PixaModel>) {
                if (response.isSuccessful) {
                    adapter = PixaAdapter(response.body()?.hits!!)
                    recycler.adapter = adapter
                }
            }

            override fun onFailure(call: Call<PixaModel>, t: Throwable) {
                Log.d("niki", "onResponse: ${t.message}")
            }
        })
    }
}

