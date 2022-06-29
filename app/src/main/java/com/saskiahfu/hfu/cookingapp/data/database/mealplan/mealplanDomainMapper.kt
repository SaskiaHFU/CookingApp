package com.saskiahfu.hfu.cookingapp.data.database.mealplan

import com.saskiahfu.hfu.cookingapp.domain.model.MealId
import com.saskiahfu.hfu.cookingapp.domain.model.Mealplan


fun mealToDb(meal: Mealplan): MealplanDb = MealplanDb(
//    day = meal.day.value,
    day = meal.day,
    bfName = meal.bfName,
    luName = meal.luName,
    diName = meal.diName,
)

fun mealFromDb(meal: MealplanDb): Mealplan? {
    return Mealplan.update(
//        day = MealId(meal.day),
        day = meal.day,
        bfName = meal.bfName,
        luName = meal.luName,
        diName = meal.diName,
    )
}