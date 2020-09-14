# EZYWAY with Save Card Demo Application
Combination of EZYWAY with EZYPOD

Mandatory Gradle to Communicate with Server and SDK Library you can find under lib folder.

    implementation 'com.squareup.retrofit2:retrofit:2.7.1'
    implementation 'com.squareup.retrofit2:converter-gson:2.7.0'
    implementation 'com.squareup.okhttp3:okhttp:4.3.0'
    //SDK Library Integration
    implementation files('libs/ezyway_sdk_v4.2.aar')  

To Communicate with the payment SDK crete the Following Object

     val payment: Payment = Payment()
     //2nd param paymentResponse is the Interface to receive response in Callback method
     //3rd param -> Live data as true, Demo data as false
     Payment.getInstance(this, paymentResponse, false) 
     
Callback Inteface Method to receive response from the SDK

    //Payment Connection Interface
    private val paymentResponse = object : PaymentResponse {
        override fun getCardList(success: CardList) {
            //Here List of cards in wallet will receive
            success.responseData.cardWalletList
        }

        override fun getCardDetail(success: CardDetail) {
            //Here Receive data for Particular CardDetail
        }

        override fun setPaymentSuccess(success: PaymentResult) {
           //Here Receive Payment Success Result

        }

        override fun setFailure(failure: String) {
            //If any Error occurs During process response will receive here
        }

        override fun setRemoveCard(success: RemoveCardPojo) {
            //If Card removed successfully response will receive here
        }

        override fun getBankList(success: BankListModel) {
            //Here get the Bank List for FPX payment
        }
        
        override fun setFpxSuccess(success: FPXPaymentResult) {
            //Here get Success response after payment
        }

        override fun getBoostData(success: BoostResponse) {
            //Here get Response to generate Boost QR code
        }
        
        override fun checkBoostStatus(success: BoostStatus) {
            //Here can check the boost payment status 
        }
    }

# PAY USING CARD DETAILS

Online payment using Card Details

     val requestMap: HashMap<String, String> = HashMap()
     requestMap[Common.mobileNo] = "12345678" //Mobile number 
     requestMap[Common.NameOnCard] = "Mobiversa" //Name in Card
     requestMap[Common.email] = "test@gomobi.io" //Email Id
     requestMap[Common.orderId] = "Testing" //Invoice ID for reference
     requestMap[Common.amount] = "2.00" //Amount field must be in 2 decimal format
     requestMap[Common.postalCode] = "638661" 
     requestMap[Common.firstName] = firstName
     requestMap[Common.lastName] = lastName
     requestMap[Common.shippingState] = "shippingState"
     requestMap[Common.saveCard] = getIsSaveCard(save_card_chk.isChecked) //Yes to save card and no for don't save card
     requestMap[Common.ip] = "shippingState"

     val cardData: HashMap<String, String> = HashMap()
     cardData[Common.mobiApiKey] = mobiApiKey.trim { it <= ' ' } //get Key from our team
     cardData[Common.loginId] = loginIdStr.trim { it <= ' ' } //get Id from our team
     cardData[Common.CardNumber] = CardNumber
     cardData[Common.CVVNumber] = CardCvv
     cardData[Common.ExpiryMonth] = CardExpMonth
     cardData[Common.ExpiryYear] = CardExpYear

     payment.jsonUPay(requestMap, cardData)
     
 # Saved Card Retrive Function

Retrive the cards list saved in Wallet using Phone Number

    val requestMap: HashMap<String, String> = HashMap()
    requestMap[Common.mobiApiKey] = mobiApiKey.trim { it <= ' ' }
    requestMap[Common.loginId] = username.trim { it <= ' ' }
    requestMap[Common.mobileNo] = "919003745350"
    payment.jsonGetCardList(requestMap)
    
Retrive particular Card Details from the list of cards

    val requestMap: HashMap<String, String> = HashMap()
    requestMap[Common.mobiApiKey] = mobiApiKey.trim { it <= ' ' }
    requestMap[Common.loginId] = username.trim { it <= ' ' }
    requestMap[Common.mobileNo] = "919003745350"
    requestMap[Common.cardToken] = cardToken
    payment.jsonGetCardDetail(requestMap)
   
Pay using particular Card from the list of cards

    val requestMap: HashMap<String, String> = HashMap()
    requestMap[Common.mobiApiKey] = mobiApiKey.trim { it <= ' ' }
    requestMap[Common.loginId] = username.trim { it <= ' ' }
    requestMap[Common.mobileNo] = "919003745350"
    requestMap[Common.cardToken] = cardToken //Retrive From the Wallet card list
    requestMap[Common.invoiceId] = "Testing"
    requestMap[Common.amount] = "1.00"
    payment.jsonPayByCard(requestMap)

Remove particular Card from the list of cards

    val requestMap: HashMap<String, String> = HashMap()
    requestMap[Common.mobiApiKey] = mobiApiKey.trim { it <= ' ' }
    requestMap[Common.loginId] = username.trim { it <= ' ' }
    requestMap[Common.mobileNo] = "919003745350"
    requestMap[Common.cardToken] = cardToken
    payment.jsonRemoveCard(requestMap)
    
# Boost Payment Function

Generate Boost QR Code

    Payment.getInstance(this, paymentResponse,true)
    val paymentActivity: Payment = Payment()
    val requestMap: HashMap<String, String> = HashMap()
    requestMap["amount"] = "1.00"
    requestMap["username"] = "Mobi Demo"
    requestMap["invoiceId"] = "ORDER001"
    requestMap["mobiApiKey"] = mobiApiKey.trim { it <= ' ' }
    requestMap["loginId"] = loginId.trim { it <= ' ' }
    payment.jsonBoostTransaction(requestMap)
    
Boost response will receive in getBoostDataResponse, By using the base64ImageQRCode can generate the QR Image.


Check Boost Status
    Can get the status update of created QRCode. Untill the Payment get success status response will receive in Failure method.

    val requestMap : HashMap<String,String> = HashMap()
    requestMap["orderId"] = orderId
    requestMap["trxId"] = trxId
    requestMap["aid"] = aid
    paymentActivity.jsonCheckBoostStatus(requestMap)

#FPX Payment

Get Bank List

    To get the bank list there are 2 types of bank, Retail Banking(BankType = 1) and Corporate Banking(BankType = 2)
    For Retail Banking list access data from BankX ArrayList
    For Corporate Banking list access data from Bank ArrayList

    //Function Call
    payment.jsonBankList()
    
Payment Using FPX

       //SBI Bank A is working for demo
       val requestMap: HashMap<String, String> = HashMap()
       val amount = edit_amount.text.toString()
       requestMap["amount"] = "1.00"
       requestMap["city"] = edit_city.text.toString()
       requestMap["contactName"] = edit_contactName.text.toString()
       requestMap["postalCode"] = edit_postalCode.text.toString()
       requestMap["ip"] = edit_ip.text.toString()
       requestMap["mobileNo"] = "12345678"
       requestMap["orderId"] = "ORDER001"
       requestMap["nameOnCard"] = ""
       requestMap["invoiceId"] = "ORDER001"
       requestMap["email"] = edit_email.text.toString()
       requestMap["state"] = edit_state.text.toString()
       requestMap["orderDesc"] = edit_orderDesc.text.toString()
       requestMap["mobiApiKey"] = mobiApiKey.trim { it <= ' ' }
       requestMap["loginId"] = loginId.trim { it <= ' ' }
       requestMap["bankType"] = bankType //Get from BankList
       requestMap["bank"] = bankName //Selected bank Name
       requestMap["buyerName"] = edit_contactName.text.toString()
       Log.e("Map", "" + requestMap)
       paymentActivity.jsonPayByFPX(requestMap)
