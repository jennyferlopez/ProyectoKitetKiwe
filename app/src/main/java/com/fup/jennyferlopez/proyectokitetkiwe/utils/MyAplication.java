package com.fup.jennyferlopez.proyectokitetkiwe.utils;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by jlo-1 on 03/04/2018.
 */

public class MyAplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .name("tasky.realm")
                .schemaVersion(0)
                .build();
        Realm.setDefaultConfiguration(realmConfig);
    }
}
