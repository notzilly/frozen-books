package notzilly.frozenbooks.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import notzilly.frozenbooks.R;
import notzilly.frozenbooks.model.Freezer;
import notzilly.frozenbooks.viewholder.FreezerViewHolder;

public class ListFreezersFragment extends Fragment {

    private DatabaseReference db;
    private FirebaseRecyclerAdapter<Freezer, FreezerViewHolder> fbAdapter;
    private RecyclerView recycler;
    private LinearLayoutManager manager;

    // Required empty public constructor
    public ListFreezersFragment() {}

    // Static method to instantiate a new fragment
    public static ListFreezersFragment newInstance() {
        ListFreezersFragment fragment = new ListFreezersFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_list_freezers, container, false);

        // Gets database reference
        db = FirebaseDatabase.getInstance().getReference();

        // Finds recycler and sets fixed size
        recycler = v.findViewById(R.id.freezers_list);
        recycler.setHasFixedSize(true);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }
}
