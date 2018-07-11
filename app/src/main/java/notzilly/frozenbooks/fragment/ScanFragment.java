package notzilly.frozenbooks.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

import notzilly.frozenbooks.R;
import notzilly.frozenbooks.activity.FreezerDetailActivity;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_scan, container, false);

        // Gets button that opens scanner and adds onClickListener
        Button openScanner = v.findViewById(R.id.open_scanner);
        openScanner.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) { scanBarcode(v); }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case 0:
                if (resultCode == CommonStatusCodes.SUCCESS && data != null) {
                    Barcode barcode = data.getParcelableExtra("barcode");
                    // Launch FreezerDetailActivity
                    Intent intent = new Intent(getActivity(), FreezerDetailActivity.class);
                    intent.putExtra(FreezerDetailActivity.EXTRA_FREEZER_KEY, barcode.displayValue);
                    startActivityForResult(intent, 1);

                } else if (resultCode == CommonStatusCodes.CANCELED && data != null) {
                    Toast.makeText(getContext(), "Permissão de acesso à câmera negada.",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Nenhum barcode foi lido.",
                            Toast.LENGTH_SHORT).show();
                }
                break;

            case 1:
                if (resultCode == CommonStatusCodes.ERROR){
                    Toast.makeText(getContext(), "Não existe Geladeiroteca cadastrada com este QR code lido.",
                            Toast.LENGTH_SHORT).show();
                }
                break;

            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }
}
