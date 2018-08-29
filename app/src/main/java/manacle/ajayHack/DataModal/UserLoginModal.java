package manacle.ajayHack.DataModal;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class UserLoginModal {

    private String userName;
    @NonNull
    @PrimaryKey
    private String userPassword;

    public UserLoginModal(String userName, @NonNull String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @NonNull
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(@NonNull String userPassword) {
        this.userPassword = userPassword;
    }
}
