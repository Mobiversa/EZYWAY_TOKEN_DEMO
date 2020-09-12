# EZYWAY_TOKEN_DEMO
Combination of EZYWAY with EZYPOD

# PAY USING CARD DETAILS

Online payment using Card Details

     val requestMap: HashMap<String, String> = HashMap()
     requestMap[Common.mobileNo] = "12345678
     requestMap[Common.NameOnCard] = "Mobiversa"
     requestMap[Common.email] = "test@gomobi.io"
     requestMap[Common.orderId] = "Testing"
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
    
