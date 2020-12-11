package com.deazzle.deazzleassignment.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.deazzle.deazzleassignment.database.RandomUser;
import com.deazzle.deazzleassignment.database.Repository;
import com.deazzle.deazzleassignment.model.Example;
import com.deazzle.deazzleassignment.model.Result;

import java.util.List;


public class MainActivityViewModel extends AndroidViewModel {

    LiveData<Example> getResultList;
    Repository repository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        repository=new Repository(application);
        getResultList=repository.getDataFromRepository();
    }

    public LiveData<Example> getDataFromVM(){
        return getResultList;
    }

    public void insertUserVM(RandomUser user){
        repository.insertUser(user);
    }


}
