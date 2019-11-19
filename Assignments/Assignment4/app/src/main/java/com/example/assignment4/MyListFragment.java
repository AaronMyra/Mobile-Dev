package com.example.assignment4;

import android.os.*;
import android.view.*;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.fragment.app.ListFragment;


public class MyListFragment extends ListFragment implements AdapterView.OnItemClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle saveInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_item_list,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.character_name,android.);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(),"Item: "+position,
                Toast.LENGTH_SHORT).show();
    }
}//end class