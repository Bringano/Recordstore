package se.yrgo.orderservice.util;

import java.time.*;
import java.time.format.*;
import java.util.*;

public final class NumberGenerator {
    private static final DateTimeFormatter DATE = DateTimeFormatter.BASIC_ISO_DATE;

    private NumberGenerator() {
    }

    public static String orderNumber() {
        return "ORD-" + DATE.format(LocalDate.now(ZoneId.systemDefault())) + "-" + randomSuffix();
    }

    public static String receiptNumber() {
        return "RCP-" + DATE.format(LocalDate.now(ZoneId.systemDefault())) + "-" + randomSuffix();
    }

    private static String randomSuffix() {
        return UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
