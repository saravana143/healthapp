package com.sara.healthapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sara.healthapp.util.CSVFileReader
import com.sara.healthapp.util.HospitalListAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), HospitalListAdapter.OnProductItemListener {

    private lateinit var hospitalListAdapter: HospitalListAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputStream = resources.openRawResource(R.raw.hospital)
        val csvFile = CSVFileReader(inputStream)
        val scoreList: List<List<String>> = csvFile.read()

        hospitalListAdapter = HospitalListAdapter(this, this)
        linearLayoutManager = LinearLayoutManager(this)
        hospitaListView.apply {
            adapter = hospitalListAdapter
            layoutManager = linearLayoutManager
        }

        hospitalListAdapter.setProducts(scoreList)
    }

    override fun onProductClick(position: Int) {
        Log.d("Hospital position", "clicked" + position)
    }
}