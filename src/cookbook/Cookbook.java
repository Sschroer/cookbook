package cookbook;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Represents a cookbook containing a collection of recipes. Each cookbook has a
 * title and a list of recipes.
 * 
 * @author Stephen Schroer
 */
@SuppressWarnings("serial")
public class Cookbook implements Serializable {
	/**
     * The title of the cookbook.
     */
	private String title;
	/**
     * The list of recipes contained in the cookbook.
     */
	private ArrayList<Recipe> recipes;

	/**
	 * default Constructor
	 */
	public Cookbook() {
		title = "";
		recipes = new ArrayList<>();
	}

	/**
	 * Constructor that assigns name to new cookbook.
	 * 
	 * @param title
	 *            Cookbook title
	 */
	public Cookbook(String title) {
		this.title = title;
		recipes = new ArrayList<>();
	}

	/**
	 * Consructor that creates a titles cookbook with recipies already in it.
	 * 
	 * @param title
	 *            Cookbook tile
	 * @param recipes
	 *            List of Recipies
	 */
	public Cookbook(String title, ArrayList<Recipe> recipes) {
		this.title = title;
		this.recipes = recipes;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the recipes
	 */
	public List<Recipe> getRecipes() {
		return recipes;
	}

	/**
	 * @param recipes
	 *            the recipes to set
	 */
	public void setRecipes(ArrayList<Recipe> recipes) {
		this.recipes = recipes;
	}

	/**
	 * This method displays the contents of the Cookbook
	 */
	public void displayContents() {
		String contents = "";
		List<String> recipeNames = new ArrayList<>();

		for (Recipe recipe : recipes) {
			recipeNames.add(recipe.getName());
		}

		Collections.sort(recipeNames);

		for (String name : recipeNames) {
			contents += name + "\n";
		}

		System.out.println(contents);
	}
	/**
	 * This method returns the index of a requested recipe.
	 * 
	 * @param recipeName
	 * @return
	 */
	private int getIndex(String recipeName) {
		for (int i = 0; i < recipes.size(); i++) {
			if (recipes.get(i).getName().equalsIgnoreCase(recipeName)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Checks if the recipe list contains a recipe with the given name.
	 * 
	 * @param recipeName
	 *            The name of the recipe to check for.
	 * @return True if the recipe list contains the recipe with the given name,
	 *         false otherwise.
	 */
	public boolean contains(String recipeName) {
		if (getIndex(recipeName) == -1) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Retrieves the recipe with the given name.
	 * 
	 * @param recipeName
	 *            The name of the recipe to retrieve.
	 * @return The recipe object if found, otherwise null.
	 */
	public Recipe getRecipe(String recipeName) {
		if (contains(recipeName)) {
			return recipes.get(getIndex(recipeName));
		} else {
			return null;
		}
	}

	/**
	 * Displays the details of a recipe with the given name.
	 * 
	 * @param recipeName
	 *            The name of the recipe to display.
	 * @throws Exception
	 *             If the recipe with the given name is not found.
	 */
	public void showRecipe(String recipeName) throws Exception {
		if (contains(recipeName)) {
			System.out.println(recipes.get(getIndex(recipeName)).toString());
		}
	}

	@Override
	public String toString() {
		return "Cookbook [title=" + title + ", recipes=" + recipes + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(recipes, title);

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cookbook other = (Cookbook) obj;
		return Objects.equals(recipes, other.recipes)
				&& Objects.equals(title, other.title);
	}

}
