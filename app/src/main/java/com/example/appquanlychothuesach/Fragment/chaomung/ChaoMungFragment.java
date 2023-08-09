package com.example.appquanlychothuesach.Fragment.chaomung;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.appquanlychothuesach.Adapter.ViewPagerAdapter;
import com.example.appquanlychothuesach.Fragment.OnMenuListener;
import com.example.appquanlychothuesach.Activity.ProfileActivity;
import com.example.appquanlychothuesach.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChaoMungFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChaoMungFragment extends Fragment {

    ViewPager viewPager;
    int currentPage = 0;
    ViewPagerAdapter viewPagerAdapter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnMenuListener listener;

    public ChaoMungFragment(OnMenuListener listener) {
        this.listener = listener;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChaoMungFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChaoMungFragment newInstance(String param1, String param2, OnMenuListener listener) {
        ChaoMungFragment fragment = new ChaoMungFragment(listener);
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
//    private String user;
//
//    public ChaoMungFragment(String user) {
//        this.user = user;
//    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View parent = inflater.inflate(R.layout.fragment_chao_mung, container, false);

        viewPager = parent.findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getActivity());
        viewPager.setAdapter(viewPagerAdapter);
        setAutoScrollViewScroll();
        viewPager.setClipToOutline(true);

//        TextView tvUser = parent.findViewById(R.id.tvUser);
//        TextView username = parent.findViewById(R.id.username);

//        if (getArguments() != null) {
//            String user = getArguments().getString(ARG_PARAM1);
//            String username1 = getArguments().getString(ARG_PARAM2);
//
//            tvUser.setText("Xin chào, " + user + "!");
//            username.setText(username1);
//        }
        parent.findViewById(R.id.btn_menu).setOnClickListener(view -> {
            if (listener != null) {
                listener.onMenuClicked();
            }
        });

        parent.findViewById(R.id.layout_profile).setOnClickListener(view -> {
//            startActivity(new Intent(getContext(), ProfileActivity.class));
            Intent intent = new Intent(getContext(), ProfileActivity.class);
//            intent.putExtra("user", user);
            startActivity(intent);
        });

        return parent;
    }
    //tự động lật
    private void setAutoScrollViewScroll() {
        Timer timer;
        final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
        final long PERIOD_MS = 2000; // time in milliseconds between successive task executions.
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == 2) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);
    }
}