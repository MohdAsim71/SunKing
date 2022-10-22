package com.codinglance.sunking.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.codinglance.sunking.R
import com.codinglance.sunking.adapter.AreaAdapter
import com.codinglance.sunking.databinding.ActivityAreaBinding
import com.codinglance.sunking.model.SalesArea
import com.codinglance.sunking.ui.view.MainActivity.Companion.TAG
import com.codinglance.sunking.ui.viewmodel.CountryViewModel.Companion.centralArealist
import com.codinglance.sunking.ui.viewmodel.CountryViewModel.Companion.esternArealist
import com.codinglance.sunking.ui.viewmodel.CountryViewModel.Companion.naArealist
import com.codinglance.sunking.ui.viewmodel.CountryViewModel.Companion.northenArealist
import com.codinglance.sunking.ui.viewmodel.CountryViewModel.Companion.westernArealist
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AreaActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityAreaBinding
    private lateinit var areaAdapter: AreaAdapter
    private var territory:String=""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val rootView = layoutInflater.inflate(R.layout.activity_area, null, true)
        binding = DataBindingUtil.bind<ActivityAreaBinding>(rootView)!!
        setContentView(rootView)
        supportActionBar?.hide()
        territory=intent?.getStringExtra("territory").toString()



        binding.areaRecyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        if (territory.equals("UGANDA::NA::CENTRAL"))
        {
            areaAdapter = AreaAdapter(centralArealist)

        }
        else if(territory.equals("UGANDA::NA::EASTERN")){
            areaAdapter = AreaAdapter(esternArealist)

        }
        else if(territory.equals("UGANDA::NA::NA")){
            areaAdapter = AreaAdapter(naArealist)

        }
        else if(territory.equals("UGANDA::NA::NORTHERN")){
            areaAdapter = AreaAdapter(northenArealist)

        }
        else if(territory.equals("UGANDA::NA::WESTERN")){
            areaAdapter = AreaAdapter(westernArealist)

        }
        binding.areaRecyclerview.adapter = areaAdapter

        binding.searchEt.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Do Nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.d(TAG, "MainFragment::onActivityCreated running)$s")

                filters(s.toString())

            }

            override fun afterTextChanged(s: Editable?) {
                // Do Nothing

            }

        })

        binding.backBtn.setOnClickListener {
            onBackPressed()
        }

    }

    private fun filters(text: String) {
        //new array list that will hold the filtered data
        var filterdNames: ArrayList<SalesArea> = ArrayList()

        //looping through existing elements
        if (text.isNotEmpty()) {
            if (territory.equals("UGANDA::NA::CENTRAL"))
            {
                filterdNames.addAll(centralArealist.filter {
                    it.territory.substringAfterLast("::").lowercase().contains(text.lowercase()) || it.territory.substringAfterLast("::").lowercase().contains(text.lowercase())
                }.toList())
            }
            else if(territory.equals("UGANDA::NA::EASTERN")){
                filterdNames.addAll(esternArealist.filter {
                    it.territory.substringAfterLast("::").lowercase().contains(text.lowercase()) || it.territory.substringAfterLast("::").lowercase().contains(text.lowercase())
                }.toList())
            }
            else if(territory.equals("UGANDA::NA::NA")){
                filterdNames.addAll(naArealist.filter {
                    it.territory.substringAfterLast("::").lowercase().contains(text.lowercase()) || it.territory.substringAfterLast("::").lowercase().contains(text.lowercase())
                }.toList())
            }
            else if(territory.equals("UGANDA::NA::NORTHERN")){
                filterdNames.addAll(northenArealist.filter {
                    it.territory.substringAfterLast("::").lowercase().contains(text.lowercase()) || it.territory.substringAfterLast("::").lowercase().contains(text.lowercase())
                }.toList())
            }
            else if(territory.equals("UGANDA::NA::WESTERN")){
                filterdNames.addAll(westernArealist.filter {
                    it.territory.substringAfterLast("::").lowercase().contains(text.lowercase()) || it.territory.substringAfterLast("::").lowercase().contains(text.lowercase())
                }.toList())
            }


            //calling a method of the adapter class and passing the filtered list}

        }
        else{
            if (territory.equals("UGANDA::NA::CENTRAL"))
            {
                filterdNames.addAll(centralArealist)

            }
            else if(territory.equals("UGANDA::NA::EASTERN")){
                filterdNames.addAll(esternArealist)

            }
            else if(territory.equals("UGANDA::NA::NA")){
                filterdNames.addAll(naArealist)

            }
            else if(territory.equals("UGANDA::NA::NORTHERN")){
                filterdNames.addAll(naArealist)

            }
            else if(territory.equals("UGANDA::NA::WESTERN")){
                filterdNames.addAll(westernArealist)

            }
        }
        areaAdapter.filterList(filterdNames)
    }

    override fun onBackPressed() {
        super.onBackPressed()
   finish()
    }

}