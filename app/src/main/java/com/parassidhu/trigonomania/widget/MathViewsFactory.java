package com.parassidhu.trigonomania.widget;

import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.google.gson.Gson;
import com.parassidhu.trigonomania.MathUtils;
import com.parassidhu.trigonomania.R;
import com.parassidhu.trigonomania.model.FirstMethodModel;

public class MathViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private String packageName;
    private FirstMethodModel list;

    public MathViewsFactory(String packageName, FirstMethodModel list) {
        this.packageName = packageName;
        this.list = list;
    }

    @Override
    public void onCreate() { }

    @Override
    public void onDataSetChanged() { }

    @Override
    public void onDestroy() { }

    @Override
    public int getCount() { return 3; }

    @Override
    public RemoteViews getViewAt(int position) {
        final RemoteViews remoteViews = new RemoteViews(packageName, R.layout.widget_layout);
        try {
            remoteViews.setTextViewText(R.id.name, String.valueOf(MathUtils.sidesPlaceHolderPhi[position]));
            setData(remoteViews, position);
        } catch (Exception e) {
            return null;
        }
        return remoteViews;
    }

    private void setData(RemoteViews remoteViews, int position) {
        Gson gson = new Gson();
        String[] dataArray = gson.fromJson(list.getData(), String[].class);
        String[] resultArray = new String[dataArray.length];
        for (int i = 0; i < dataArray.length; i++) {
            resultArray[i] = String.valueOf(Math.round(Double.valueOf(dataArray[i]) * 100) / 100.0);
        }

        remoteViews.setTextViewText(R.id.value, resultArray[position]);
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
