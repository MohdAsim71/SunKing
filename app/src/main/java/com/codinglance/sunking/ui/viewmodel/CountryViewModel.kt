package com.codinglance.sunking.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codinglance.sunking.model.*
import com.codinglance.sunking.repository.CountryRepository
import com.codinglance.sunking.repository.ResultState
import com.codinglance.sunking.ui.view.MainActivity.Companion.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(
    private val repository: CountryRepository
):ViewModel(){
    private val _countryState: MutableLiveData<ResultState<CounrtyDate>> = MutableLiveData()
    val counrtystate: LiveData<ResultState<CounrtyDate>> = _countryState

    var mSubscriber= MutableLiveData(false)
    var zonelist: ArrayList<SalesZone> = ArrayList()
    var arealist: ArrayList<SalesArea> = ArrayList()
    var customArealist: ArrayList<SalesArea> = ArrayList()
    var regionlist: ArrayList<SalesRegion> = ArrayList()
    var countrylist: ArrayList<SalesCountry> = ArrayList()

    companion object{

        var centralArealist: ArrayList<SalesArea> = ArrayList()
        var esternArealist: ArrayList<SalesArea> = ArrayList()
        var naArealist: ArrayList<SalesArea> = ArrayList()
        var northenArealist: ArrayList<SalesArea> = ArrayList()
        var westernArealist: ArrayList<SalesArea> = ArrayList()
    }


    init {
        DataApi()
    }

    private fun DataApi() {
        viewModelScope.launch(Dispatchers.IO) {
            _countryState.postValue(ResultState.Loading)
            try {

                val response =  repository.getDate()

                if (response.Success ) {
                    centralArealist.clear()
                    naArealist.clear()
                    westernArealist.clear()
                    esternArealist.clear()
                    northenArealist.clear()
                    _countryState.postValue(ResultState.Success(response))
                    countrylist= response.ResponseData.sales_country as ArrayList<SalesCountry>
                    arealist= response.ResponseData.sales_area as ArrayList<SalesArea>
                    regionlist= response.ResponseData.sales_region as ArrayList<SalesRegion>
                    zonelist= response.ResponseData.sales_zone as ArrayList<SalesZone>
                    mSubscriber.postValue(true)

                    Log.d(TAG, "DataApi: success")
                     for (i in arealist)
                     {
                         val first = i.territory.substringBeforeLast("::")
                         val second = i.territory.substringAfterLast("::")
                         Log.d(TAG, "DataApi: first $first")
                         Log.d(TAG, "DataApi: second $second")
                         if (first.equals("UGANDA::NA::CENTRAL"))
                         {
                             centralArealist.add(SalesArea(i.area,i.count_unsigned_contracts,i.last_month_sales,
                                 i.lmsd_weighted_units,i.mtd_new_selling_agents,i.mtd_unit_sales,
                                 i.mtd_weighted_units,i.signed_contracts,i.territory))
                         }
                         else if(first.equals("UGANDA::NA::EASTERN")){
                             esternArealist.add(SalesArea(i.area,i.count_unsigned_contracts,i.last_month_sales,
                                 i.lmsd_weighted_units,i.mtd_new_selling_agents,i.mtd_unit_sales,
                                 i.mtd_weighted_units,i.signed_contracts,i.territory))
                         }
                         else if(first.equals("UGANDA::NA::NA")){
                             naArealist.add(SalesArea(i.area,i.count_unsigned_contracts,i.last_month_sales,
                                 i.lmsd_weighted_units,i.mtd_new_selling_agents,i.mtd_unit_sales,
                                 i.mtd_weighted_units,i.signed_contracts,i.territory))
                         }
                         else if(first.equals("UGANDA::NA::NORTHERN")){
                             northenArealist.add(SalesArea(i.area,i.count_unsigned_contracts,i.last_month_sales,
                                 i.lmsd_weighted_units,i.mtd_new_selling_agents,i.mtd_unit_sales,
                                 i.mtd_weighted_units,i.signed_contracts,i.territory))
                         }
                         else if(first.equals("UGANDA::NA::WESTERN")){
                             westernArealist.add(SalesArea(i.area,i.count_unsigned_contracts,i.last_month_sales,
                                 i.lmsd_weighted_units,i.mtd_new_selling_agents,i.mtd_unit_sales,
                                 i.mtd_weighted_units,i.signed_contracts,i.territory))
                         }

                     }

                }
            }

            catch (e:Exception){
                Log.d("LoginData_TAG", "loginApi: $e")
                // nextAction.value = Action.create(Action.Type.ON_LOGIN_RESPONSE)
                _countryState.postValue(ResultState.Error(e.localizedMessage.toString()))

            }

        }
    }

}





