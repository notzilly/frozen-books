package notzilly.frozenbooks.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import notzilly.frozenbooks.R;
import notzilly.frozenbooks.model.Book;

public class BookViewHolder extends RecyclerView.ViewHolder {

    private TextView titleView;

    public BookViewHolder(View itemView) {
        super(itemView);

        titleView = itemView.findViewById(R.id.book_title);
    }

    public void bindToBook(Book book) {
        titleView.setText(book.getTitle());
    }
}
