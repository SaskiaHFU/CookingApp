package com.saskiahfu.hfu.cookingapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.saskiahfu.hfu.cookingapp.data.database.cart.ShoppingCartDao
import com.saskiahfu.hfu.cookingapp.data.database.cart.ShoppingCartProductDb
import com.saskiahfu.hfu.cookingapp.data.database.recipe.RecipeDao
import com.saskiahfu.hfu.cookingapp.data.database.recipe.RecipeDb
import com.saskiahfu.hfu.cookingapp.data.database.links.LinkCategoryDb
import com.saskiahfu.hfu.cookingapp.data.database.links.LinksCategoryDao
import com.saskiahfu.hfu.cookingapp.data.database.links.LinksDao
import com.saskiahfu.hfu.cookingapp.data.database.links.LinksDb
import com.saskiahfu.hfu.cookingapp.data.database.mealplan.DailyMealsDao
import com.saskiahfu.hfu.cookingapp.data.database.mealplan.MealplanDb

//import com.saskiahfu.hfu.cookingapp.data.database.recipe.RecipeCategoryDao
//import com.saskiahfu.hfu.cookingapp.data.database.recipe.RecipeCategoryDb

@Database(
    version = 1,
    entities = [
        ProductDb::class,
        ShoppingCartProductDb::class,
        RecipeDb::class,
        LinksDb::class,
        LinkCategoryDb::class,
        MealplanDb::class,
//        RecipeCategoryDb::class,
    ],
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun shoppingCartDao(): ShoppingCartDao
    abstract fun recipeDao(): RecipeDao

    //    abstract fun recipeCatDao(): RecipeCategoryDao
    abstract fun linksDao(): LinksDao
    abstract fun linksCategoryDao(): LinksCategoryDao
    abstract fun dailyMealsDao(): DailyMealsDao

}
