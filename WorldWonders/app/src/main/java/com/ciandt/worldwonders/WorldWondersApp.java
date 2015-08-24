package com.ciandt.worldwonders;

import android.app.Application;
import android.util.Log;

import com.ciandt.worldwonders.database.WondersSQLiteHelper;
import com.facebook.stetho.Stetho;

import java.io.IOException;

/**
 * Created by jfranco on 8/24/15.
 */
public class WorldWondersApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        if (!WondersSQLiteHelper.checkOpenDatabase()) {
            try {
                WondersSQLiteHelper.createDatabase(this);
            } catch (IOException e) {
                Log.i("WorldWondersApp", "Falha ao criar base de dados", e);
            }
        }

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(
                                Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(
                                Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }
}
