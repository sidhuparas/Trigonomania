package com.parassidhu.trigonomania;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.parassidhu.trigonomania.model.FirstMethodModel;
import com.parassidhu.trigonomania.model.SecondMethodModel;

public class MainViewModel extends AndroidViewModel {

    private TrigonometricRepo tRepo;

    public MainViewModel(@NonNull Application application) {
        super(application);
        tRepo = new TrigonometricRepo(application);
    }

    public void insertInFirstMethod(FirstMethodModel calculation) { tRepo.insertInFirstMethod(calculation) ;}

    public void insertInSecondMethod(SecondMethodModel calculation) { tRepo.insertInSecondMethod(calculation); }
}
