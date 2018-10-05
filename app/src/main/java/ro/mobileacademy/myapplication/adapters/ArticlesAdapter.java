package ro.mobileacademy.myapplication.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ro.mobileacademy.myapplication.R;

/**
 * Created by andrei on 29/09/2018.
 * 2018
 */
public class ArticlesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> dataSet;

    public ArticlesAdapter(List<String> dataSet) {
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article, parent, false);
        return new ItemVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ItemVH) holder).indexTV.setText(dataSet.get(position));
    }

    public void addItem(int position, String value) {
        dataSet.add(position, value);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        dataSet.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    private class ItemVH extends RecyclerView.ViewHolder {

        private TextView indexTV;

        public ItemVH(View itemView) {
            super(itemView);
            indexTV = itemView.findViewById(R.id.article_index);
        }
    }

}
