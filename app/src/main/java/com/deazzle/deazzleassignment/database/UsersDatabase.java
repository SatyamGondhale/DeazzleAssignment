package com.deazzle.deazzleassignment.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {RandomUser.class},exportSchema = false,version = 1)
public abstract class UsersDatabase extends RoomDatabase {
    public abstract RandomUserDAO userDao();
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private static volatile UsersDatabase INSTANCE;
    public static  UsersDatabase getDatabase(final Context context){
        if(INSTANCE==null){
            synchronized (UsersDatabase.class){
                if(INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context,UsersDatabase.class, "users_data")
                            //.addMigrations(MIGRATION_3_4)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
