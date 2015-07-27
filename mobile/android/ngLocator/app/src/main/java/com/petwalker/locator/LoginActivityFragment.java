package com.petwalker.locator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ngworks.locator.R;

public class LoginActivityFragment extends Fragment {

    private static final String TAG = LoginActivityFragment.class.toString();

    private View view;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private EditText login;
    private EditText password;

    private boolean active;

    public LoginActivityFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");

        active = true;

        view = inflater.inflate(R.layout.fragment_login, container, false);
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        Button button = (Button) view.findViewById(R.id.login_btn);
        login = (EditText) view.findViewById(R.id.login);
        password = (EditText) view.findViewById(R.id.password);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.login_btn) {
                     if ("".equals(login.getText().toString()) || login.getText().toString() == null) {

                        Toast.makeText(view.getContext(), "Invalid login!", Toast.LENGTH_LONG).show();

                     } else {

                        final OptionsFragment optionsFragment = OptionsFragment.newInstance("Jaja", "z majonezem");


                        int cx = (view.getLeft() + view.getRight()) / 2;
                        int cy = (view.getTop() + view.getBottom()) / 2;

                        int initialRadius = view.getWidth();

                        Animator anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, initialRadius, 0);

                        view.setVisibility(View.VISIBLE);
                        anim.start();
                        anim.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                fragmentTransaction.replace(R.id.container, optionsFragment);
                                fragmentTransaction.commit();
                            }
                        });
                     }

                }
            }
        });
        return view;
    }

    public interface OnLoginFragmentInteractionListener {
        void onMainFragmentInteraction(Uri uri);
    }

}
