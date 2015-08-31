package material.km.com.onestopshop.card;

import material.km.com.onestopshop.model.Card;

public interface CardInf {
    public void bindData(Card card);
    public void renderTitle();
    public void renderThumbnail();
    public void renderExtra();
}
