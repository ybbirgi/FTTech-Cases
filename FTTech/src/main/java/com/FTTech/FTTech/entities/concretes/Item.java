package com.FTTech.FTTech.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "item_id")
    private int itemId;

    @Column(name= "price")
    private int itemPrice;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @OneToMany(mappedBy = "item" ,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<ItemComment> itemCommentList;

}
