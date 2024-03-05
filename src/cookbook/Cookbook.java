package cookbook;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

/**
 * This is the cookbook object.
 * @author Stephen Schroer
 *
 */
@SuppressWarnings("serial")
public class Cookbook implements Serializable {
	private String title;
	private ArrayList<Recipe> recipes;

	/**
	 * default Constructor
	 */
	public Cookbook() {
		title = "";
		recipes = new ArrayList<>();
	}

	/**
	 * @param title
	 */
	public Cookbook(String title) {
		this.title = title;
		recipes = new ArrayList<>();
	}

	/**
	 * @param title
	 * @param recipes
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
	public ArrayList<Recipe> getRecipes() {
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
		ArrayList<String> recipeNames = new ArrayList<>();

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
	 * 
	 * @param recipeName
	 * @return
	 */
	private int getIndex(String recipeName) {
		for (int i = 0; i < recipes.size(); i++) {
			if (recipes.get(i).getName().equalsIgnoreCase(recipeName)) {
				return i; // Return index if found
			}
		}
		return -1; // Return -1 if not found
	}

	/**
	 * Checks to see if recipe is in the cookbook.
	 * 
	 * @param recipeName
	 * @return
	 */
	public boolean contains(String recipeName) {
		if (getIndex(recipeName) == -1) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Displays requested recipie
	 * 
	 * @param recipeName
	 * @throws Exception
	 */
	public void showRecipe(String recipeName) throws Exception {
		if (contains(recipeName)) {
			System.out.println(recipes.get(getIndex(recipeName)).toString());
		} else {
			throw new Exception(
					"The recipe requested is not in this cookbook.");
		}
	}

	@Override
	public String toString() {
		return "Cookbook [title=" + title + ", recipes=" + recipes + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(recipes, title);
		//auto generated
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
		//auto generated
	}
	
	
	
	

}
