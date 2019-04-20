package com.poniatowska.n.listapracownikow;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder>{

    private List<Person> dataList;
    private Context context;

    ListAdapter(List<Person> dataList, Context context)
    {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_row, parent, false);

        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, final int position) {

        holder.txtId.setText("ID: "+dataList.get(position).getId());
        holder.txtName.setText("Name: "+dataList.get(position).getEmployeeName());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Person p = dataList.get(position);
                Intent intent = new Intent(context, PersonActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("p", p);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder{

        TextView txtId, txtName;
        LinearLayout linearLayout;

        public ListViewHolder(View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.textView);
            txtName = itemView.findViewById(R.id.textView2);
            linearLayout = itemView.findViewById(R.id.layout);
        }
    }
}
