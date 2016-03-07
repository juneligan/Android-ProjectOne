package com.project_one.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.project_one.contract.TableData;
import com.project_one.model.OrderDetail;
import com.project_one.utils.DatabaseHelper;

import java.util.List;

/**
 * Created by JenuNagil on 9/20/2015.
 */
public class OrderDetailDaoimpl implements OrderDetailDao {

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private Context context;
    private String[] allColumns = {
            TableData.TableOrderDetail._ID,
            TableData.TableCategory.NAME
    };

    public OrderDetailDaoimpl(Context context) {
        this.context = context;
        dbHelper = DatabaseHelper.getInstance(context);
        open();
    }

    private void open() {
        database = dbHelper.getWritableDatabase();
    }

    private void close() {
        dbHelper.close();
    }

    @Override
    public List<OrderDetail> fetchAllAvailableItems() {

//        Cursor cursor = database.query(TableData.TableOrderDetail.TABLE_NAME, allColumns,
//                TableData.TableCategory.NAME + " = ?",
//                new String[] { String.valueOf(name)}, null, null, null, null);
//
//        cursor.moveToFirst();
//        return cursor.getCount() > 0 ? cursorToCategory(cursor) : null;
        return null;
    }
}
