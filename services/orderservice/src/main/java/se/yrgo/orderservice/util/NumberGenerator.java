package se.yrgo.orderservice.util;

import java.time.*;
import java.time.format.*;
import java.util.*;

/**
 * Utility class for generating unique receipt and order numbers.
 * <p>
 * Numbers get the format {@code PREFIX-yyyyMMdd-XXXXXXXX},
 * where date is the systems current date and the suffix is
 * eight random uppercase hex-characters derived from {@link UUID}
 * <p>
 * 
 */
public final class NumberGenerator {
    private static final DateTimeFormatter DATE = DateTimeFormatter.BASIC_ISO_DATE;

    private NumberGenerator() {
    }

    /**
     * Generates a new order number.
     * <p>
     * Example: {@code ORD-20261126-A1B2C3D4}
     *
     * @return a new order number prefixed with {@code ORD-}
     */
    public static String orderNumber() {
        return "ORD-" + DATE.format(LocalDate.now(ZoneId.systemDefault())) + "-" + randomSuffix();
    }

    /**
     * Generates a new purchase order number.
     * <p>
     * Example: {@code PO-20261126-A1B2C3D4}
     *
     * @return a new purchase order number prefixed with {@code PO-}
     */
    public static String purchaseOrderNumber() {
        return "PO-" + DATE.format(LocalDate.now(ZoneId.systemDefault())) + "-" + randomSuffix();
    }

    /**
     * Generates a new receipt number.
     * <p>
     * Example: {@code RCP-20261126-A1B2C3D4}
     *
     * @return a new receipt number prefixed with {@code RCP-}
     */
    public static String receiptNumber() {
        return "RCP-" + DATE.format(LocalDate.now(ZoneId.systemDefault())) + "-" + randomSuffix();
    }

    /**
     * Creates a random 8-character uppercase suffix.
     *
     * @return the first eight characters of a random UUID, in uppercase
     */
    private static String randomSuffix() {
        return UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
