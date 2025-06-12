package com.example.lab51;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ModuleAdapter  extends RecyclerView.Adapter<ModuleAdapter.ModuleViewHolder>{
    private List<Module> modules;

    public ModuleAdapter(List<Module> modules) {
        this.modules = modules;
    }

    @NonNull
    @Override
    public ModuleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_module, parent, false);
        return new ModuleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ModuleViewHolder holder, int position) {
        Module m = modules.get(position);
        holder.ivIcon.setImageResource(m.getIconRes());
        holder.tvTitle.setText(m.getTitle());
        holder.tvDesc.setText(m.getDescription());
        holder.tvCategory.setText(m.getCategory());
    }

    @Override
    public int getItemCount() {
        return modules.size();
    }

    static class ModuleViewHolder extends RecyclerView.ViewHolder {
        ImageView ivIcon;
        TextView tvTitle, tvDesc, tvCategory;
        ModuleViewHolder(@NonNull View itemView) {
            super(itemView);
            ivIcon      = itemView.findViewById(R.id.ivIcon);
            tvTitle     = itemView.findViewById(R.id.tvTitle);
            tvDesc      = itemView.findViewById(R.id.tvDesc);
            tvCategory  = itemView.findViewById(R.id.tvCategory);
        }
    }
}
