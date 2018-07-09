package notzilly.frozenbooks.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import notzilly.frozenbooks.R;
import notzilly.frozenbooks.model.Freezer;

public class FreezerViewHolder extends RecyclerView.ViewHolder {

    TextView freezerAddress;
    TextView freezerBookAmount;

    public FreezerViewHolder(View itemView) {
        super(itemView);

        freezerAddress = itemView.findViewById(R.id.freezer_address);
        freezerBookAmount = itemView.findViewById(R.id.freezer_book_amount);
    }

    public void bindToFreezer(Freezer freezer) {
        freezerAddress.setText(freezer.getAddress());
        freezerBookAmount.setText(freezer.getBookQtt());
    }
}
