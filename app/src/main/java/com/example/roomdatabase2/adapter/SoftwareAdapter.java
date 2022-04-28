package com.example.roomdatabase2.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdatabase2.R;
import com.example.roomdatabase2.database.entity.Software;

import java.util.List;

public class SoftwareAdapter extends RecyclerView.Adapter<SoftwareAdapter.ViewAdapter> {
    private List<Software> list;
    private Context context;
    private Dialog dialog;

    public interface Dialog {
        void onClick(int position);
    }

    public void setDialog(Dialog dialog){
        this.dialog = dialog;
    }

    public SoftwareAdapter(Context context, List<Software> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SoftwareAdapter.ViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.software_row, parent, false);
        return new ViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SoftwareAdapter.ViewAdapter holder, int position) {
        holder.name.setText(list.get(position).sName);
        holder.year.setText(list.get(position).year);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewAdapter extends RecyclerView.ViewHolder{
        TextView name, year;
        public ViewAdapter(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.full_name);
            year = itemView.findViewById(R.id.year);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(dialog!=null){
                        dialog.onClick(getLayoutPosition());
                    }
                }
            });
        }
    }
}
