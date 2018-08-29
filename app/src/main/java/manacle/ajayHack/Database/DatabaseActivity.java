package manacle.ajayHack.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import manacle.ajayHack.DataModal.UserLoginModal;
import manacle.ajayHack.Interface.Dao;

@Database(entities = UserLoginModal.class , version = 1 , exportSchema = false)
public abstract class DatabaseActivity extends RoomDatabase {

    public abstract Dao dao();
}
