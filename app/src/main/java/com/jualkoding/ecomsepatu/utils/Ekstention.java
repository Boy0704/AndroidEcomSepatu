package com.jualkoding.ecomsepatu.utils;

import java.text.NumberFormat;
import java.util.Locale;

public class Ekstention {

    public String convertPrice (int priceParameter) {
        Locale locale = new Locale("id","ID");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);

        return numberFormat.format(priceParameter);
    }

    public String convertPrice (String priceParameter) {
        Locale locale = new Locale("id","ID");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);

        Double price = Double.parseDouble(priceParameter);

        return numberFormat.format(price);
    }

}
