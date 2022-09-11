package org.com.qsqLt.dto;

import lombok.Data;
import org.com.qsqLt.entity.Setmeal;
import org.com.qsqLt.entity.SetmealDish;

import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
