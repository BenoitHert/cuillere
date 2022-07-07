package com.salted_broccoli.cuillere.Repository;

import com.salted_broccoli.cuillere.Model.Shoplist.ShopItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository <ShopItem, Long> {
    ShopItem findShopItemById(Long id);
}
