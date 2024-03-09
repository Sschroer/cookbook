package cookbook;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) throws Exception {
		Cookbook testBook = new Cookbook("My Cookbook");
		Recipe pbj = new Recipe("PB&J");
		Recipe banBr = new Recipe("Bananna Bread");
		
		testBook.getRecipes().add(pbj);
		testBook.getRecipes().add(banBr);
		
		pbj.getIngredients().add("Bread");
		pbj.getIngredients().add("Peanut Butter");
		pbj.getIngredients().add("Jelly");
		pbj.setInstructions("""
				1. Get two slices of bread.
				2. Spread Peanut Butter on one slice.
				3. Spread Jelly of your choice on the other slice.
				4. Put slices together and enjoy.
				""");
		
		System.out.println(testBook.getTitle());
		testBook.displayContents();
		testBook.showRecipe("PB&J");
		
		System.out.println(testBook.contains("Bananna Bread") + " = true");
		System.out.println(testBook.contains("pizza") + " = false");
		
		try {
			testBook.showRecipe("pizza");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		
		}
		
		 
		//below code is to have user input a text block.
		Scanner scanner = new Scanner(System.in);

	        System.out.println("Enter a text block (type 'end' on a new line to finish):");
	        StringBuilder textBlock = new StringBuilder();

	        // Read lines until the user types 'end'
	        while (true) {
	            String line = scanner.nextLine();
	            if (line.equals("end")) {
	                break;
	            }
	            textBlock.append(line).append("\n"); // Append each line to the text block
	        }

	        System.out.println("You entered the following text block:");
	        System.out.println(textBlock.toString());

	        scanner.close();
	        
	}

	//updated toString for recipe class
    public String toString() {
    	StringBuilder sb = new StringBuilder();
        sb.append(name).append("\n");
        sb.append("Ingredients:\n");
        
        // Calculate the midpoint of the ingredients list to split into two columns
        int midpoint = ingredients.size() / 2;

        // Append ingredients in two columns
        for (int i = 0; i < midpoint; i++) {
            sb.append(ingredients.get(i)).append("\t\t").append(ingredients.get(i + midpoint)).append("\n");
        }

        // If the number of ingredients is odd, append the last ingredient in the first column
        if (ingredients.size() % 2 != 0) {
            sb.append(ingredients.get(midpoint)).append("\n");
        }
        
        sb.append("Instructions:\n").append(instructions);
        return sb.toString();
        
        /*
         * return name + "\n" + "Ingredients: " + ingredients + "\n"
				+ "Instructions:\n" + instructions;  //original to string
         */
    }
}
