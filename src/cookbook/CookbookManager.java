package cookbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
	private static String desktopPath = System.getProperty("user.home") + "/Desktop";
	private static String folderName = "Cookbooks";
	private static File saveFolder = new File(desktopPath, folderName);
	private static String saveFolderPath = desktopPath + "/" + folderName;

	/*
	 * This will be the main application. This app will display the main menu
	 * where user will be able to.. Create, edit, save, load new cookbooks and
	 * edit/view recipies in them.
	 */
	public static void main(String[] args) {
		saveFolderCheck();
		
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
		String cbName = currentBook.getTitle();

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
					currentBook.displayContents();
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
					breakline();
					break;
			}
		} while (choice != 5);

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
			FileOutputStream newSave = new FileOutputStream(saveFolderPath + "/"+ fileName + ".save");
			ObjectOutputStream oos = new ObjectOutputStream(newSave);
			oos.writeObject(currentBook);
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * This method loads asks the user which cookbook they want to load, and opens it.
	 */
	private static void loadCookbook() {
		display("What cookbook do you want to load?");
		scnr.nextLine(); //if a newline character needs to be consumed always use this.
		
		String cookbookName = scnr.nextLine();
		String fileName = cookbookName.toUpperCase();
		String folderPath = System.getProperty("user.home") + "/Desktop/Cookbooks";
		String filePath = folderPath + "/" + fileName + ".save";
	
		
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
		display ("-----------------------------------------------");
	}
}

