package com.ngworks.nglocator;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainActivityFragment extends Fragment {

    private static final String TAG = MainActivityFragment.class.toString();

    private View view;

    private FragmentManager fragmentManager = getFragmentManager();
    private FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);
        Button button = (Button)view.findViewById(R.id.login_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"button click called");
                if (v.getId() == R.id.login_btn) {
                    OptionsFragment optionsFragment = OptionsFragment.newInstance("Jaja","z majonezem");
                    fragmentTransaction.replace(R.id.welcome_fragment, optionsFragment);
                    fragmentTransaction.addToBackStack(null);

                    fragmentTransaction.commit();
                }
            }
        });
        return view;
    }


}
