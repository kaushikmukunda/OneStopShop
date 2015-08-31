package material.km.com.onestopshop.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import material.km.com.onestopshop.card.CardHolder;
import material.km.com.onestopshop.card.CardHolderFactory;
import material.km.com.onestopshop.model.Card;
import material.km.com.onestopshop.model.Types;

public class CardsAdapter extends RecyclerView.Adapter<CardHolder> {
    private ArrayList<Card> mCards;

    public CardsAdapter(ArrayList<Card> mCards) {
        this.mCards = mCards;
    }

    @Override
    public CardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return CardHolderFactory.getCardHolder(viewType, inflater);
    }

    @Override
    public void onBindViewHolder(CardHolder holder, int position) {
        holder.bindData(mCards.get(position));
    }

    @Override
    public int getItemCount() {
        return mCards.size();
    }

    @Override
    public int getItemViewType(int position) {
        String type = mCards.get(position).getType();
        return Types.typeLookup.get(type);
    }
}
