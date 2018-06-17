package com.cosmin.bookstore;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.cosmin.bookstore.data.BookContract;
import com.cosmin.bookstore.data.BookContract.BookEntry;
import com.cosmin.bookstore.data.BookDbHelper;

public class MainActivity extends AppCompatActivity {
    private BookDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDbHelper = new BookDbHelper(this);
    }

    /**
     * Helper method to insert hardcoded book data into the database.
     */
    private void insertBook() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and 1'st Book's attributes are the values.
        ContentValues values = new ContentValues();
        values.put(BookEntry.COLUMN_PRODUCT_NAME, "Mark Twain - The Adventures of Tom Sawyer");
        values.put(BookEntry.COLUMN_PRICE, 20);
        values.put(BookEntry.COLUMN_QUANTITY, 10);
        values.put(BookEntry.COLUMN_SUPPLIER_NAME, "Penguin Classics");
        values.put(BookEntry.COLUMN_SUPPLIER_PHONE_NUMBER, "212-366-2000");

        // Insert a new row for 1'st book in the database, returning the ID of that new row.
        long newRowId = db.insert(BookEntry.TABLE_NAME, null, values);
    }

    @Override
    protected void onStart() {
        super.onStart();
        insertBook();
        displayDatabaseInfo();
    }

    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the books database.
     */
    private void displayDatabaseInfo() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String firstTextToShow;
        //Perform a query using cursor
        String[] projection = {
                BookEntry._ID,
                BookEntry.COLUMN_PRODUCT_NAME,
                BookEntry.COLUMN_PRICE,
                BookEntry.COLUMN_QUANTITY,
                BookEntry.COLUMN_SUPPLIER_NAME,
                BookEntry.COLUMN_SUPPLIER_PHONE_NUMBER
        };
        Cursor cursor = db.query(
                BookEntry.TABLE_NAME,
                projection, null, null, null, null, null
        );

        TextView displayView = findViewById(R.id.books_text_view);
        try {
            firstTextToShow = getResources().getString((R.string.textToShowFirst), cursor.getCount());
            displayView.setText(firstTextToShow);
            displayView.append(BookEntry._ID + " - " +
                    BookEntry.COLUMN_PRODUCT_NAME + " - " +
                    BookEntry.COLUMN_PRICE + " - " +
                    BookEntry.COLUMN_QUANTITY + " - " +
                    BookEntry.COLUMN_SUPPLIER_NAME + " - " +
                    BookEntry.COLUMN_SUPPLIER_PHONE_NUMBER + "\n");

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(BookEntry._ID);
            int productNameColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_PRODUCT_NAME);
            int priceColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_PRICE);
            int quantityColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_QUANTITY);
            int supplierNameColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_SUPPLIER_NAME);
            int supplierPhoNoColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_SUPPLIER_PHONE_NUMBER);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String currentProductName = cursor.getString(productNameColumnIndex);
                int currentPrice = cursor.getInt(priceColumnIndex);
                int currentQuantity = cursor.getInt(quantityColumnIndex);
                String currentSupplierName = cursor.getString(supplierNameColumnIndex);
                String currentSupplierPhoNo = cursor.getString(supplierPhoNoColumnIndex);

                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " - " +
                        currentProductName + " - " +
                        currentPrice + " - " +
                        currentQuantity + " - " +
                        currentSupplierName + " - " +
                        currentSupplierPhoNo));
            }
        } finally {
            cursor.close();
        }
    }
}
