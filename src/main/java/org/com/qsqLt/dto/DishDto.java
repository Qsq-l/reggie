package org.com.qsqLt.dto;

import lombok.Data;
import org.com.qsqLt.entity.Dish;
import org.com.qsqLt.entity.DishFlavor;

import java.util.ArrayList;
import java.util.List;

@Data
public class DishDto extends Dish {

    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;
}
