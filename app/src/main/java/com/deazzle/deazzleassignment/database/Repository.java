package com.deazzle.deazzleassignment.database;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.deazzle.deazzleassignment.api.Api;
import com.deazzle.deazzleassignment.api.ApiInterface;
import com.deazzle.deazzleassignment.model.Example;
import com.deazzle.deazzleassignment.model.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private static Repository repository;
    private ApiInterface apiInterface;
    private RandomUserDAO dao;
    private MutableLiveData<Example> getResponseList;
    private List<Result> getResponseCacheList;

    public Repository(Application application){
        UsersDatabase database=UsersDatabase.getDatabase(application);
        dao=database.userDao();
        apiInterface= Api.getClient();
        getResponseList=new MutableLiveData<Example>();
        getResultData();
    }

    public void getResultData(){
        UsersDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.emptyTable();
            }
        });
        Call<Example> apiData=apiInterface.getResultData(10);
        apiData.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if(response.isSuccessful()){
                  //  List<Result> getList=response.body().getResults();
                    getResponseList.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });
    }

    public LiveData<Example>getDataFromRepository(){
        return getResponseList;
    }

    public void insertUser(final RandomUser user){
        UsersDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.insertUser(user);
            }
        });
    }

}
