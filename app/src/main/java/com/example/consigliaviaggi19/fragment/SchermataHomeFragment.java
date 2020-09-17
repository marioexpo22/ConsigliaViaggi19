package com.example.consigliaviaggi19.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.example.consigliaviaggi19.MainActivity;
import com.example.consigliaviaggi19.R;
import com.example.consigliaviaggi19.controller.HomeController;

public class SchermataHomeFragment extends Fragment {
    public MainActivity mainActivity;
    RecyclerView recyclerView1;
    RecyclerView recyclerView2;
    RecyclerView recyclerView3;

    public static SchermataHomeFragment newInstance(MainActivity mainActivity){ return new SchermataHomeFragment(mainActivity); }

    private SchermataHomeFragment (MainActivity mainActivity){ this.mainActivity = mainActivity; }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView1 = getActivity().findViewById(R.id.recyclerView1);
        recyclerView2 = getActivity().findViewById(R.id.recyclerView2);
        recyclerView3 = getActivity().findViewById(R.id.recyclerView3);

        createRecyclerView(recyclerView1, "ristorante");
        createRecyclerView(recyclerView2, "ristorante");
        createRecyclerView(recyclerView3, "ristorante");
    }

    public void createRecyclerView(RecyclerView recyclerView, String type){
        HomeController homeController = new HomeController(mainActivity, recyclerView);
        homeController.execute(type);
    }

}
