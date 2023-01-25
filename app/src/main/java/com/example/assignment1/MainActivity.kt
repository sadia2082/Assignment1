package com.example.assignment1


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.ui.AppBarConfiguration
import com.example.assignment1.ui.addrecord.AddRecordFragment
import com.example.assignment1.ui.dashboard.DashboardFragment
import com.example.assignment1.ui.favourite.FavouriteFragment
import com.example.assignment1.ui.search.SearchFragment
import com.example.assignment1.ui.settings.SettingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var bottomNav : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragment(DashboardFragment())
        bottomNav = findViewById(R.id.bottomNavigationView) as BottomNavigationView
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.dashboard -> {
                    loadFragment(DashboardFragment())
                    true
                }
                R.id.favourite -> {
                    loadFragment(FavouriteFragment())
                    true
                }
                R.id.add_record -> {
                    loadFragment(AddRecordFragment())
                    true
                }

                R.id.search -> {
                    loadFragment(SearchFragment())
                    true
                }
                R.id.settings2 -> {
                    loadFragment(SettingsFragment())
                    true
                }
                else ->{
                    loadFragment(AddRecordFragment())
                    true
                }
            }
        }
    }
    val appBarConfiguration = AppBarConfiguration(
        setOf(
            R.id.dashboard, R.id.favourite, R.id.add_record
        )
    )

    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.activity_main_nav_host_fragment,fragment)
        transaction.commit()
    }

}
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//import androidx.navigation.findNavController
//import androidx.navigation.ui.setupWithNavController
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.assignment1.databinding.ActivityMainBinding
//import com.google.android.material.bottomnavigation.BottomNavigationView
//
//
//class MainActivity : AppCompatActivity() {
//
//    // view binding for the activity
//    private var _binding: ActivityMainBinding? = null
//    private val binding get() = _binding!!
//
//
//    // get reference to the adapter class
//    private var languageList = ArrayList<Record>()
//    private lateinit var rvAdapter: RvAdapter
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        _binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(R.layout.activity_main)
//
//        // define layout manager for the Recycler view
//        binding.recyclerView.layoutManager = LinearLayoutManager(this)
//
//        // attach adapter to the recycler view and also handle item click
//
//        // attach adapter to the recycler view
//        rvAdapter = RvAdapter(languageList)
//
//        // add adapter to the recycler view
//        binding.recyclerView.adapter = rvAdapter
//
//        // create objects of Language
//        // create some row data
//        val language1 = Record("Sadia", "3 Year student")
//        val language2 = Record("Sadia", "2 Year student")
//        val language3 = Record("Sadia", "1 Year student")
//        val language4 = Record("Sadia", "4 Year student")
//        val language5 = Record("Sadia", "4 Year student")
//
//        // pass raw data t rhe list
//        languageList.add(language1)
//        languageList.add(language2)
//        languageList.add(language3)
//        languageList.add(language4)
//        languageList.add(language5)
//
//
//        rvAdapter.notifyDataSetChanged()
//
//        //Initialize the bottom navigation view
//        //create bottom navigation view object
//        val bottomNavigationView = findViewById<BottomNavigationView
//                >(R.id.bottomNavigationView)
//        val navController = findNavController(R.id.activity_main_nav_host_fragment)
//        bottomNavigationView.setupWithNavController(navController
//        )
//
//    }
//
//    // on destroy of view make the
//    // binding reference to null
//    override fun onDestroy() {
//        super.onDestroy()
//        _binding = null
//    }
