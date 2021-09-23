package com.gknsvs.javamaptravelbook.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.gknsvs.javamaptravelbook.R;
import com.gknsvs.javamaptravelbook.adapter.PlaceAdapter;
import com.gknsvs.javamaptravelbook.databinding.ActivityMainBinding;
import com.gknsvs.javamaptravelbook.model.Place;
import com.gknsvs.javamaptravelbook.model.PlaceDao;
import com.gknsvs.javamaptravelbook.model.PlaceDatabase;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private CompositeDisposable compositeDisposable=new CompositeDisposable();
    PlaceDao placeDao;
    PlaceDatabase placeDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);

        placeDatabase= Room.databaseBuilder(getApplicationContext(),PlaceDatabase.class,"places").build();
        placeDao=placeDatabase.placeDao();

        compositeDisposable.add(placeDao.getAll().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(MainActivity.this::handleResponse));
    }
    private void handleResponse(List<Place> placeList){
        binding.recyList.setLayoutManager(new LinearLayoutManager(this));
        PlaceAdapter placeAdapter=new PlaceAdapter(placeList);
        binding.recyList.setAdapter(placeAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_activitymain, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.addplace) {
            Intent intent = new Intent(MainActivity.this, MapsActivity.class);
            intent.putExtra("info","new");
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}