package com.parassidhu.trigonomania;

import android.app.Application;

import com.parassidhu.trigonomania.database.MathDao;
import com.parassidhu.trigonomania.database.MathDatabase;
import com.parassidhu.trigonomania.model.FirstMethodModel;
import com.parassidhu.trigonomania.model.SecondMethodModel;

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
}
