package com.jualkoding.ecomsepatu.model;

import com.midtrans.sdk.corekit.core.TransactionRequest;
import com.midtrans.sdk.corekit.models.BankType;
import com.midtrans.sdk.corekit.models.CustomerDetails;
import com.midtrans.sdk.corekit.models.ItemDetails;
import com.midtrans.sdk.corekit.models.snap.Authentication;
import com.midtrans.sdk.corekit.models.snap.CreditCard;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CustomerModel {

    public static CustomerDetails customerDetails() {
        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setFirstName("Boy Kurniawan");
        customerDetails.setPhone("6285273171136");
        customerDetails.setEmail("boykurniawan123@gmail.com");
        return customerDetails;
    }

    public static TransactionRequest transactionRequest (String id, int price, int qty, String name) {

        TransactionRequest transactionRequest = new TransactionRequest(System.currentTimeMillis()+ " ", 500000);
        transactionRequest.setCustomerDetails(customerDetails());

        ItemDetails details = new ItemDetails(id,price,qty,name);
        ArrayList<ItemDetails> itemDetails = new ArrayList<>();
        itemDetails.add(details);

        transactionRequest.setItemDetails(itemDetails);

        CreditCard creditCard = new CreditCard();
        creditCard.setSaveCard(false);
        creditCard.setAuthentication(Authentication.AUTH_RBA);
        creditCard.setBank(BankType.MANDIRI);

        transactionRequest.setCreditCard(creditCard);
        return transactionRequest;

    }

}
