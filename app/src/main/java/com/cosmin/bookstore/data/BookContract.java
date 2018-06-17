package com.cosmin.bookstore.data;

import android.provider.BaseColumns;


/**
 * API Contract for the BookStore app.
 */
public final class BookContract {

    private BookContract() {
    }

    /**
     * Inner class that defines constant values for the books database table.
     * Each entry in the table represents a single book.
     */
    public static final class BookEntry implements BaseColumns {

        public final static String TABLE_NAME = "books";

        public final static String _ID = BaseColumns._ID;

        /**
         * Product Name.
         * <p>
         * Type: TEXT
         */
        public final static String COLUMN_PRODUCT_NAME = "product_name";

        /**
         * Price of the book.
         * <p>
         * Type: INTEGER
         */
        public final static String COLUMN_PRICE = "price";

        /**
         * Quantity of books.
         * <p>
         * Type: INTEGER
         */
        public final static String COLUMN_QUANTITY = "quantity";

        /**
         * Supplier Name.
         * <p>
         * Type: TEXT
         */
        public final static String COLUMN_SUPPLIER_NAME = "supplier_name";

        /**
         * Supplier phone number.
         * <p>
         * Type: TEXT
         */
        public final static String COLUMN_SUPPLIER_PHONE_NUMBER = "supplier_phone_number";
    }
}