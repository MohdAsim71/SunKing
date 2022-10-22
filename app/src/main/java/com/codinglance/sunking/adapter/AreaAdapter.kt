package com.codinglance.sunking.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.codinglance.sunking.R
import com.codinglance.sunking.databinding.ZoneListBinding
import com.codinglance.sunking.model.SalesArea


class AreaAdapter( private var areaList: ArrayList<SalesArea>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    class IdViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var binding: ZoneListBinding? = DataBindingUtil.bind(v)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.zone_list, parent, false)
        return IdViewHolder(view)


    }

    override fun getItemCount() =areaList.size


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


        val viewHolder= holder as IdViewHolder
        val first = areaList[position].territory.substringBeforeLast("::")
        val second = areaList[position].territory.substringAfterLast("::")
        viewHolder.binding!!.zoneTv.text=second


    }


    @SuppressLint("NotifyDataSetChanged")
    fun filterList(filteredList: ArrayList<SalesArea>) {
        areaList = filteredList
        notifyDataSetChanged()
    }
}