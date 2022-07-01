package hfu.cookingapp.com.routes


import hfu.cookingapp.com.model.RecipeItem
import hfu.cookingapp.com.model.RecipeItems
import hfu.cookingapp.com.model.recipeDao
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

@kotlinx.serialization.Serializable
private data class AddRecipeItemRequest(
    val name: String,
    val img: String,
    val ingredients: String,
    val steps: String,
    val category: String,
    val sourceName: String,
    val sourceUri: String
)

fun Route.recipeRouting() {
    route("/v1/recipes") {
        get {
            call.respond(recipes)
        }

        post {
            val recipeData = call.receive<AddRecipeItemRequest>()
            if (recipeData.name.isBlank()
                || recipeData.img.isBlank()
                || recipeData.ingredients.isEmpty() || recipeData.steps.isEmpty()
                || recipeData.category.isBlank()
            ) {
                call.respond(HttpStatusCode.BadRequest)
            } else {
                recipeDao.addRecipeItem(
                    UUID.randomUUID().toString(),
                    recipeData.name,
                    recipeData.img,
                    recipeData.ingredients,
                    recipeData.steps,
                    recipeData.category,
                    recipeData.sourceName,
                    recipeData.sourceUri,
                )?.let { _ ->
                    call.respond(HttpStatusCode.OK)
                } ?: call.respond(HttpStatusCode.BadRequest)
            }
        }
        //TODO eigentlich noch put wenn man was ändert
    }
}

val prefixImg = "productimage/"

val recipes = listOf(
    RecipeItem(
        id = "0",
        name = "Berry and coconut smoothie bowl",
        img = prefixImg + "SmoothieBowl.jpeg",
        category = "Breakfast",
        ingredients = "150g frozen raspberries\n" +
                "100ml coconut milk (from a carton, not tinned)\n" +
                "1 tablespoon of almond butter\n" +
                "1 large frozen banana\n" +
                "To serve\n" +
                "Sprinkle of fresh or frozen raspberries\n" +
                "A handful of coconut chips\n" +
                "1 tablespoon of almond butter",
        steps = "Place all of the smoothie bowl ingredients into a blender and pulse until smooth. Once smooth, pour into a bowl and top with coconut chips, raspberries and a drizzle of almond butter.",
        sourceName = "Deliciously Ella",
        sourceUri = "https://deliciouslyella.com/recipes/berry-coconut-smoothie-bowl/"
    ),
    RecipeItem(
        id = "1",
        name = "Avocado mushrooms on toast",
        img = prefixImg + "avotoast.jpg",
        category = "Breakfast",
        ingredients = "the flesh of 1 large or 2 small avocados\n" +
                "basil\n" +
                "1 garlic clove, peeled\n" +
                "pine nuts\n" +
                "1 tablespoon tahini\n" +
                "juice of half lemon\n" +
                "1 tablespoon of olive oil\n" +
                "2 tablespoons of water\n" +
                "pinch of salt\n" +
                "For the mushrooms & toast:\n" +
                "250g mushrooms\n" +
                "3 garlic cloves\n" +
                "2 tablespoons of pine nuts\n" +
                "Drizzle of olive oil\n" +
                "Pinch of salt\n" +
                "Juice of 1 lemon\n" +
                "2-4 slices of bread",
        steps = "Place all of the avocado base ingredients into a blender or food processor and pulse until broken down but still a little chunky.\n" +
                "Place a large pan over a medium heat and add a drizzle of olive oil. Once warm, add the mushrooms, pine nuts, garlic and a pinch of salt. Cook for 5-10 minutes until soft.\n" +
                "Toast the bread before spooning some of the avocado mixture on top of each slice, you can use a spoon to spread it out evenly.\n" +
                "Top with the mushrooms from the pan, a sprinkle of fresh basil, a pinch of black pepper and a squeeze of lemon juice.",
        sourceName = "Deliciously Ella",
        sourceUri = "https://deliciouslyella.com/recipes/avocado-mushrooms-on-toast/"
    ),
    RecipeItem(
        id = "2",
        name = "Minestrone Soup",
        img = prefixImg + "minestrone-soup.jpg",
        category = "Lunch",
        ingredients = "2 tablespoons extra-virgin olive oil\n" +
                "1 medium yellow onion, diced\n" +
                "2 medium carrots, chopped\n" +
                "2 celery ribs, thinly sliced\n" +
                "1 teaspoon sea salt, plus more to taste\n" +
                "Freshly ground black pepper\n" +
                "3 garlic cloves, grated\n" +
                "1 (28-ounce) can diced tomatoes\n" +
                "1 1/2 cups white beans or kidney beans, cooked, drained, and rinsed\n" +
                "1 cup chopped green beans\n" +
                "4 cups vegetable broth\n" +
                "2 bay leaves\n" +
                "1 teaspoon dried oregano\n" +
                "1 teaspoon dried thyme\n" +
                "3/4 cup small pasta, elbows, shells, orecchiette\n" +
                "½ cup chopped fresh parsley\n" +
                "Pinches of red pepper flakes\n" +
                "Grated Parmesan cheese, optional, for serving",
        steps = "Heat the oil in a large pot over medium heat. Add the onion, carrots, celery, salt, and several grinds of black pepper, and cook, stirring occasionally, for 8 minutes, until the vegetables begin to soften.\n" +
                "Add the garlic, tomatoes, beans, green beans, broth, bay leaves, oregano, and thyme. Cover and simmer for 20 minutes.\n" +
                "Stir in the pasta and cook, uncovered, for 10 more minutes, until the pasta is cooked through.\n" +
                "Season to taste and serve with parsley, red pepper flakes, and parmesan, if desired.",
        sourceName = "Love & Lemons",
        sourceUri = "https://www.loveandlemons.com/minestrone-soup/"
    ),
    RecipeItem(
        id = "3",
        name = "Roasted Cauliflower Tacos",
        img = prefixImg + "cauliflower-tacos.jpg",
        category = "Dinner",
        ingredients = "1 small cauliflower, outer leaves and core discarded, cut into small florets\n" +
                "1 pound red cabbage, thinly sliced (about 1/2 small red cabbage or 6 cups sliced)\n" +
                "¼ cup extra-virgin olive oil\n" +
                "1 teaspoon ground cumin\n" +
                "1 teaspoon ground coriander\n" +
                "1 teaspoon kosher salt \n" +
                "1 recipe Avocado Sauce\n" +
                "12 corn tortillas, warmed\n" +
                "Thinly sliced radishes, optional\n" +
                "Sliced serrano peppers, optional\n" +
                "Avocado slices, optional\n" +
                "Cilantro, optional",
        steps = "Preheat the oven to 400°F.\n" +
                "Place the cauliflower and cabbage on a large sheet pan. Drizzle with the olive oil and sprinkle with the cumin, coriander, and salt. Use your hands to mix everything together and spread it out into an even layer. Roast, stirring every 15 minutes, until the vegetables are softened and browned in spots, about 45 minutes. (I roasted my cabbage and cauliflower on two separate sheet pans. I cooked the cauliflower for 25 minutes and the cabbage for 10 minutes. Both ways work!)\n" +
                "Evenly divide the Avocado Sauce and roasted vegetables among the tortillas. Serve immediately with the radishes, serranos, avocado slices, and cilantro, if using.",
        sourceName = "Love & Lemons",
        sourceUri = "https://www.loveandlemons.com/cauliflower-tacos/"
    )
)