package com.parassidhu.trigonomania.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViewsService;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.parassidhu.trigonomania.TrigonometricRepo;
import com.parassidhu.trigonomania.model.FirstMethodModel;

import java.util.List;

public class MathListWidgetService extends RemoteViewsService {

    public static Intent createIntent(Context context, int appWidgetId) {
        Intent intent = new Intent(context, MathListWidgetService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
        return intent;
    }

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        Gson gson = new Gson();
        TrigonometricRepo.initSharedPreferences(this);
        FirstMethodModel list =  gson.fromJson(TrigonometricRepo.getData(),
                new TypeToken<FirstMethodModel>(){
                }.getType());

        return new MathViewsFactory(getPackageName(), list);
    }
}
