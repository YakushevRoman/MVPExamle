package e.user301.mvpexamle;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import e.user301.mvpexamle.Common.User;
import e.user301.mvpexamle.Common.UserAdapter;
import e.user301.mvpexamle.Common.UserTable;
import e.user301.mvpexamle.DataBase.Dbhelper;

public class SingleActivity extends AppCompatActivity {
    public static final String TAG = "UserAdapter";
    private UserAdapter rUserAdapter;
    private Dbhelper rDbhelper;

    private EditText rEditTextName;
    private EditText rEditTextEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);
        init();
    }

    private void init() {
        rEditTextName = findViewById(R.id.edit_text_name);
        rEditTextEmail = findViewById(R.id.edit_text_email);

        findViewById(R.id.button_add_user).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
                Log.d(TAG, "onClick: addUser();");
            }
        });

        findViewById(R.id.button_clear_all).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        rUserAdapter = new UserAdapter();
        RecyclerView recyclerView = findViewById(R.id.recycler_view_single_activity);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(rUserAdapter);

        rDbhelper = new Dbhelper(this);
    }

    private void addUser() {
        String name = rEditTextName.getText().toString();
        String email = rEditTextEmail.getText().toString();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email)){
            Toast.makeText(this, "net data", Toast.LENGTH_SHORT).show();
            return;
        }

        ContentValues values = new ContentValues();
        values.put(UserTable.COLUMNS.NAME, name);
        values.put(UserTable.COLUMNS.EMAIL, email);

        AddUserTask addUserTask = new AddUserTask();
        addUserTask.execute(values);
        rEditTextName.setText("");
        rEditTextEmail.setText("");
        Log.d(TAG, String.format("addUser: name : %s , email: %s", name, email));

    }

    class AddUserTask extends AsyncTask<ContentValues, Void, Void>{

        @Override
        protected Void doInBackground(ContentValues... contentValues) {
            ContentValues values = contentValues[0];
            rDbhelper.getWritableDatabase().insert(UserTable.TABLE, null, values);
            try{
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            loadUsers();
        }
    }

    private void loadUsers() {
        LoadUsers loadUsers = new LoadUsers();
        loadUsers.execute();
    }

    private class LoadUsers extends AsyncTask<Void, Void, List<User>>{

        @Override
        protected List<User> doInBackground(Void... voids) {
            List<User> users = new LinkedList<>();
            Cursor cursor = rDbhelper.getReadableDatabase().query(UserTable.TABLE, null, null, null, null, null, null);
            while (cursor.moveToNext()){
                User user = new User();
                user.setId(cursor.getInt(cursor.getColumnIndex(UserTable.COLUMNS.ID)));
                user.setName(cursor.getString(cursor.getColumnIndex(UserTable.COLUMNS.NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(UserTable.COLUMNS.EMAIL)));
                users.add(user);
            }
            return users;
        }

        @Override
        protected void onPostExecute(List<User> users) {
            super.onPostExecute(users);
            rUserAdapter.setData(users);
        }
    }
}
