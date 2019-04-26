package com.headspire.theaterearlogin.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.headspire.theaterearlogin.model.ProductModel;

import java.util.List;

@Dao
public interface DaoAccess {
    @Insert
    void insertData(ProductModel... productModel);

    @Query("Select * from ProductModel")
    LiveData<List<ProductModel>> getdata();
}
