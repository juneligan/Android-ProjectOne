package com.project_one.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.activeandroid.query.Select;
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
    public SalesOrderDaoImpl() {
    }

    @Override
    public List<SalesOrder> fetchAllOrders() {
        return new Select().from(SalesOrder.class).execute();
    }
}
