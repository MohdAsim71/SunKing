package com.codinglance.sunking.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.codinglance.sunking.ui.view.MainActivity
import com.codinglance.sunking.R
import com.codinglance.sunking.ui.view.RegionActivity
import com.codinglance.sunking.databinding.ZoneListBinding
import com.codinglance.sunking.model.SalesZone


class ZoneAdapter(private var mainActivity: MainActivity, private var zoneList: List<SalesZone>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    class IdViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var binding: ZoneListBinding? = DataBindingUtil.bind(v)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.zone_list, parent, false)
        return IdViewHolder(view)


    }

    override fun getItemCount() =zoneList.size


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


        val viewHolder= holder as IdViewHolder
        viewHolder.binding!!.zoneTv.text=zoneList[position].zone

        viewHolder.itemView.setOnClickListener {

            val intent = Intent(mainActivity, RegionActivity::class.java)
            mainActivity.startActivity(intent)

        }
    }
}