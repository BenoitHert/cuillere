package com.salted_broccoli.cuillere.Service;

import com.salted_broccoli.cuillere.Model.Menu.Ingredient;
import com.salted_broccoli.cuillere.Model.Shoplist.ShopItem;
import com.salted_broccoli.cuillere.Model.User;
import com.salted_broccoli.cuillere.Repository.ShopRepository;
import com.salted_broccoli.cuillere.Repository.UserRepository;
import com.salted_broccoli.cuillere.Service.form.ItemForm;
import com.salted_broccoli.cuillere.Service.form.ShopForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service("Shop Service")
public class ShopService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ShopRepository shopRepository;

    //    TODO Couverture de tests

    public User findUser() {
        //Spring Security-side identifier; tied to the user's session => user's email
        String connectedUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        //Fetch according to the email
        return userRepository.findUserByEmail(connectedUserEmail);
    }

    public ShopItem createShopItem(ShopForm form){
        ShopItem ShopItem = new ShopItem();
        ShopItem.setItem(form.getItem());
        ShopItem.setAmount(form.getAmount());
        return shopRepository.save(ShopItem);
    }

    public User addItemToList(ShopForm form){
        User user = findUser();
        ShopItem item = createShopItem(form);
        user.getShoplist().add(item);
        return userRepository.save(user);
    }

    public User removeItemFromList(Long id){
        User user = findUser();
        ShopItem item = shopRepository.findShopItemById(id);
        user.getShoplist().remove(item);
        return userRepository.save(user);
    }

    public User addIngredientToList(Ingredient ingredient, Long amount){
        User user = findUser();
        ShopForm form = new ShopForm();
        form.setItem(ingredient.getAliment());
        form.setAmount(amount);
        ShopItem shopItem = createShopItem(form);
        user.getShoplist().add(shopItem);
        return userRepository.save(user);




    }

}
