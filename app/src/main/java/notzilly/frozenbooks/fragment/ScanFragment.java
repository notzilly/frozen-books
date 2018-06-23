package notzilly.frozenbooks.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import notzilly.frozenbooks.R;
import notzilly.frozenbooks.activity.ScanActivity;

public class ScanFragment extends Fragment implements View.OnClickListener {

    // Required empty public constructor
    public ScanFragment() {}

    // Click event for opening scanner

    public void scanBarcode(View v){
        Intent intent = new Intent(getActivity(), ScanActivity.class);
        startActivityForResult(intent, 0);
    }

    // Factory of ScanFragment
    public static ScanFragment newInstance() {
        ScanFragment fragment = new ScanFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_scan, container, false);

        Button openScanner = (Button) v.findViewById(R.id.open_scanner);
        openScanner.setOnClickListener(this);

        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onClick(View v) {
        scanBarcode(v);
    }

    @Override
    public void onAttach(Context context) { super.onAttach(context); }

    @Override
    public void onDetach() { super.onDetach(); }

}
