package notzilly.frozenbooks;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    private TextView homeTextView;

    // TODO: Rename and change types of parameters
    private String mParam1;

    // Required empty public constructor
    public HomeFragment() {}

    // Factory of HomeFragment
    public static HomeFragment newInstance(String param1) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        homeTextView = (TextView) rootView.findViewById(R.id.home_textview);

        homeTextView.setText(mParam1);

        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onAttach(Context context) { super.onAttach(context); }

    @Override
    public void onDetach() { super.onDetach(); }

}
