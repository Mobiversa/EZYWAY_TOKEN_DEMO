package com.mobi.ezywayt_demo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.mobi.ezyway.service.network.CardWallet
import com.mobi.ezywayt_demo.CardListActivity
import com.mobi.ezywayt_demo.R
import kotlinx.android.synthetic.main.list_cards.view.*

class CardListAdapter : RecyclerView.Adapter<CardListAdapter.ViewHolder> {


    var context: Context? = null
    var dataList: List<CardWallet> = ArrayList()

    constructor(context: Context, categoryList: ArrayList<CardWallet>){
        this.dataList = categoryList
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.list_cards, parent, false)
        context = parent.context
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.text_walletId.text = "Wallet Id : "+dataList[position].walletId
        holder.text_maskedPan.text = "Masked Pan : "+dataList[position].maskedPan
        holder.text_expDate.text = "Exp Date : "+dataList[position].expDate

        holder.button_pay.setOnClickListener {
            (context as CardListActivity).payByCard(dataList[position].walletId)
        }
        holder.button_delete.setOnClickListener {
            (context as CardListActivity).deleteCard(dataList[position].walletId)
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val text_walletId: AppCompatTextView = itemView.text_walletId
        val text_maskedPan: AppCompatTextView = itemView.text_maskedPan
        val text_expDate: AppCompatTextView = itemView.text_expDate
        val button_pay: AppCompatButton = itemView.button_pay
        val button_delete: AppCompatButton = itemView.button_delete
    }
}