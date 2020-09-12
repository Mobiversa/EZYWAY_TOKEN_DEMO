# EZYWAY TOKEN DEMO APPLICATION
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
     
 # WALLET FUNCTIONS

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
