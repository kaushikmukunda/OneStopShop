package material.km.com.onestopshop.card;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import material.km.com.onestopshop.R;

public class MovieCardHolder extends CardHolder {
    ImageView mActorImg;

    public MovieCardHolder(View itemView) {
        super(itemView);
        mActorImg = (ImageView) itemView.findViewById(R.id.actor);
    }

    @Override
    public void renderExtra() {
        super.renderExtra();
        Picasso.with(mContext).load(mData.getExtra()).into(mActorImg);
    }
}
