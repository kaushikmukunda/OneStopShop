package material.km.com.onestopshop.card;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import material.km.com.onestopshop.R;
import material.km.com.onestopshop.model.Card;

public abstract class CardHolder extends RecyclerView.ViewHolder implements CardInf {
    TextView mTitle;
    ImageView mThumbnail;
    Card mData;
    Context mContext;


    public CardHolder(View itemView) {
        super(itemView);
        mTitle = (TextView) itemView.findViewById(R.id.title);
        mThumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
        mContext = itemView.getContext();
    }

    @Override
    public void bindData(Card card) {
        mData = card;
        renderTitle();
        renderThumbnail();
        renderExtra();
    }

    @Override
    public void renderTitle() {
        mTitle.setText(mData.getTitle());
    }

    @Override
    public void renderThumbnail() {
        Picasso.with(mContext)
            .load(mData.getThumbNail())
            .into(mThumbnail);
    }

    @Override
    public void renderExtra() {
    }
}
