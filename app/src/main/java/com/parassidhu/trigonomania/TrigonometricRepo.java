package com.parassidhu.trigonomania;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.parassidhu.trigonomania.database.MathDao;
import com.parassidhu.trigonomania.database.MathDatabase;
import com.parassidhu.trigonomania.model.FirstMethodModel;
import com.parassidhu.trigonomania.model.SecondMethodModel;

import java.util.List;

public class TrigonometricRepo {

    private MathDao mathDao;

    public TrigonometricRepo(Application application) {
        MathDatabase database = MathDatabase.getDatabase(application);
        mathDao = database.mathDao();
    }

    public void insertInFirstMethod(FirstMethodModel calculation) {
        mathDao.insertInFirstMethod(calculation);
    }

    public void insertInSecondMethod(SecondMethodModel calculation) {
        mathDao.insertInSecondMethod(calculation);
    }

    public LiveData<List<FirstMethodModel>> getFirstMethodData(){
        return mathDao.getFirstMethodData();
    }

    public LiveData<List<SecondMethodModel>> getSecondMethodData(){
        return mathDao.getSecondMethodData();
    }

    public void deleteAllFirstMethod(List<FirstMethodModel> list){ mathDao.deleteAllFirstMethod(list); }

    public void deleteAllSecondMethod(List<SecondMethodModel> list){ mathDao.deleteAllSecondMethod(list); }
}
