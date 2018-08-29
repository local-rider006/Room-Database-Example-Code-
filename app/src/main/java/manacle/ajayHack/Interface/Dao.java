package manacle.ajayHack.Interface;

import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import manacle.ajayHack.DataModal.UserLoginModal;

@android.arch.persistence.room.Dao
public interface Dao {

    @Insert
    void insertUserLoginCredentials(UserLoginModal userLoginModal);

    @Query("SELECT * FROM UserLoginModal")
    List<UserLoginModal> getAllData();

    @Query("DELETE FROM UserLoginModal WHERE UserLoginModal.userName = :username")
    void delete(String username);
}
