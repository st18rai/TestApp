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
    private final Callback mListener;
    private int expandedHeight;
    private int margin;

    RecyclerAdapter(List<Card> mCard, Callback mListener) {
        this.mCard = mCard;
        this.mListener = mListener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final Callback mListener;

        //Определение класса ViewHolder
        private LinearLayout mLinearLayout;

        ViewHolder(LinearLayout v, Callback mListener) {
            super(v);
            this.mListener = mListener;
            mLinearLayout = v;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mListener.onItemClick(getAdapterPosition());
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Создание нового представления
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(linearLayout, mListener);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        //Заполнение заданного представления данными
        final LinearLayout linear = holder.mLinearLayout;

        final TextView textView = linear.findViewById(R.id.textView);
        final CardView cardView = linear.findViewById(R.id.card);

        final Card card = mCard.get(position);

        expandedHeight = holder.itemView.getResources().getDimensionPixelSize(R.dimen.height);
        margin = holder.itemView.getResources().getDimensionPixelSize(R.dimen.margin);

        textView.setText(card.getColorName());

        if (card.isPressed()) {
            cardExpand(cardView, textView, mCard.get(holder.getAdapterPosition()).getColor());
        } else {
            cardCollapse(cardView, textView, mCard.get(holder.getAdapterPosition()).getColor(), holder);
        }

    }

    @Override
    public int getItemCount() {
        return mCard.size();
    }

    private void cardCollapse(CardView cardView, TextView textView, int color, ViewHolder holder) {
        textView.setTextColor(color);
        cardView.setCardBackgroundColor(holder.itemView.getResources().getColor(R.color.gray));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(margin, margin, margin, margin);
        cardView.setLayoutParams(layoutParams);

    }

    private void cardExpand(CardView cardView, TextView textView, int color) {
        textView.setTextColor(Color.BLACK);
        cardView.setCardBackgroundColor(color);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) cardView.getLayoutParams();
        layoutParams.height = expandedHeight;
        layoutParams.setMargins(margin, margin, margin, margin);
        cardView.setLayoutParams(layoutParams);

    }

    public interface Callback {
        void onItemClick(int position);
    }
}
