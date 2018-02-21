package com.st18apps.testapp;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by st18rai on 21.02.18.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<Card> mCard;
    private Boolean expanded = false;

    RecyclerAdapter(List<Card> mCard) {
        this.mCard = mCard;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        //Определение класса ViewHolder
        private LinearLayout mLinearLayout;

        ViewHolder(LinearLayout v) {
            super(v);
            mLinearLayout = v;
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Создание нового представления
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(linearLayout);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        //Заполнение заданного представления данными
        final LinearLayout linear = holder.mLinearLayout;

        final TextView textView = linear.findViewById(R.id.textView);
        final CardView cardView = linear.findViewById(R.id.card);

        textView.setText(mCard.get(position).getColorName());
        textView.setTextColor(mCard.get(position).getColor());
        cardView.setCardBackgroundColor(holder.itemView.getResources().getColor(R.color.gray));

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!expanded) {
                    cardExpand(cardView, textView, mCard.get(holder.getAdapterPosition()).getColor());
                    expanded = true;
                } else {
                    cardCollapse(cardView, textView, mCard.get(holder.getAdapterPosition()).getColor(), holder);
                    expanded = false;
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mCard.size();
    }

    private void cardCollapse(CardView cardView, TextView textView, int color, ViewHolder holder) {
        textView.setTextColor(color);
        cardView.setCardBackgroundColor(holder.itemView.getResources().getColor(R.color.gray));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10, 10, 10, 10);
        cardView.setLayoutParams(layoutParams);

    }


    private void cardExpand(CardView cardView, TextView textView, int color) {
        textView.setTextColor(Color.BLACK);
        cardView.setCardBackgroundColor(color);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) cardView.getLayoutParams();
        layoutParams.height = 1000;
        layoutParams.setMargins(10, 10, 10, 10);
        cardView.setLayoutParams(layoutParams);

    }

}
