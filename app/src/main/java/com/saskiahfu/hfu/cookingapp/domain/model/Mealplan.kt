package com.saskiahfu.hfu.cookingapp.domain.model


@JvmInline
value class MealId(val value: String)

class Mealplan private constructor(
    val day: MealId,
    val bfName: String,
    val luName: String,
    val diName: String,
) {
    companion object {
        fun update(
            day: MealId,
            bfName: String,
            luName: String,
            diName: String,
        ): Mealplan? {
            return Mealplan(day, bfName, luName, diName)
        }

        fun create(
            day: MealId,
            bfName: String,
            luName: String,
            diName: String,
        ): Mealplan? {
            return Mealplan(day, bfName, luName, diName)
        }
    }
}