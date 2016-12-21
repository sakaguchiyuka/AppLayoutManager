package net.ysakaguchi.appmanager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ysakaguchi on 2016/12/22.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<String> data;
    private OnRecyclerListener listener;

    public RecyclerAdapter(Context context, ArrayList<String> data, OnRecyclerListener listener) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.listener = listener;
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(inflater.inflate(R.layout.grid_item_app, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
        if (data != null && data.size() > position && data.get(position) != null) {
            viewHolder.textView.setText(data.get(position));
        }

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRecyclerClicked(v, viewHolder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        } else {
            return 0;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.app_name);
        }
    }

    public interface OnRecyclerListener {
        void onRecyclerClicked(View v, int position);
    }
}
