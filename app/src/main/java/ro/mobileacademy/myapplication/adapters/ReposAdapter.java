package ro.mobileacademy.myapplication.adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ro.mobileacademy.myapplication.Main2Activity;
import ro.mobileacademy.myapplication.R;
import ro.mobileacademy.myapplication.model.Repo;

/**
 * Created by andrei on 29/09/2018.
 * 2018
 */
public class ReposAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Repo> dataSet;

    public ReposAdapter(List<Repo> dataSet) {
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
        ((ItemVH) holder).repoName.setText(dataSet.get(position).getName());
        ((ItemVH) holder).repoDescription.setText(dataSet.get(position).getDescription());
    }

    public void updateDataSet(List<Repo> newDataSet) {
        dataSet = newDataSet;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    private class ItemVH extends RecyclerView.ViewHolder {

        private TextView repoName;
        private TextView repoDescription;

        public ItemVH(View itemView) {
            super(itemView);
            repoName = itemView.findViewById(R.id.repo_name);
            repoDescription = itemView.findViewById(R.id.repo_description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), Main2Activity.class);
                    intent.putExtra("repo_name", dataSet.get(getAdapterPosition()).getName());
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

}
