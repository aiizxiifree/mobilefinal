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
    }

    public void text(){
        sharedPreferences = getActivity().getSharedPreferences("sharePreference", Context.MODE_PRIVATE);
        String userIdString = sharedPreferences.getString("userID", "not found");
        String passwordString = sharedPreferences.getString("password", "not found");
        TextView textView = getView().findViewById(R.id.home_text);
        textView.setText("Hello " + userIdString + " " + passwordString + "this is my quote" + "\"today is my day\"");
    }
}
