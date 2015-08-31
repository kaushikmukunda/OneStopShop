package material.km.com.onestopshop.card;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import material.km.com.onestopshop.R;

public class MusicCardHolder extends CardHolder {
    Button mPlay;

    public MusicCardHolder(View itemView) {
        super(itemView);
        mPlay = (Button) itemView.findViewById(R.id.play);
    }

    @Override
    public void renderExtra() {
        super.renderExtra();
        mPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mData.getExtra()));
                mContext.startActivity(intent);
            }
        });
    }
}
