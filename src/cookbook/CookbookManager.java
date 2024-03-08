package cookbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author Stephen Schroer
 *
 */
public class CookbookManager {
	private static Cookbook currentBook; // This is where a new cookbook or old
											// cookbook will be loaded to.
	private static Scanner scnr = new Scanner(System.in);
	private static int choice;
	private static String desktopPath = System.getProperty("user.home")
			+ "/Desktop";
	private static String folderName = "Cookbooks";
	private static File saveFolder = new File(desktopPath, folderName);
	private static String saveFolderPath = desktopPath + "/" + folderName;

	/*
	 * This will be the main application. This app will display the main menu
	 * choices and will get user input.
	 */
	public static void main(String[] args) {
		saveFolderCheck();

		while (true) {
			displayMainMenu();
			mainMenuChoices();
		}
	}

	/**
	 * simplifying sys.out.println command.
	 * 
	 * @param text
	 */
	private static void display(String text) {
		System.out.println(text);
	}

	/**
	 * Displays main menu for program.
	 */
	private static void displayMainMenu() {
		display("""
				~~Cookbook Manager~~
				Please enter your choice.

				1. New Cookbook
				2. Load Cookbook
				3. Save Cookbook
				4. Exit
				""");
	}

	private static void mainMenuChoices() {
		choice = scnr.nextInt();

		switch (choice) {
			case 1 :
				breakline();
				String name;
				display("Type title of new cookbook.");
				scnr.nextLine();
				name = scnr.nextLine();
				currentBook = new Cookbook(name);
				breakline();
				cookbookMenu();
				breakline();
				break;
			case 2 :
				breakline();
				loadCookbook();
				cookbookMenu();
				breakline();
			case 3 :
				breakline();
				saveCookbook();
				display("Cookbook sucessfully saved.");
				breakline();
				break;
			case 4 :
				breakline();
				display("Exiting Cookbook Manager...");
				scnr.close();
				breakline();
				System.exit(0);
			default :
				breakline();
				display("Invalid choice.");
				breakline();
		}
	}

	/**
	 * This method displays the menu of the current cookbook.
	 */
	private static void cookbookMenu() {
		String cbName = currentBook.getTitle();

		do {
			display("~" + cbName + "~");
			display("""
					Please enter your choice.

					1. Recipe Index
					2. View Recipe
					3. Add Recipe
					4. Modify Recipe
					5. Export Recipe
					6. Return to Main Menu
					""");

			choice = scnr.nextInt();

			switch (choice) {
				case 1 :
					breakline();
					display("**Recipes** \n");
					currentBook.displayContents();
					breakline();
					break;
				case 2 :
					breakline();
					showRecipe();
					breakline();
					break;
				case 3 :
					breakline();
					addRecipe();
					breakline();
					break;
				case 4 :
					breakline();
					modifyRecipe();
					breakline();
					break;
				case 5 :
					breakline();
					
					try {
						exportRecipe();
					} catch (Exception e) {
						display(" The requested recipe is not in the cookbook.");
					}
					
					breakline();
					break;
				case 6 :
					breakline();
					display("Exiting...");
					break;
				default :
					breakline();
					display("Invalid choice.");
					breakline();
					break;
			}
		} while (choice != 6);

	}

	/**
	 * This method exports the toString of requested recipe as a .txt file and
	 * saves it to the desktop for immediate user use.
	 * 
	 * @throws Exception
	 */
	private static void exportRecipe() throws Exception {
		display("Which recipe do you want to export?");
		scnr.nextLine();

		String recipeName = scnr.nextLine();
		String fileName = currentBook.getRecipe(recipeName).getName() + ".txt";
		String filePath = desktopPath + "/" + fileName;
		String fileContent = currentBook.getRecipe(recipeName).toString();

		if (currentBook.contains(recipeName)) {
			try {
				FileWriter writer = new FileWriter(new File(filePath));
				writer.write(fileContent);
				writer.write("\nRecipe from " + currentBook.getTitle());
				writer.write("\nCreated using Cookbook Manager");
				writer.close();
			} catch (IOException e) {
				display(e.getMessage());
			}
			display("This recipe was sucessfully exported to your desktop.");
		}
	}

	/**
	 * finds requested recipe and user chooses to edit recipe name, ingredients,
	 * or instructions.
	 */
	private static void modifyRecipe() {
		display("Which recipe do you want to modify?");
		scnr.nextLine();
		Recipe recipe = currentBook.getRecipe(scnr.nextLine());
		breakline();
		
		do{
			display("**" + recipe.getName() + "**");
			display("""
					
					1. Change name
					2. Edit ingredients
					3. Edit instructions
					4. Return to cookbook menu
					
					""");
			
			choice = scnr.nextInt();
			switch(choice) {
				case 1:
					breakline();
					display("What is the new name for this recipe?");
					scnr.nextLine();
					recipe.setName(scnr.nextLine());
					breakline();
					break;
				case 2:
					breakline();
					display("""
							Please enter all ingredients (with amounts) on seperate lines.
							Enter done on new line when finished.
							""");
					recipe.setIngredients(getNewIngredientList());
					breakline();
					break;
				case 3:
					breakline();
					display("""
							Enter each instuction on a seperate line.
							Type 'done' on a new line to finish.
							""");
					recipe.setInstructions(getNewInstructions());
					breakline();
					break;
				case 4:
					breakline();
					display("Returning to cookbook menu");
					breakline();
			}
			
		}while(choice != 4);
		saveCookbook();
	}

	private static String getNewInstructions() {
		StringBuilder instructions = new StringBuilder();
		scnr.nextLine();

		// Read lines until the user types 'done'
		while (true) {
			String line = scnr.nextLine();
			if (line.equals("done")) {
				break;
			}
			instructions.append(line).append("\n");
		}
		return instructions.toString();
	}

	private static ArrayList<String> getNewIngredientList() {
		String ingredient = "";
		ArrayList<String> ingredientList = new ArrayList<>();

		while (true) {
			ingredient = scnr.nextLine();
			if (ingredient.equalsIgnoreCase("done")) {
				break;
			}

			ingredientList.add(ingredient);
		}
		return ingredientList;
		
		
		
	}

	/**
	 * Checks for a folder on users desktop to save cookbooks to. If there isn't
	 * one then one will be created.
	 */
	private static void saveFolderCheck() {
		if (!saveFolder.exists()) {
			saveFolder.mkdir();
		}
	}

	/**
	 * Saves current cookbook to Cookbooks folder.
	 */
	private static void saveCookbook() {
		String fileName = currentBook.getTitle().toUpperCase();

		try {
			FileOutputStream newSave = new FileOutputStream(
					saveFolderPath + "/" + fileName + ".save");
			ObjectOutputStream oos = new ObjectOutputStream(newSave);
			oos.writeObject(currentBook);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method loads asks the user which cookbook they want to load, and
	 * opens it.
	 */
	private static void loadCookbook() {
		display("What cookbook do you want to load?");
		scnr.nextLine();

		String cookbookName = scnr.nextLine().toUpperCase();
		String folderPath = System.getProperty("user.home")
				+ "/Desktop/Cookbooks";
		String filePath = folderPath + "/" + cookbookName + ".save";

		try {
			FileInputStream fileIn = new FileInputStream(filePath);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			currentBook = (Cookbook) in.readObject();
			in.close();

		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		breakline();
	}

	private static void breakline() {
		display("----------------------------------------------- \n");
	}

	/**
	 * Displays recipe in currently selected cookbook.
	 */
	private static void showRecipe() {
		display("What recipe do you want to view?");
		scnr.nextLine();

		String recipeName = scnr.nextLine();

		breakline();

		try {
			currentBook.showRecipe(recipeName);
		} catch (Exception e) {
			display(e.getMessage());
		}
	}

	/**
	 * Creates new recipie and Adds it to currently loaded cookbook.
	 */
	private static void addRecipe() {
		display("What is this new recipe called?");
		scnr.nextLine();
		String recipeName = scnr.nextLine();

		display("""
				What are its ingredients? (enter amounts too)
				Enter 'done' on a new line when finished.
				""");

		
		ArrayList<String> ingredientList = getNewIngredientList();


		display("""
				Enter each instuction on a seperate line.
				Type 'done' on a new line to finish.
				""");

		String instructions = getNewInstructions();

		Recipe newRecipe = new Recipe(recipeName, ingredientList,
				instructions);
		
		currentBook.getRecipes().add(newRecipe);

		saveCookbook();
		display(recipeName + " sucessfully saved.");
	}
}
