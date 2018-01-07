package com.example.leo.listadapter.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.leo.listadapter.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Leo on 05/01/2018.
 */

public class SecondFragment extends Fragment {
    // Store instance variables
    ArrayList<String> list = new ArrayList<>();

    private String title;
    private int page;

    // newInstance constructor for creating fragment with arguments
    public static SecondFragment newInstance() {
        SecondFragment fragmentSecond = new SecondFragment();
//        Bundle args = new Bundle();
//        args.putInt("someInt", page);
//        args.putString("someTitle", title);
//        fragmentFirst.setArguments(args);
        return fragmentSecond;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        page = getArguments().getInt("someInt", 0);
//        title = getArguments().getString("someTitle");
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate
                (R.layout.fragment_second, container, false);

        final ListView listview = (ListView) view.findViewById(R.id.listview);

        String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
                "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
                "Android", "iPhone", "WindowsMobile" };


        list = new ArrayList<String>();

        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }

        //proigoumeno example exei this (giati einai se Activity kai to Context pou einai mesa sto View , kaleite me 'this' h'
        // getContext()  )

        //View getContext what is context ??
        final StableArrayAdapter adapter = new StableArrayAdapter(view.getContext(),
                android.R.layout.simple_list_item_1, list);


        listview.setAdapter(adapter);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            //OnItemClick

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {

                //get Item from whole List
                final String item = (String) parent.getItemAtPosition(position);

                //Result is an Item , to that item we do :
                view.animate().setDuration(2000).alpha(0)
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                // what we want to do.
                                list.remove(item);
                                adapter.notifyDataSetChanged();
                                view.setAlpha(1);

                            }
                        });
            }

        });

        return view;
    }

    //Unique Object extention of ArrayAdapter
    private class StableArrayAdapter extends ArrayAdapter<String> {

        //HashMap = Key value pair , with String : 'android' , Interger : index=1
        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        //Default Constructor of ArrayAdapter<String>
        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);

            //get List objects , and insert it inside HashMap
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }

        }

        //find item based on Position and the return it from HashMap .
        @Override
        public long getItemId(int position) {
            String item = getItem(position);

            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }


}
