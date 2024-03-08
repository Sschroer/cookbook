package cookbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
				display("");
				cookbookMenu();
				display("");
				break;
			case 2 :
				breakline();
				loadCookbook();
				cookbookMenu();
				display("");
				break;
			case 3 :
				breakline();
				saveCookbook();
				display("Cookbook sucessfully saved.");
				display("");
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
				display("");
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
					display("**Recipes**");
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
					exportRecipe();
					breakline();
					break;
				case 6 :
					breakline();
					display("Exiting...");
					breakline();
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
	 */
	private static void exportRecipe() {
		display("function coming soon");

	}

	/**
	 * finds requested recipie and user chooses to edit recipe name,
	 * ingredients, or instructions.
	 */
	private static void modifyRecipe() {
		display("function coming soon");

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
		display("-----------------------------------------------");
	}

	/**
	 * Displays recipe in currently selected cookbook.
	 */
	private static void showRecipe() {
		display("What recipe do you want to view?");
		scnr.nextLine();

		String recipeName = scnr.nextLine();

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

		String ingredient = "";
		ArrayList<String> ingredientList = new ArrayList<>();

		while (true) {
			ingredient = scnr.nextLine();
			if (ingredient.equalsIgnoreCase("done")) {
				break;
			}

			ingredientList.add(ingredient);
		}

		display("""
				Enter instuctions separately.
				Type 'done' on a new line to finish.
				""");

		StringBuilder instructions = new StringBuilder();

		// Read lines until the user types 'done'
		while (true) {
			String line = scnr.nextLine();
			if (line.equals("done")) {
				break;
			}
			instructions.append(line).append("\n");
		}

		Recipe newRecipe = new Recipe(recipeName, ingredientList,
				instructions.toString());
		currentBook.getRecipes().add(newRecipe);

		display(recipeName + " sucessfully added.");
	}
}
