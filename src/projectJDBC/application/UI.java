package projectJDBC.application;

public class UI {

	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public static void firstMenu() {
		System.out.println("    Menu:");
		System.out.println("1 - CreateDataBase (localhost, mySQL)");
		System.out.println("2 - Others");
		System.out.println("3 - Exit");
		System.out.print("Choice:");
	}

	public static void secondMenu() {
		System.out.println("    Sub-Menu:");
		System.out.println("4 - Standardize database used in the program (Mandatory if not created a Database)");
		System.out.println("5 - New Student");
		System.out.println("6 - Query All Students");		
		System.out.println("7 - Update Student");		
		System.out.println("8 - Create Table Student!");
		System.out.println("9 - Back to first menu");
		System.out.println("10 - Remove Student");
		System.out.print("Choice:");
	}
}
