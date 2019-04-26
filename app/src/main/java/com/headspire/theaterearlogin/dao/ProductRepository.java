package com.headspire.theaterearlogin.dao;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import com.headspire.theaterearlogin.model.ProductModel;
import java.util.List;

public class ProductRepository {
    private DaoAccess daoAccess;
    private LiveData<List<ProductModel>> products;
    public ProductRepository(Application application)
    {
        ProductDataBase productDataBase=ProductDataBase.getInstance(application);
        daoAccess =productDataBase.daoAccess();
        products=daoAccess.getdata();
    }
}
