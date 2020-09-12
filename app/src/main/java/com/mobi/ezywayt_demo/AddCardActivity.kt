package com.mobi.ezywayt_demo

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.mobi.ezyway.Common
import com.mobi.ezyway.Payment
import com.mobi.ezyway.service.network.*
import kotlinx.android.synthetic.main.activity_add_card.*

class AddCardActivity : AppCompatActivity() {

    val payment: Payment = Payment()
    private var mProgressDialog: ProgressDialog? = null

    var mobiApiKey = "b07ad9f31df158edb188a41f725899bc" //for demo token
    var loginIdStr = "Mobiversa" // for demo
    val requestMap: HashMap<String, String> = HashMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_card)

        Payment.getInstance(this@AddCardActivity, paymentResponse, false)

        btn_submit.setOnClickListener { view ->

            val nameOnCard = edit_nameOnCard.text.toString()
            val firstName = edit_firstName.text.toString()
            val lastName = edit_lastName.text.toString()
            val CardNumber = edit_cardNumber.text.toString()
            val CardCvv = edit_cardCVV.text.toString()
            val CardExpMonth = edit_cardExpirymonth.text.toString()
            val CardExpYear = edit_cardExpiryYear.text.toString()
            val emailStr = edit_email.text.toString()

            if (edit_nameOnCard.text.toString().isEmpty()) {
                edit_nameOnCard.error = "Enter Valid Name"
            } else if (edit_firstName.text.toString().isEmpty()) {
                edit_firstName.error = "Enter Valid Name"
            } else if (edit_lastName.text.toString().isEmpty()) {
                edit_lastName.error = "Enter Valid Name"
            } else if (edit_cardNumber.text.toString().length != 16 && edit_cardNumber.text.toString().length != 19) {
                edit_cardNumber.error = "Enter Valid Card Number"
            } else if (edit_cardCVV.text.toString().length != 3) {
                edit_cardCVV.error = "Enter Valid CVV"
            } else if (edit_cardExpirymonth.text.toString().length != 2) {
                edit_cardExpirymonth.error = "Enter Valid Expiry Month"
            } else if (edit_cardExpiryYear.text.toString().length != 2) {
                edit_cardExpiryYear.error = "Enter Valid Expiry Year"
            } else {

                val requestMap: HashMap<String, String> = HashMap()
                requestMap[Common.mobileNo] = edit_phone.text.toString()
                requestMap[Common.NameOnCard] = edit_nameOnCard.text.toString()
                requestMap[Common.email] = edit_email.text.toString()
                requestMap[Common.orderId] = "Testing"
                requestMap[Common.amount] = "2.00"
                requestMap[Common.postalCode] = "638661"
                requestMap[Common.firstName] = firstName
                requestMap[Common.lastName] = lastName
                requestMap[Common.shippingState] = "shippingState"
                requestMap[Common.saveCard] = getIsSaveCard(save_card_chk.isChecked) //Yes to save card and no for don't save card
                requestMap[Common.ip] = "shippingState"

                val cardData: HashMap<String, String> = HashMap()
                cardData[Common.mobiApiKey] = mobiApiKey.trim { it <= ' ' }
                cardData[Common.loginId] = loginIdStr.trim { it <= ' ' }
                cardData[Common.CardNumber] = CardNumber
                cardData[Common.CVVNumber] = CardCvv
                cardData[Common.ExpiryMonth] = CardExpMonth
                cardData[Common.ExpiryYear] = CardExpYear

//                payment.jsonAddCard(requestMap, cardData)
                payment.jsonUPay(requestMap, cardData)

            }
        }
    }

    private fun getIsSaveCard(saveCard: Boolean): String {
        return if (saveCard) "Yes" else "No"
    }

    //Payment Connection Interface
    private val paymentResponse = object : PaymentResponse {
        override fun getCardList(success: CardList) {

        }

        override fun getCardDetail(cardList: CardDetail) {
            TODO("Not yet implemented")
        }

        override fun setPaymentSuccess(success: PaymentResult) {
            Log.e("Response Success Result", "${success.responseDescription}")

        }

        override fun setFailure(failure: String) {
            Log.e("Response Failure", "$failure")
            Toast.makeText(applicationContext, failure, Toast.LENGTH_SHORT).show()
        }

        override fun setRemoveCard(success: RemoveCardPojo) {

        }
    }
}