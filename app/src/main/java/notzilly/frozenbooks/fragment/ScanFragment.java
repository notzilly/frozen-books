package notzilly.frozenbooks.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

import notzilly.frozenbooks.R;
import notzilly.frozenbooks.activity.ScanActivity;

public class ScanFragment extends Fragment implements View.OnClickListener {

    TextView barcodeResult;

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

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_scan, container, false);

        // Gets TextView for scanner result
        barcodeResult = (TextView) v.findViewById(R.id.scan_result);

        // Gets button that opens scanner and adds onClickListener
        Button openScanner = (Button) v.findViewById(R.id.open_scanner);
        openScanner.setOnClickListener(this);

        return v;
    }

    @Override
    public void onAttach(Context context) { super.onAttach(context); }

    @Override
    public void onDetach() { super.onDetach(); }

    @Override
    public void onClick(View v) { scanBarcode(v); }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 0) {

            if(resultCode == CommonStatusCodes.SUCCESS && data != null) {
                Barcode barcode = data.getParcelableExtra("barcode");
                barcodeResult.setText(barcode.displayValue);
            } else {
                barcodeResult.setText("no barcode found");
            }

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
