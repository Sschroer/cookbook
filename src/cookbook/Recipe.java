package cookbook;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

@SuppressWarnings("serial")
public class Recipe implements Serializable {
	private String name;
	private ArrayList<String> ingredients;
	private String instructions;

	/**
	 * 
	 */
	public Recipe() {
		name = "";
		ingredients = new ArrayList<>();
		instructions = "";
	}

	/**
	 * @param name
	 */
	public Recipe(String name) {
		this.name = name;
		ingredients = new ArrayList<>();
		instructions = "";
	}

	/**
	 * @param name
	 * @param ingredients
	 */
	public Recipe(String name, ArrayList<String> ingredients) {
		this.name = name;
		this.ingredients = ingredients;
		instructions = "";
	}

	/**
	 * @param name
	 * @param ingredients
	 * @param instructions
	 */
	public Recipe(String name, ArrayList<String> ingredients,
			String instructions) {
		this.name = name;
		this.ingredients = ingredients;
		this.instructions = instructions;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the ingredients
	 */
	public ArrayList<String> getIngredients() {
		return ingredients;
	}
	/**
	 * @param ingredients
	 *            the ingredients to set
	 */
	public void setIngredients(ArrayList<String> ingredients) {
		this.ingredients = ingredients;
	}
	/**
	 * @return the instructions
	 */
	public String getInstructions() {
		return instructions;
	}
	/**
	 * @param instructions
	 *            the instructions to set
	 */
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	@Override
	public String toString() {
		return name + "\n" + "Ingredients: " + ingredients + "\n"
				+ "Instructions:\n" + instructions;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ingredients, instructions, name);
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
		Recipe other = (Recipe) obj;
		return Objects.equals(ingredients, other.ingredients)
				&& Objects.equals(instructions, other.instructions)
				&& Objects.equals(name, other.name);
		//auto generated
	}

}
