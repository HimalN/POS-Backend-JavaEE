package lk.ijse.posbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Item implements Serializable {
    private String itemCode;
    private String itemName;
    private String category;
    private  String weight;
    private float price;
    private int qty;
}
