package notzilly.frozenbooks.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

import notzilly.frozenbooks.R;
import notzilly.frozenbooks.model.Book;

public class BookViewHolder extends RecyclerView.ViewHolder {

    private TextView titleView;
    private ToggleButton bookAction;

    public BookViewHolder(View itemView) {
        super(itemView);

        titleView = itemView.findViewById(R.id.book_title);
        bookAction = itemView.findViewById(R.id.book_action);
    }

    public void bindToBook(Book book, Boolean buttonChecked, View.OnClickListener actionClickListener) {
        titleView.setText(book.getTitle());
        bookAction.setChecked(buttonChecked);
        bookAction.setOnClickListener(actionClickListener);
    }
}
