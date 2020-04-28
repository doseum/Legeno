package com.example.billage.ui.home.subView;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.billage.R;
import com.example.billage.adapter.UsageAdapter;
import com.example.billage.UsageList;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UsageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UsageFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UsageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment usageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UsageFragment newInstance(String param1, String param2) {
        UsageFragment fragment = new UsageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_usage, container, false);


        final ArrayList<UsageList> items = new ArrayList<>() ;

        items.add(new UsageList("a","1133s","fdf"));
        items.add(new UsageList("a","1133s","fdf"));
        items.add(new UsageList("b","1133s","fdf"));items.add(new UsageList("dfs","1133s","fdf"));items.add(new UsageList("dfs","1133s","fdf"));items.add(new UsageList("dfs","1133s","fdf"));
        items.add(new UsageList("b","1133s","fdf"));items.add(new UsageList("dfs","1133s","fdf"));
        items.add(new UsageList("x","1133s","fdf"));
        items.add(new UsageList("x","1133s","fdf"));
        items.add(new UsageList("a","1133s","fdf"));




        // ArrayAdapter 생성. 아이템 View를 선택(single choice)가능하도록 만듦.
      //  final ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, items) ;

        // listview 생성 및 adapter 지정.
        final ListView listview = (ListView) root.findViewById(R.id.usage_list) ;
       // listview.setAdapter(adapter) ;


        final UsageAdapter usageAdapter = new UsageAdapter(getActivity(),items,listview);
        listview.setAdapter(usageAdapter);


        return root;
    }
}
