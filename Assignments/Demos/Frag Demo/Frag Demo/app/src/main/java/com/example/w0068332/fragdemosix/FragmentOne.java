package com.example.w0068332.fragdemosix;

import android.support.v4.app.*;
import android.os.*;
import android.view.*;
import android.view.ViewGroup;

public class FragmentOne extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
        ViewGroup container, Bundle savedInstanceState) {

        //Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_one, container, false);
    }//end onCreateView

} //end class
