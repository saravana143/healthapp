package com.sara.healthapp.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sara.healthapp.R

class HospitalListAdapter(context: Context?, onProductItemListener: OnProductItemListener) :
    RecyclerView.Adapter<HospitalListAdapter.ProductViewHolder>() {

    private var mInflater: LayoutInflater = LayoutInflater.from(context)
    private var products: List<List<String>>
    private var onProductItemListener: OnProductItemListener = onProductItemListener

    init {
        products = ArrayList<ArrayList<String>>()
    }

    fun setProducts(products: List<List<String>>) {
        this.products = products
        notifyDataSetChanged()
    }

    class ProductViewHolder(view: View, var onProductItemListener: OnProductItemListener) :
        RecyclerView.ViewHolder(view), View.OnClickListener {

        var hosNameView: TextView
        var hosTypeView: TextView
        var sectorView: TextView

        var address1View: TextView
        var address2View: TextView
        var address3View: TextView

        var telephoneView: TextView

        fun bindData(item: List<String>) {
            hosNameView.text = item?.get(7)
            hosTypeView.text = item?.get(3)
            sectorView.text = item?.get(4)

            address1View.text = item?.get(11)
            address2View.text = item?.get(12)
            address3View.text = item?.get(13)

            telephoneView.text = item?.getOrNull(18)
        }

        init {
            hosNameView = itemView.findViewById(R.id.hosName)
            hosTypeView = itemView.findViewById(R.id.hostype)
            sectorView = itemView.findViewById(R.id.sector)

            address1View = itemView.findViewById(R.id.address1)
            address2View = itemView.findViewById(R.id.address2)
            address3View = itemView.findViewById(R.id.address3)

            telephoneView = itemView.findViewById(R.id.telephone)
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            onProductItemListener.onProductClick(adapterPosition)
        }
    }

    interface OnProductItemListener {
        fun onProductClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view: View = mInflater.inflate(R.layout.hospital_list_item, parent, false)
        return ProductViewHolder(view, onProductItemListener)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products.get(position + 1)
            holder.bindData(product)
    }

    override fun getItemCount(): Int {
        return products.size
    }

}
