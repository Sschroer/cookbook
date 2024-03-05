package cookbook;

import java.util.Scanner;

/**
 * 
 * @author Stephen Schroer
 *
 */
public class CookbookManager {
	private static Cookbook current; // This is where a new cookbook or old
										// cookbook will be loaded to.
	private static Scanner scnr = new Scanner(System.in);
	private static int choice;

	/*
	 * This will be the main application. This app will display the main menu
	 * where user will be able to.. Create, edit, save, load new cookbooks and
	 * edit/view recipies in them.
	 */
	public static void main(String[] args) {

		while (true) {
			display("""
					~~Cookbook Manager~~
					Please enter your choice.

					1. New Cookbook
					2. Load Cookbook
					3. Save Cookbook
					4. Exit
					""");

			choice = scnr.nextInt();

			switch (choice) {
				case 1 :
					String name;
					display("Type title of new cookbook.");
					scnr.nextLine();
					name = scnr.nextLine();
					current = new Cookbook(name);
					display("");
					cookbookMenu();
					display("");
					break;
				case 2 :
					// ask user for cookbook name, locate cookbook and assign to
					// current.
					// run cookbookMenu method
					display("need to display sub-menu of loaded cookbook");
					cookbookMenu();
					display("");
					break;
				case 3 :
					// save cookbook object to file/directory
					display("cookbook saved.");
					display("");
					break;
				case 4 :
					display("Exiting Cookbook Manager...");
					scnr.close();
					display("");
					System.exit(0);
				default :
					display("Invalid choice.");
					display("");
			}
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
	 * This method displays the menu of the current cookbook.
	 */
	private static void cookbookMenu() {
		String cbName = current.getTitle();

		do {
			display("~" + cbName + "~");
			display("""
					Please enter your choice.

					1. Recipe Index
					2. View Recipe
					3. Add Recipe
					4. Modify Recipe
					5. Return to Main Menu
					""");

			choice = scnr.nextInt();

			switch (choice) {
				case 1 :
					display("**Recipes**");
					current.displayContents();
					display("");
					break;
				case 2 :
					display("viewing recipe");
					// ask for recipie name, find index and display recipe.
					display("");
					break;
				case 3 :
					display("recipe added");
					// create new recipie ask for necessary elements and add to
					// cookbook.
					display("");
					break;
				case 4 :
					display("function coming soon");
					// asks which recipe and what section user wants to edit,
					// then modifies recipie accordingly.
					display("");
					break;
				case 5 :
					display("Exiting...");
					display("");
					break;
				default :
					display("Invalid choice.");
					display("");
					break;
			}
		} while (choice != 5);

	}

}

