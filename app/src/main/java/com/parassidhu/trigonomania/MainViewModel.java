package com.parassidhu.trigonomania;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.parassidhu.trigonomania.model.FirstMethodModel;
import com.parassidhu.trigonomania.model.SecondMethodModel;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private TrigonometricRepo tRepo;

    public MainViewModel(@NonNull Application application) {
        super(application);
        tRepo = new TrigonometricRepo(application);
    }

    void insertInFirstMethod(FirstMethodModel calculation) { tRepo.insertInFirstMethod(calculation) ;}

    void insertInSecondMethod(SecondMethodModel calculation) { tRepo.insertInSecondMethod(calculation); }

    LiveData<List<FirstMethodModel>> getFirstMethodData(){ return tRepo.getFirstMethodData(); }

    LiveData<List<SecondMethodModel>> getSecondMethodData(){ return tRepo.getSecondMethodData(); }

    void deleteAllFirstMethod(List<FirstMethodModel> list){ tRepo.deleteAllFirstMethod(list); }

    void deleteAllSecondMethod(List<SecondMethodModel> list){ tRepo.deleteAllSecondMethod(list); }

}
