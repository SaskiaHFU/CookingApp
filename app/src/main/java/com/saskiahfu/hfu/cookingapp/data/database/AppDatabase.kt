package com.saskiahfu.hfu.cookingapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.saskiahfu.hfu.cookingapp.data.database.cart.CartDao
import com.saskiahfu.hfu.cookingapp.data.database.cart.CartDb
import com.saskiahfu.hfu.cookingapp.data.database.recipe.RecipeDao
import com.saskiahfu.hfu.cookingapp.data.database.recipe.RecipeDb
import com.saskiahfu.hfu.cookingapp.data.database.mealplan.DailyMealsDao
import com.saskiahfu.hfu.cookingapp.data.database.mealplan.MealplanDb
import com.saskiahfu.hfu.cookingapp.data.database.recipe.RecipeCategoryDao
import com.saskiahfu.hfu.cookingapp.data.database.recipe.RecipeCategoryDb

@Database(
    version = 1,
    entities = [
        CartDb::class,
        RecipeDb::class,
        RecipeCategoryDb::class,
        MealplanDb::class,
    ],
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao
    abstract fun recipeDao(): RecipeDao
    abstract fun recipeCatDao(): RecipeCategoryDao
    abstract fun dailyMealsDao(): DailyMealsDao
}
