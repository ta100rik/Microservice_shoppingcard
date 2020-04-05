package com.CartService.cartService.dataLayer.DataAccessObjectsMySQL.DaoImpl;

import com.CartService.cartService.dataLayer.DataAccessObjectsMySQL.BaseDaoMySQL;
import com.CartService.cartService.dataLayer.InterfacesDao.shoppingCartDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class CardDaoImpl extends BaseDaoMySQL implements shoppingCartDao {
    @Override
    public int newShoppingcard() {
        try{
//            https://stackoverflow.com/questions/7162989/sqlexception-generated-keys-not-requested-mysql
            Connection connection  = super.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT shoppingCart (cartname) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,"card");
            int test= super.executeQueryReturningId(preparedStatement,connection);
            return test;
        }
        catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
    public int addProduct(int shoppingCartid, int productid){
        try{
            Connection connection  = super.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO cartItems (productId,cartId) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1,productid);
            preparedStatement.setInt(2,shoppingCartid);
            return super.executeQueryReturningId(preparedStatement,connection);
        }
        catch (Exception e){
            e.printStackTrace();
            return 0;
        }

    }
    public Boolean deleteLine(int lineid){
        try{
            Connection connection = super.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM cartItems where idcartItems = ?;");
            preparedStatement.setInt(1,lineid);
            return super.updateQuery(preparedStatement,connection);
        }catch (Exception e) {
            return false;
        }

    }

}
