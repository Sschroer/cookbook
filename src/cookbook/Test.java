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
		pbj.addTag("main course");
		
		
		pbj.addTag("Korean");
		
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
				textBlock.append(line).append("\n"); // Append each line to the
														// text block
			}

			System.out.println("You entered the following text block:");
			System.out.println(textBlock.toString());

			scanner.close();
	
	
	
	}
	
	}
