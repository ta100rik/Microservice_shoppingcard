package com.CartService.cartService.dataLayer.InterfacesDao;

public interface shoppingCartDao {
    int newShoppingcard();
    int addProduct(int shoppingCartid, int productid);
    Boolean deleteLine(int lineid);
}
