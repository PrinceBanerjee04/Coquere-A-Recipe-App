package com.example.coquere

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.coquere.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding:ActivityHomeBinding
    private lateinit var rvAdapter: PopularAdapter
    private lateinit var dataList: ArrayList<Recipe>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()

    }

    private fun setUpRecyclerView() {
        dataList= ArrayList()
        binding.rvPopular.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

        var db= Room.databaseBuilder(this@HomeActivity,AppDatabase::class.java,"db_name")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("recipe.db")
            .build()

//        var daoObject=db.getDao()
//        var recipes=daoObject.getAll()
//        for (i in recipes!!.indices){
//            if(recipes[i]!!.catergory.contains("Popular")){
//                dataList.add(recipes[i]!!)
//            }
//            rvAdapter=PopularAdapter(dataList,this)
//            binding.rvPopular.adapter=rvAdapter

        try {
            val daoObject = db.getDao()
            val recipes = daoObject.getAll() ?: emptyList()

            val popularRecipes = recipes.filter { it?.catergory?.contains("Popular", ignoreCase = true) == true }
            val rvAdapter = PopularAdapter(popularRecipes.filterNotNull(), this)
            binding.rvPopular.adapter = rvAdapter
        } catch (e: Exception) {
            Log.e("YourTag", "Error setting up RecyclerView", e)
            // Handle the exception gracefully or display an error message to the user
        }
        

    }
}