package manacle.ajayHack.Activity;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import manacle.ajayHack.DataModal.UserLoginModal;
import manacle.ajayHack.Database.DatabaseActivity;
import manacle.ajayHack.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.listView)
    ListView listView;
    private DatabaseActivity databaseActivity;
    private final String database_name = "users_db";
    private List<String> dataList;
    private List<UserLoginModal> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initialize();

        //ListView OnClick Event:-
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                databaseActivity.dao().delete(list.get(position).getUserName());
                readData();
            }
        });

        //ListView onLong Click Event:-
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "UserName : " + list.get(position).getUserName(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    private void initialize() {
        btnLogin.setOnClickListener(this);
        databaseActivity = Room.databaseBuilder(MainActivity.this,
                DatabaseActivity.class,
                database_name).allowMainThreadQueries().build();
    }

    private void insertData() {
        UserLoginModal userLoginModal = new UserLoginModal(etUsername.getText().toString().trim(),
                etPassword.getText().toString().trim());
        databaseActivity.dao().insertUserLoginCredentials(userLoginModal);
        etUsername.setText("");
        etPassword.setText("");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                if(etUsername.getText().toString().trim().isEmpty()){
                    Toast.makeText(MainActivity.this, "UserName field could not be empty !", Toast.LENGTH_SHORT).show();
                }else if(etPassword.getText().toString().trim().isEmpty()){
                    Toast.makeText(MainActivity.this, "Password field could not be empty !", Toast.LENGTH_SHORT).show();
                }else {
                    insertData();
                    readData();
                }
                break;
        }
    }

    private void readData() {
        list = databaseActivity.dao().getAllData();
        dataList = new ArrayList<>();
        dataList.clear();
        for(int i=0; i<list.size(); i++){
            dataList.add(list.get(i).getUserName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this , android.R.layout.simple_list_item_1 , dataList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }
}
