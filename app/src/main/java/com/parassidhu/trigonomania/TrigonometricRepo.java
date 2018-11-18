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

    public void insertInFirstMethod(final FirstMethodModel calculation) {
        AsyncWrapper.run(new AsyncWrapper.Task() {
            @Override
            public void during() {
                mathDao.insertInFirstMethod(calculation);
            }
        });
    }

    public void insertInSecondMethod(final SecondMethodModel calculation) {
        AsyncWrapper.run(new AsyncWrapper.Task() {
            @Override
            public void during() {
                mathDao.insertInSecondMethod(calculation);
            }
        });
    }

    public LiveData<List<FirstMethodModel>> getFirstMethodData(){
        return mathDao.getFirstMethodData();
    }

    public LiveData<List<SecondMethodModel>> getSecondMethodData(){
        return mathDao.getSecondMethodData();
    }

    public void deleteAllFirstMethod(final List<FirstMethodModel> list){
        AsyncWrapper.run(new AsyncWrapper.Task() {
            @Override
            public void during() {
                mathDao.deleteAllFirstMethod(list);
            }
        });
    }

    public void deleteAllSecondMethod(final List<SecondMethodModel> list){
        AsyncWrapper.run(new AsyncWrapper.Task() {
            @Override
            public void during() {
                mathDao.deleteAllSecondMethod(list);
            }
        });
    }
}
