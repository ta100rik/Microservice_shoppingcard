package com.CartService.cartService.Services;

import com.CartService.cartService.dataLayer.DataAccessObjectsMySQL.DaoImpl.CardDaoImpl;

public class CardService {
    private final CardDaoImpl CartImpl = new CardDaoImpl();

    public int newShoppingcard(){
        return CartImpl.newShoppingcard();
    }
    public int addProduct(int shoppingcardid, int productid){
        return CartImpl.addProduct(shoppingcardid,productid);
    }
    public boolean deleteLine(int lineId){
        return CartImpl.deleteLine(lineId);
    }

}
