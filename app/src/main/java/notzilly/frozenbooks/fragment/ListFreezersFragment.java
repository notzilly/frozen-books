package notzilly.frozenbooks.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import notzilly.frozenbooks.R;
import notzilly.frozenbooks.model.Freezer;
import notzilly.frozenbooks.viewholder.FreezerViewHolder;

public class ListFreezersFragment extends Fragment {

    private DatabaseReference db;
    private FirebaseRecyclerAdapter<Freezer, FreezerViewHolder> adapter;
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

        // Setting up manager
        manager = new LinearLayoutManager(getActivity());
        manager.setReverseLayout(true);
        manager.setStackFromEnd(true);
        recycler.setLayoutManager(manager);

        DividerItemDecoration itemDecor = new DividerItemDecoration(recycler.getContext(), manager.getOrientation());
        recycler.addItemDecoration(itemDecor);

        // Set up FirebaseRecyclerAdapter with the Query
        Query freezersQuery = getQuery(db);

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<Freezer>()
                .setQuery(freezersQuery, Freezer.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<Freezer, FreezerViewHolder>(options) {

            @NonNull
            @Override
            public FreezerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
                return new FreezerViewHolder(inflater.inflate(R.layout.item_freezer, viewGroup, false));
            }

            @Override
            protected void onBindViewHolder(@NonNull FreezerViewHolder holder, int position, @NonNull Freezer model) {
                final DatabaseReference freezerRef = getRef(position);

                // Set click listener for the whole freezer view
//                final String freezerKey = freezerRef.getKey();
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Launch PostDetailActivity
                        Toast.makeText(getActivity(), "testing onclick", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(getActivity(), FreezerDetailActivity.class);
//                        intent.putExtra(FreezerDetailActivity.EXTRA_POST_KEY, postKey);
//                        startActivity(intent);
                    }
                });
                holder.bindToFreezer(model);
            }
        };
        recycler.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (adapter != null) {
            adapter.startListening();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (adapter != null) {
            adapter.stopListening();
        }
    }

    public Query getQuery(DatabaseReference db){
        return db.child("freezers");
    }

}
