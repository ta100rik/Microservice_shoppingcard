package com.CartService.cartService.domain;

public class cartItem {
    private int cardid;
        private int productid;
        private int databaseid;
    public void setCardid(int cardid) {
        this.cardid = cardid;
    }

    public void setDatabaseid(int databaseid) {
        this.databaseid = databaseid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public int getDatabaseid() {
        return databaseid;
    }

    public int getCardid() {
        return cardid;
    }

    public int getProductid() {
        return productid;
    }
}
