package com.saskiahfu.hfu.cookingapp.domain.model


@JvmInline
value class RecipeId(val value: String)

sealed class RecipeImg {
    //TODO anpassen
    object Unknown : RecipeImg()
    class Local(val name: String) : RecipeImg()

}

class Recipe private constructor(
    val id: RecipeId,
    val name: String,
//    val img: RecipeImg,
//    val ingredients: List<String>,
//    val steps: List<String>,
//    val category: String,
    val sourceName: String,
    val sourceUri: String
) {

    //    Compare Recipes
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Recipe

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
//Create Recipes although its private and you cant access it

    companion object {
        fun create(
            id: RecipeId,
            name: String,
//            img: RecipeImg,
//            ingredients: List<String>,
//            steps: List<String>,
//            category: String,
            sourceName: String,
            sourceUri: String
        ): Recipe? {
            return Recipe(id, name, sourceName, sourceUri)
        }

    }
}


//@Composable
//fun CreateAddRecipeButton(onClick: () -> Unit) {
//    TextButton(
//        onClick = onClick,
//    ) {
//        Text(
//            text = "add Recipe", //Warum geht R.stringRes... nicht?
//            style = MaterialTheme.typography.body1,
//            fontSize = 10.sp
//        )
//    }
//}