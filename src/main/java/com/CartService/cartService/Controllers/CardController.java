package com.CartService.cartService.Controllers;

import com.CartService.cartService.Services.ServiceProvider;
import com.CartService.cartService.domain.cartItem;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shoppingCard")
public class CardController {

    @PostMapping("/new")
    public ResponseEntity<?> newShoppingcard() throws JSONException {
        int shoppingcardid = ServiceProvider.getCardService().newShoppingcard();
        if(shoppingcardid != 0){
            JSONObject obj = new JSONObject();
            obj.put("Shoppincardid", shoppingcardid);
//f
            return ResponseEntity.ok(obj);

        }else{
            return ResponseEntity.status(500).body("Whoops we're having some trouble try again later!");
        }
        //        https://www.tutorialspoint.com/json/json_java_example.htm
    }
    @PostMapping("/addProduct")
    public ResponseEntity<?> addShoppingcartItem(@RequestBody cartItem cartItem) throws JSONException {
        int result = ServiceProvider.getCardService().addProduct(cartItem.getCardid(),cartItem.getProductid());
        if(result != 0){
            cartItem.setDatabaseid(result);
            return ResponseEntity.ok(cartItem);
        }else{
            cartItem.setDatabaseid(0);
            return ResponseEntity.status(500).body(cartItem);
        }
        //        https://www.tutorialspoint.com/json/json_java_example.htm
    }
    @DeleteMapping("/deleteline/{itemline}")
    public ResponseEntity deleteLine(@PathVariable int itemline){
        boolean result = ServiceProvider.getCardService().deleteLine(itemline);
        return ResponseEntity.ok(result);
    }

}
