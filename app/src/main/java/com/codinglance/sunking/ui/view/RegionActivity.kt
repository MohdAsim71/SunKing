package com.codinglance.sunking.ui.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.codinglance.sunking.ui.viewmodel.CountryViewModel
import com.codinglance.sunking.R
import com.codinglance.sunking.adapter.RegionAdapter
import com.codinglance.sunking.databinding.ActivityRegionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegionActivity : AppCompatActivity() {

    private val countryViewModel: CountryViewModel by viewModels()
    private lateinit var  binding: ActivityRegionBinding
    private lateinit var zoneAdapter: RegionAdapter
    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val rootView = layoutInflater.inflate(R.layout.activity_region, null, true)
        binding = DataBindingUtil.bind<ActivityRegionBinding>(rootView)!!
        setContentView(rootView)
        supportActionBar?.hide()

        binding.viewModel=countryViewModel

        (countryViewModel as CountryViewModel).mSubscriber.observe(this, androidx.lifecycle.Observer {
            if(it){
                binding.regionRecyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                zoneAdapter = RegionAdapter(this,countryViewModel.regionlist)
                binding.regionRecyclerview.adapter = zoneAdapter


            }
        })


        binding.backBtn.setOnClickListener {
            onBackPressed()
        }
    }


}