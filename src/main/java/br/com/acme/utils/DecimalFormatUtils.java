package br.com.acme.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class DecimalFormatUtils {
    public static String formatToDecimal(BigDecimal value) {
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        return decimalFormat.format(value);
    }
}
