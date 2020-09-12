package com.mobi.ezywayt_demo

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobi.ezyway.Common
import com.mobi.ezyway.Payment
import com.mobi.ezyway.service.network.*
import com.mobi.ezywayt_demo.adapter.CardListAdapter
import kotlinx.android.synthetic.main.activity_card_list.*

class CardListActivity : AppCompatActivity() {

    val payment: Payment = Payment()
    var btn_submit: Button? = null
    private var mProgressDialog: ProgressDialog? = null

    var mobiApiKey = "b07ad9f31df158edb188a41f725899bc" //for demo token
    var username = "Mobiversa" // for demo
    val requestMap: HashMap<String, String> = HashMap()


    private lateinit var cardListAdapter: CardListAdapter
    private var cardList : ArrayList<CardWallet> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_list)

        card_list_rec.layoutManager =
            LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
        cardListAdapter = CardListAdapter( applicationContext!!,cardList)
        card_list_rec.adapter = cardListAdapter

        Payment.getInstance(this@CardListActivity, paymentResponse, false)

        requestMap[Common.mobiApiKey] = mobiApiKey.trim { it <= ' ' }
        requestMap[Common.loginId] = username.trim { it <= ' ' }
        requestMap[Common.mobileNo] = "919003745350"

        payment.jsonGetCardList(requestMap)
    }

    fun deleteCard(cardToken : String){
        requestMap[Common.cardToken] = cardToken
        payment.jsonRemoveCard(requestMap)
    }

    fun getCardDetail(cardToken : String){
        requestMap[Common.cardToken] = cardToken
        payment.jsonGetCardDetail(requestMap)
    }

    fun payByCard(cardToken : String){
        requestMap[Common.cardToken] = cardToken
        requestMap[Common.invoiceId] = "Testing"
        requestMap[Common.amount] = "1.00"
        payment.jsonPayByCard(requestMap)
    }

    //Payment Connection Interface
    private val paymentResponse = object : PaymentResponse {
        override fun getCardList(success: CardList) {
            cardList.addAll(success.responseData.cardWalletList)
            cardListAdapter.notifyDataSetChanged()
        }

        override fun getCardDetail(success: CardDetail) {
            result_txt.text = ""+success.responseDescription
        }

        override fun setPaymentSuccess(success: PaymentResult) {
            result_txt.text = ""+success.responseDescription

        }

        override fun setFailure(failure: String) {
            Log.e("Response Failure", "$failure")
            result_txt.text = failure
        }

        override fun setRemoveCard(success: RemoveCardPojo) {
            result_txt.text = success.responseDescription
        }
    }
}