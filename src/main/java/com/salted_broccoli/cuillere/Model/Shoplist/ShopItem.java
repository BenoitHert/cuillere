package com.salted_broccoli.cuillere.Model.Shoplist;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class ShopItem {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;
    private String item;
    private Long amount;
}
