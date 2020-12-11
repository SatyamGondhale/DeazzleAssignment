package com.deazzle.deazzleassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.deazzle.deazzleassignment.Adapter.RandomUserListAdapter;
import com.deazzle.deazzleassignment.database.RandomUser;
import com.deazzle.deazzleassignment.model.Coordinates;
import com.deazzle.deazzleassignment.model.Dob;
import com.deazzle.deazzleassignment.model.Example;
import com.deazzle.deazzleassignment.model.Id;
import com.deazzle.deazzleassignment.model.Location;
import com.deazzle.deazzleassignment.model.Login;
import com.deazzle.deazzleassignment.model.Name;
import com.deazzle.deazzleassignment.model.Picture;
import com.deazzle.deazzleassignment.model.Registered;
import com.deazzle.deazzleassignment.model.Result;
import com.deazzle.deazzleassignment.viewmodel.MainActivityViewModel;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.Duration;
import com.yuyakaido.android.cardstackview.StackFrom;
import com.yuyakaido.android.cardstackview.SwipeAnimationSetting;
import com.yuyakaido.android.cardstackview.SwipeableMethod;

import java.util.List;

public class MainActivity extends AppCompatActivity implements CardStackListener {

    MainActivityViewModel mainActivityViewModel;
    CardStackView userCardsList;
    RandomUserListAdapter adapter;
    CardStackLayoutManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userCardsList=findViewById(R.id.user_cards);
        manager=new CardStackLayoutManager(getApplicationContext());
      //  userCardsList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mainActivityViewModel=new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(MainActivityViewModel.class);
        mainActivityViewModel.getDataFromVM().observe(this, new Observer<Example>() {
            @Override
            public void onChanged(Example result) {
                List<Result> getResult= result.getResults();
                for(int i=0;i<getResult.size();i++){
                    if(getResult!=null && getResult.size()>0){
                        String gender= getResult.get(i).getGender();
                        Name name= getResult.get(i).getName();
                        Location location= getResult.get(i).getLocation();
                        String email= getResult.get(i).getEmail();
                        Login login= getResult.get(i).getLogin();
                        Dob dob= getResult.get(i).getDob();
                        Registered registered= getResult.get(i).getRegistered();
                        String phone= getResult.get(i).getPhone();
                        Id userID= getResult.get(i).getId();
                        Picture picture= getResult.get(i).getPicture();
                        String nationality= getResult.get(i).getNat();
                        RandomUser randomUser=new RandomUser(gender,name,location,email,login,dob,registered,phone,userID,picture,nationality);
                        mainActivityViewModel.insertUserVM(randomUser);
                    }
                }
                    adapter=new RandomUserListAdapter(MainActivity.this,getResult);
                userCardsList.setLayoutManager(manager);
                manager.setStackFrom(StackFrom.None);
                manager.setVisibleCount(1);
                manager.setTranslationInterval(8.0f);
                manager.setScaleInterval(0.95f);
                manager.setSwipeThreshold(0.3f);;
                manager.setMaxDegree(20.0f);
                manager.setDirections(Direction.HORIZONTAL);
                manager.setCanScrollHorizontal(true);
                manager.setCanScrollVertical(true);
                manager.setSwipeableMethod(SwipeableMethod.Manual);
                    userCardsList.setAdapter(adapter);
            }
        });
    }

    private void swipeLeft(int position) {

    }

    @Override
    public void onCardDragging(Direction direction, float ratio) {

    }

    @Override
    public void onCardSwiped(Direction direction) {
        if(direction==Direction.Left){
            Toast.makeText(this,"Hello Left",Toast.LENGTH_SHORT).show();
        }else if(direction==Direction.Right){
            Toast.makeText(this,"Hello Right",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onCardRewound() {

    }

    @Override
    public void onCardCanceled() {

    }

    @Override
    public void onCardAppeared(View view, int position) {

    }

    @Override
    public void onCardDisappeared(View view, int position) {

    }
}