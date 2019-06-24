package com.example.slimify;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import java.util.ArrayList;
import java.util.List;

public class GetUsers extends AsyncTaskLoader<List<User>> {

    private DatabaseHelper mDatabaseHelper;

    public GetUsers(Context context) {
        super(context);
        mDatabaseHelper = new DatabaseHelper(context);
    }

    @Nullable
    @Override
    public List<User> loadInBackground() {
        Cursor data = mDatabaseHelper.getData();

        List<User> deviceList = new ArrayList<>();

        while(data.moveToNext()){
            //get the value from the database in column 1
            //then add it to the ArrayList
            deviceList.add(new User(data.getString(1), data.getString(2)));
        }

        return deviceList;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
