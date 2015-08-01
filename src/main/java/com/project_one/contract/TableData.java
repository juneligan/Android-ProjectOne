package com.project_one.contract;

import android.provider.BaseColumns;

/**
 * Created by JenuNagil on 7/28/2015.
 */
public class TableData {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public TableData() {}

    /* Inner class that defines the table contents */
    public static abstract class TableUser implements BaseColumns {
        public static final String TABLE_NAME = "User";
        public static final String USERNAME = "username";
        public static final String PASSWORD = "password";
        public static final String ROLE_ID = "role_id";
    }

    public static abstract class TableRole implements BaseColumns {
        public static final String TABLE_NAME = "Role";
        public static final String TYPE = "type";
    }

    public static abstract class TableCategory implements BaseColumns {
        public static final String TABLE_NAME = "Category";
        public static final String NAME = "name";
    }

    public static abstract class TableOrderDetail implements BaseColumns {
        public static final String TABLE_NAME = "OrderDetail";
        public static final String PRODUCT_ID = "product_id";
        public static final String UNIT_PRICE = "unit_price";
        public static final String QUANTITY = "quantity";
    }

    public static abstract class TableSalesOrder implements BaseColumns {
        public static final String TABLE_NAME = "SalesOrder";
        public static final String USER_ID = "user_id";
        public static final String ORDER_DETAIL_ID = "order_detail_id";
        public static final String AMOUNT = "amount";
    }

    public static abstract class TableProduct implements BaseColumns {
        public static final String TABLE_NAME = "Product";
        public static final String CATEGORY_ID = "category_id";
        public static final String PRODUCT_NAME = "product_name";
        public static final String UNIT_PRICE = "unit_price";
    }

    public static abstract class TableInventoryItem implements BaseColumns {
        public static final String TABLE_NAME = "InventoryItem";
        public static final String PRODUCT_ID = "product_id";
        public static final String INVENTORY_QUANTITY = "inventory_quantity";

    }
}
