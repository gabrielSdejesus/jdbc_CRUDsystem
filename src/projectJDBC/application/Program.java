package projectJDBC.application;

import java.util.Scanner;

import projectJDBC.DAO.DAOsql;
import projectJDBC.DAO.DAOstudent;

public class Program {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int system = 0;

		while (system != 3) {
			UI.firstMenu();
			int choice = scan.nextInt();
			scan.nextLine();
			system = choice;
			System.out.println("\n");

			if (choice == 1) {
				UI.clearScreen();
				System.out.print("Name new DataBase:");
				String name = scan.nextLine();
				DAOsql.createDataBase(name);
			}
			if (choice == 2) {
				while (choice != 9) {
					UI.secondMenu();
					choice = scan.nextInt();
					scan.nextLine();
					System.out.println("\n");
					if (choice >= 4 && choice <= 8 || choice == 10) {
						switch (choice) {
						case 4: {
							System.out.print("Name of DataBase:");
							String dataBase = scan.nextLine();
							DAOsql.setDataBase(dataBase);
							System.out.println("\n");
							break;
						}
						case 5: {
							do {
								DAOstudent.newStudent(scan);
								System.out.print("Continue type it 5 else 0:");
								choice = scan.nextInt();
								scan.nextLine();
							} while (choice == 5);
							break;
						}
						case 6: {
							DAOstudent.queryStudents();
							System.out.println("Press enter to return to the menu");
							scan.nextLine();
							break;
						}
						case 7: {
							do {
								DAOstudent.updateStudent(scan);
								System.out.print("Continue type it 7 else 0:");
								choice = scan.nextInt();
								scan.nextLine();
							} while (choice == 7);
							break;
						}

						case 8: {
							DAOsql.createTable();
							System.out.println("Sucess!");
							break;
						}
						
						case 10:{
							do {
								System.out.print("Name student:");
								String name = scan.nextLine();
								DAOstudent.removeStudent(name);
								System.out.print("Continue type it 10 else 0:\n");
								choice = scan.nextInt();
								scan.nextLine();
							} while (choice == 10);
							break;
						}
						default:
							throw new IllegalArgumentException("Unexpected value: " + choice);
						}
					}
				}
			}

		}
		scan.close();

	}

}
