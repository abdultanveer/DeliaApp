package com.abdul.deliaapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LanguagesAdapter  extends RecyclerView.Adapter<LanguagesAdapter.LanguagesViewHolder> {
    String[] langs;
    public LanguagesAdapter(String[] languages) {
        langs = languages;
    }

    /**
     * This guy will buy planks from market if needed
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public LanguagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view =   LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_row,parent,false);
        return new LanguagesViewHolder(view);
    }

    //writer -- he'll write the menu item on the plank which he got from the viewholder
    @Override
    public void onBindViewHolder(@NonNull LanguagesViewHolder holder, int position) {
    holder.languageTextView.setText(langs[position]);
    }

    //he'll keep the count of no of items in the data set so that if required oncreateviewholder can buy new planks
    @Override
    public int getItemCount() {
        return langs.length;
    }

    // a reserve to hold the extra planks to be reused
    public class LanguagesViewHolder extends RecyclerView.ViewHolder {
        TextView languageTextView;
        public LanguagesViewHolder(@NonNull View itemView) {
            super(itemView);
            languageTextView = itemView.findViewById(R.id.tvLanguage);
        }
    }
}
