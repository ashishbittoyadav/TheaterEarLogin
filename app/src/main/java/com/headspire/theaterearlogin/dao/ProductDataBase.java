package com.headspire.theaterearlogin.dao;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.headspire.theaterearlogin.model.ProductModel;

@Database(entities = {ProductModel.class}, version = 1, exportSchema = false)
public abstract class ProductDataBase extends RoomDatabase {

    private static volatile ProductDataBase productDataBase;
    public abstract DaoAccess daoAccess();
    static ProductDataBase getInstance(final Context context) {
        if(productDataBase==null)
        {
            synchronized (ProductDataBase.class)
            {
                if(productDataBase==null)
                {
                    productDataBase= Room.databaseBuilder(context.getApplicationContext()
                    ,ProductDataBase.class,"product_database")
                            .build();
                }
            }
        }
        return productDataBase;
    }

}
