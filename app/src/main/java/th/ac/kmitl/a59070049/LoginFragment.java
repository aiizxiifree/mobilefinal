package th.ac.kmitl.a59070049;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginFragment extends Fragment {
    EditText userIdEdittext;
    EditText passwordEdittext;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        register();
        login();
        alreadyLogin();
    }

    public void alreadyLogin(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("sharePreference", Context.MODE_PRIVATE);
        Log.d("ASD", sharedPreferences.getString("userID", "not found"));
        String state = sharedPreferences.getString("userID", "not found");
        if(state.equals("not found")){
            Log.d("ASD", "false");

        }else{
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_view, new HomeFragment())
                    .addToBackStack(null)
                    .commit();
        }
    }

    public void login(){
        Button logInbutton = getView().findViewById(R.id.login_login_btn);
        logInbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkFill()){
                    Toast.makeText(getActivity(), "Please fill out this form", Toast.LENGTH_SHORT).show();
                }else if(checkInDB()){
                    Toast.makeText(getActivity(), "Invalid User or Password", Toast.LENGTH_SHORT).show();

                }else{

                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view, new HomeFragment())
                            .addToBackStack(null)
                            .commit();
                }
            }
        });
    }

    public boolean checkInDB(){
        SQLiteDatabase db = getActivity().openOrCreateDatabase("my.db", Context.MODE_PRIVATE, null);

        db.execSQL("CREATE TABLE IF NOT EXISTS UserAccont (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " uid VARCHAR(200), name VARCHAR(200), age VARCHAR(200), password VARCHAR(200), quote VARCHAR(200))");


        final Cursor myCursor = db.rawQuery("select _id, name, age from UserAccont where uid = "+ "'" + userIdEdittext.getText().toString()+ "' and password = "+ "'" +
                passwordEdittext.getText().toString() + "'",
                null);
        Log.d("ASD", "number of row " + String.valueOf(myCursor.getCount()));
        String _id = "";
        Log.d("ASD", String.valueOf(_id.isEmpty()));
        while (myCursor.moveToNext()){
            String _idTemp = myCursor.getString(0);
            _id = _idTemp;
        }
        Log.d("ASD", String.valueOf(_id.isEmpty())+ userIdEdittext.getText().toString());
        if(_id.isEmpty()){
            return true;
        }else{
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("sharePreference", Context.MODE_PRIVATE);
            sharedPreferences.edit().putString("userID", userIdEdittext.getText().toString()).apply();
            sharedPreferences.edit().putString("password", passwordEdittext.getText().toString()).apply();

            return false;
        }
    }

    public boolean checkFill(){
        userIdEdittext = getView().findViewById(R.id.login_user_id);
        passwordEdittext = getView().findViewById(R.id.login_password);
        if(userIdEdittext.getText().toString().isEmpty() || passwordEdittext.getText().toString().isEmpty()){
            return true;
        }else{
            return false;
        }
    }


    public void register(){
        TextView textViewRegister = getView().findViewById(R.id.login_register);
        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new RegisterFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}
