package com.gknsvs.javamaptravelbook.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Place.class},version = 1)
public abstract class PlaceDatabase extends RoomDatabase {
    public abstract PlaceDao placeDao();
}
