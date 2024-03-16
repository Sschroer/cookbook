package cookbook;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

/**
 * This the Recipe Class
 * 
 * @author Stephen Schroer
 *
 */
@SuppressWarnings("serial")
public class Recipe implements Serializable {
	private String name;
	private ArrayList<String> ingredients;
	private String instructions;
	private HashSet<MealType> tagList;

	/**
	 * 
	 */
	public Recipe() {
		name = "";
		ingredients = new ArrayList<>();
		instructions = "";
		tagList = new HashSet<>();
	}

	/**
	 * @param name
	 */
	public Recipe(String name) {
		this.name = name;
		ingredients = new ArrayList<>();
		instructions = "";
		tagList = new HashSet<>();
	}

	/**
	 * @param name
	 * @param ingredients
	 */
	public Recipe(String name, ArrayList<String> ingredients) {
		this.name = name;
		this.ingredients = ingredients;
		instructions = "";
		tagList = new HashSet<>();
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
		tagList = new HashSet<>();
	}

	/**
	 * @param name
	 * @param ingredients
	 * @param instructions
	 * @param type
	 */
	public Recipe(String name, ArrayList<String> ingredients,
			String instructions, MealType tag) {
		this.name = name;
		this.ingredients = ingredients;
		this.instructions = instructions;

		tagList = new HashSet<>();
		tagList.add(tag);
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

	/**
	 * @return the tagList
	 */
	public HashSet<MealType> getTagList() {
		return tagList;
	}

	/**
	 * @param tagList
	 *            the tagList to set
	 */
	public void setTagList(HashSet<MealType> tagList) {
		this.tagList = tagList;
	}

	/**
	 * This method adds a tag to the tagList
	 * 
	 * @param tag
	 * @return
	 * @throws TypeNotFoundException
	 */
	public boolean addTag(String tag)  {
		String modTag = tag.replaceAll(" ", "_");
		MealType foodType;
		
		try {
			foodType = getType(modTag);
			tagList.add(foodType);
			return true;
			
		} catch (TypeNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	
	}

	/**
	 * This method checks to see if the tag exists in the enum list.
	 * 
	 * @param tag
	 * @return
	 * @throws TypeNotFoundException
	 */
	private MealType getType(String tag) throws TypeNotFoundException {
		for (MealType type : MealType.values()) {
			if (type.toString().equalsIgnoreCase(tag)) {
				return type;
			}
		}

		throw new TypeNotFoundException("The Tag you entered does not exist.");
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(name).append("\n");
		sb.append("Ingredients:\n");

		// Calculate the midpoint of the ingredients list to split into two
		// columns
		int midpoint = ingredients.size() / 2;

		// Find the maximum length of ingredient to determine the width of the
		// first column
		int maxWidth = 0;
		for (String ingredient : ingredients) {
			maxWidth = Math.max(maxWidth, ingredient.length());
		}
		int columnWidth = maxWidth + 5; // Adjust the column width as needed

		// Append ingredients in two columns
		for (int i = 0; i < midpoint; i++) {
			String ingredient1 = ingredients.get(i);
			String ingredient2 = (i + midpoint < ingredients.size())
					? ingredients.get(i + midpoint)
					: "";

			sb.append(String.format("%-" + columnWidth + "s", ingredient1))
					.append("\t\t").append(String
							.format("%-" + columnWidth + "s", ingredient2))
					.append("\n");
		}

		// If the number of ingredients is odd, append the last ingredient in
		// the first column
		if (ingredients.size() % 2 != 0) {
			sb.append(String.format("%-" + columnWidth + "s",
					ingredients.get(midpoint))).append("\n");
		}

		sb.append("Instructions:\n").append(instructions)
				.append("Tags: " + tagList);
		return sb.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(ingredients, instructions, name);
		// auto generated
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
		// auto generated
	}

}
