package th.ac.kmitl.a59070049;

import android.content.ContentValues;
import android.content.Context;
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
import android.widget.Toast;

public class RegisterFragment extends Fragment {
    EditText useridEdittext;
    EditText nameEdittext;
    EditText ageEdittext;
    EditText passswordEdittext;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        confirmRegister();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }


    public void confirmRegister(){
        Button registerButton = getView().findViewById(R.id.register_register_btn);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkUserId() == false){
                    Toast.makeText(getActivity(), "User ID Invalid at least 6 and less than 13.", Toast.LENGTH_SHORT).show();
                }else if(checkName() == false){
                    Toast.makeText(getActivity(), "Invalid Name Example 'Name Sirname'.", Toast.LENGTH_SHORT).show();
                }else if(checkAge() == false){
                    Toast.makeText(getActivity(), "Age between 10 - 80.", Toast.LENGTH_SHORT).show();
                }else if(checkPassword() == false){
                    Toast.makeText(getActivity(), "Password atleast 7 character.", Toast.LENGTH_SHORT).show();
                }else{
                    createDB();
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view, new LoginFragment())
                            .addToBackStack(null)
                            .commit();
                }
            }
        });
    }
    public void createDB(){
        SQLiteDatabase db = getActivity().openOrCreateDatabase("my.db", Context.MODE_PRIVATE, null);

        db.execSQL("CREATE TABLE IF NOT EXISTS UserAccont (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " uid VARCHAR(200), name VARCHAR(200), age VARCHAR(200), password VARCHAR(200)");

        ContentValues contentValues = new ContentValues();
        contentValues.put("uid", useridEdittext.getText().toString());
        contentValues.put("name", nameEdittext.getText().toString());
        contentValues.put("age", ageEdittext.getText().toString());
        contentValues.put("password", passswordEdittext.getText().toString());
        contentValues.put("quote", "today is my day");
        db.insert("UserAccont", null, contentValues);
        db.close();
        Log.d("ASD", "finish");

    }


    public boolean checkUserId(){
        useridEdittext = getView().findViewById(R.id.register_user_id);
        String userIdString = useridEdittext.getText().toString();
        if(userIdString.length() > 5 && userIdString.length() < 13){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkName(){
        nameEdittext = getView().findViewById(R.id.register_name);
        String nameString  = nameEdittext.getText().toString();

        if((nameString.split(" ")).length == 2){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkAge(){
        ageEdittext = getView().findViewById(R.id.register_age);
        Log.d("ASD", String.valueOf(ageEdittext.getText().toString().isEmpty()));
        if(String.valueOf(ageEdittext.getText().toString()).isEmpty()){
            return false;
        }
        int ageInteger = Integer.parseInt(ageEdittext.getText().toString());
        Log.d("ASD", String.valueOf(ageInteger));

        if(ageInteger > 9 && ageInteger < 81){
            return true;
        }else {
            return false;
        }
    }

    public boolean checkPassword(){
        passswordEdittext =getView().findViewById(R.id.register_password);
        String passwordString = passswordEdittext.getText().toString();
        if(passwordString.length() > 6 ){
            return true;
        }else{
            return false;
        }
    }

}
