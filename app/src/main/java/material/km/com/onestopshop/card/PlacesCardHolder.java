package material.km.com.onestopshop.card;

import android.view.View;
import android.widget.TextView;

import material.km.com.onestopshop.R;

public class PlacesCardHolder extends CardHolder {
    TextView mCategory;

    public PlacesCardHolder(View itemView) {
        super(itemView);
        mCategory = (TextView) itemView.findViewById(R.id.category);
    }

    @Override
    public void renderExtra() {
        super.renderExtra();
        mCategory.setText(mData.getExtra());
    }
}
