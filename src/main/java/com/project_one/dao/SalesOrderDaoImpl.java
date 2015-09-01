package com.project_one.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.project_one.contract.TableData;
import com.project_one.model.OrderDetail;
import com.project_one.model.SalesOrder;
import com.project_one.model.User;
import com.project_one.utils.DatabaseHelper;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JenuNagil on 8/31/2015.
 */
public class SalesOrderDaoImpl implements SalesOrderDao {
    public static final String TAG = "SalesOrderDao";

    private Context context;
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private String[] allSalesOrderColumns = {
            TableData.TableSalesOrder._ID,
            TableData.TableSalesOrder.USER_ID,
            TableData.TableSalesOrder.AMOUNT,
            TableData.TableSalesOrder.CREATED_DATE,
    };
    private void open() {
        database = dbHelper.getWritableDatabase();
    }

    private void close() {
        dbHelper.close();
    }
    public SalesOrderDaoImpl(Context context) {
        this.context = context;
        dbHelper = DatabaseHelper.getInstance(context);
        open();
    }

    @Override
    public List<SalesOrder> fetchAllOrders() {

        System.out.println("tesst");
        //TODO query user and order detail
        String query = "SELECT SalesOrder._id, SalesOrder.user_id, User.username," +
                "SalesOrder.amount, SalesOrder.created_date " +
                "FROM SalesOrder INNER JOIN User ON SalesOrder.user_id = User._id;";

        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        List<SalesOrder> listOfOrders = new ArrayList<SalesOrder>();
        while(!cursor.isAfterLast()) {
            SalesOrder newOrder = cursorToSalesOrder(cursor);
            listOfOrders.add(newOrder);
            cursor.moveToNext();
        }
        cursor.close();
        return listOfOrders;
    }

    private SalesOrder cursorToSalesOrder(Cursor cursor) {

        if(cursor.getCount() == 0) {
            return null;
        }

        SalesOrder order = new SalesOrder();
        order.setId(cursor.getLong(0));

        User user = new User();
        user.setId(cursor.getLong(1));
        user.setUsername(cursor.getString(2));

        order.setUser(user);

        order.setAmount(new BigDecimal(cursor.getString(3)));
        order.setDateCreated(new Date(cursor.getLong(4)));
        return order;
    }
}
