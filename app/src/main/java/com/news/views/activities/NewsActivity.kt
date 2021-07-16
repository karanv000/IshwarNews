package com.news.views.activities

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.news.R
import com.news.utils.InternetCheck
import com.news.adapter.NewsRecyclerViewAdapter
import com.news.utils.Utils
import com.news.models.NewsViewModel
import com.news.models.SingleNewsItem
import okhttp3.ResponseBody
import java.math.BigInteger
import java.util.*


class NewsActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: NewsRecyclerViewAdapter

    lateinit var newsViewModel: NewsViewModel

    private lateinit var circleProgress: ProgressBar
    private lateinit var emptyScreen: ImageView
    private lateinit var newsFullDetails: Map<BigInteger, SingleNewsItem>
    private lateinit var newsList: RecyclerView

    private val periods = arrayOf("1", "7", "30")
    private var periodSelected = periods[0]



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // declare views
        newsList = findViewById(R.id.news_list)
        circleProgress = findViewById(R.id.circular_progress)
        emptyScreen = findViewById(R.id.empty_screen_decoration)
        // initiating the RecyclerView
        linearLayoutManager = LinearLayoutManager(this)
        newsList.layoutManager = linearLayoutManager
        // declaring the view model and it's observer
        newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        observeNewsViewModel()
        // Get News Details
        getNewsData()
    }


    /**
     * View model observers subscribers for data handling
     */
    private fun observeNewsViewModel() {
        // observer for the loading process
        newsViewModel.loading.observe(this, androidx.lifecycle.Observer {
            if (it) {
                circleProgress.visibility = View.VISIBLE
            } else {
                circleProgress.visibility = View.GONE
            }
        })
        // observer for the data success result
        newsViewModel.newsDataError.observe(this, androidx.lifecycle.Observer {
            try {
                // get the New Error Data to handle/display
                for ((newsId, newsError) in it) {
                    // now go for it
                    goForError(newsError)
                    emptyScreen.visibility = View.VISIBLE
                }
            } catch (e: Exception) {
                print(e.toString())
            }
        })
        // observer for the data error
        newsViewModel.newsData.observe(this, androidx.lifecycle.Observer {
            try {
                // get the Data and display it
                newsFullDetails = it
                displayNewsData()
            } catch (ex: Exception) {
                ex.printStackTrace()
                if (it != null) {
                    Toast.makeText(
                        this@NewsActivity, resources.getText(R.string.something_went_wrong),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        })
    }

    private fun displayNewsData() {
        adapter = NewsRecyclerViewAdapter(newsFullDetails.values.toList())
        newsList.adapter = adapter
    }

    private fun getPeriod(): String {
        return periodSelected
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun getNewsData() {
        // call the API
        InternetCheck(object : InternetCheck.Consumer {
            override fun accept(internet: Boolean?) {

                if(internet!!){
                    Log.e("Internet Check", "Internet Connection Available")
                    emptyScreen.visibility = View.GONE
                    newsList.visibility=View.VISIBLE
                    newsViewModel.getNewsDetails( getPeriod())
                }else{
                    emptyScreen.visibility = View.VISIBLE
                    newsList.visibility=View.GONE
                    circleProgress.visibility = View.GONE
                    Log.e("Internet Check", "Internet Connection Not Available")
                    showSnackBar("Internet Connection Not Available", this@NewsActivity)
                }

            }
        })

    }

    fun showSnackBar(message: String?, activity: Activity?) {
        if (null != activity && null != message) {
            Snackbar.make(
                activity.findViewById(android.R.id.content),
                message, Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    fun goBack(view: View) {
        super.onBackPressed()
    }

    fun reloadProcess(view: View) {
        // give press feeling
        view.alpha = 0.3f
        Handler().postDelayed({
            view.alpha = 1.0f
        }, 150)
        // do the work
        getNewsData()
    }

    private fun goForError(errorBody: ResponseBody?) {
        Utils.displayError(this@NewsActivity, errorBody)
        emptyScreen.visibility = View.VISIBLE
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            //R.id.action_day -> return true

            R.id.action_day -> {
                periodSelected = periods[0]
                circleProgress.visibility = View.VISIBLE
                getNewsData()
                true
            }
            R.id.action_week -> {
                periodSelected = periods[1]
                circleProgress.visibility = View.VISIBLE
                getNewsData()
                true
            }
            R.id.action_month -> {
                periodSelected = periods[2]
                circleProgress.visibility = View.VISIBLE
                getNewsData()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}