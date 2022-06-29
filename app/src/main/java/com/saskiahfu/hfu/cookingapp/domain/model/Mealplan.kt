package com.saskiahfu.hfu.cookingapp.domain.model


@JvmInline
value class MealId(val value: String)

data class Mealplan private constructor(
//    val day: MealId,
    val day: String,
    val bfName: String,
    val luName: String,
    val diName: String,
) {
    companion object {
        fun update(
//            day: MealId,
            day: String,
            bfName: String,
            luName: String,
            diName: String,
        ): Mealplan? {
            return Mealplan(day, bfName, luName, diName)
        }

        fun create(
//            day: MealId,
            day: String,
            bfName: String,
            luName: String,
            diName: String,
        ): Mealplan? {
            return Mealplan(day, bfName, luName, diName)
        }
    }
}
