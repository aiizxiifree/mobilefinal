package th.ac.kmitl.a59070049;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class HomeFragment extends Fragment {
    SharedPreferences sharedPreferences;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        text();
        signOut();
        profile();
        friend();


    }

    public void friend(){
        Button friendBtn = getView().findViewById(R.id.home_my_friend);
        friendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new FriendFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
    public void profile(){
        Button profileBtn = getView().findViewById(R.id.home_profile_setup);
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new ProfileFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    public void text(){
        sharedPreferences = getActivity().getSharedPreferences("sharePreference", Context.MODE_PRIVATE);
        String userIdString = sharedPreferences.getString("userID", "not found");
        String passwordString = sharedPreferences.getString("password", "not found");
        String quoteString = sharedPreferences.getString("quote", "not found");
        TextView textView = getView().findViewById(R.id.home_text);
        textView.setText("Hello " + userIdString + " " + passwordString + "this is my quote" + "\""+ "today is my day" + "\"");
    }

    public void signOut(){
        Button signOutButton = getView().findViewById(R.id.home_sign_out);
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.edit().clear().apply();
//                sharedPreferences.edit().remove("userID");
//                sharedPreferences.edit().remove("password");

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new LoginFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }


}
