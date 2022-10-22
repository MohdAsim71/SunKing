package com.codinglance.sunking.ui.view

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.codinglance.sunking.ui.viewmodel.CountryViewModel
import com.codinglance.sunking.R
import com.codinglance.sunking.adapter.ZoneAdapter
import com.codinglance.sunking.databinding.ActivityMainBinding
import com.codinglance.sunking.repository.ResultState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val countryViewModel: CountryViewModel by viewModels()
    private lateinit var  binding:ActivityMainBinding
    private lateinit var zoneAdapter: ZoneAdapter
    private lateinit var mProgressDialog: ProgressDialog

    companion object{
        const val TAG="SUNKING"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val rootView = layoutInflater.inflate(R.layout.activity_main, null, true)
        binding = DataBindingUtil.bind<ActivityMainBinding>(rootView)!!
        setContentView(rootView)
        supportActionBar?.hide()
        binding.viewModel=countryViewModel

        (countryViewModel as CountryViewModel).mSubscriber.observe(this, androidx.lifecycle.Observer {
            if(it){
                binding.zoneRecyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                zoneAdapter = ZoneAdapter(this,countryViewModel.zonelist)
                binding.zoneRecyclerview.adapter = zoneAdapter
                binding.countryNameText.text=countryViewModel.countrylist[0].country

            }
        })

        initViewModel()

        binding.backBtn.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initViewModel() {
        countryViewModel.counrtystate.observe(this){
            when(it){
                ResultState.Loading -> {
                displayProgressAnimation()
                }
                is ResultState.Success ->{
                  hideProgressAnimation()
                }
                is ResultState.Error ->{
                    Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()

                }

            }
        }
    }

     fun displayProgressAnimation() {

        // only create one
        if(!this::mProgressDialog.isInitialized) {
            // start progress Dialog animation:
            mProgressDialog = ProgressDialog.show(
                this,
                null,
                "LOADING...",
                false
            )
        } else {
            mProgressDialog.show()
        }
    }


     fun hideProgressAnimation() {

        mProgressDialog.let {
            //if (it.isShowing)
            mProgressDialog.dismiss()
        }
    }

}