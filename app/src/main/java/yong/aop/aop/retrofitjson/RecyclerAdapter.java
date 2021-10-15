package yong.aop.aop.retrofitjson;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder> {

    ArrayList<Post> dataList = new ArrayList<Post> ();

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from (parent.getContext ()).inflate (R.layout.item, parent, false);
        return new ItemViewHolder (view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int position) {
        itemViewHolder.onBind (dataList.get (position));
    }

    @Override
    public int getItemCount() {
        return dataList.size ();
    }

    public void addItem(Post post) {
        dataList.add (post);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView textView1;
        private TextView textView2;

        ItemViewHolder(@NonNull View itemView) {
            super (itemView);
            textView1 = itemView.findViewById (R.id.textView1);
            textView2 = itemView.findViewById (R.id.textView2);
        }

        void onBind(Post post) {
            textView1.setText (post.getTitle ());
            textView2.setText (post.getBody ());
        }
    }
}

