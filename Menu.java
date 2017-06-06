package facade;


import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import abstractfactory.PortfolioAbstractFactory;
import factory.InvestmentFactory;
import singleton.Logger;
import singleton.Reader;
import adapter.DataAdapter;
import command.AddInvestmentCommand;
import command.Command;
import command.Commander;


public class Menu {

	static int choice;
	static int portfolioChoice;
	static int tradeChoice;
	static int styleChoice;
	static Logger logfile = Logger.getInstance();
	static Scanner input = Reader.getInstance();
	static Commander invoke  = new Commander();


	public Menu() {
		super();
	}


	public static int mainMenu() throws InputMismatchException {

		System.out.println("Choose an option:");
		System.out.println("1. Get current trading information");
		System.out.println("2. Find my investment style");
		System.out.println("3. Portfolio Management");
		System.out.println("4. Exit");

		// Get user's choice:
		try {
			int choice = input.nextInt();
			return choice;
		} catch (InputMismatchException e) {
			System.out.println("Not a number. Try again.");
			return 10;
		}
	}

	/**
	 * Choices is the control mechanism using a switch statement that is
	 * controlled by the user's input from the printMenu method.
	 * 
	 * @param choice
	 *            is the choice made by the user in the printMenu
	 * @throws IOException
	 */
	public static void mainMenuChoices(int choice) throws InputMismatchException, IOException {

		// Define the choices:
		switch (choice) {
		case 1:
			logfile.append("Chose Trading\n");
			DataAdapter.tradesMenu();
			choice = mainMenu(); // Go back to the main menu for another choice
			mainMenuChoices(choice);
			break;

		case 2:
			logfile.append("Chose Styles\n");
			styleChoice = stylesMenu();
			stylesMenuChoices(styleChoice);
			choice = mainMenu(); // Go back to the main menu for another choice
			mainMenuChoices(choice);
			break;

		case 3:
			logfile.append("Chose Portfolio Management\n");
			portfolioChoice = portfoliosMenu();
			portfoliosMenuChoices(portfolioChoice);
			choice = mainMenu();
			mainMenuChoices(choice);
			break;

		case 4:
			logfile.append("Chose to exit\n");
			System.out.println("Have a nice day!"); // End the program
			break;

		default:
			System.out.println("Enter a choice from 1 to 4!"); // If something
			// other than 1, 2, 3, 4 is entered
			choice = mainMenu(); /// Go back to the main menu for another choice
			mainMenuChoices(choice);
		}
	}

	public static int stylesMenu() throws InputMismatchException {

		System.out.println("Choose an investment style:");
		System.out.println("1. Aggressive");
		System.out.println("2. Defensive");
		System.out.println("3. Income");
		System.out.println("4. Hybrid");
		System.out.println("5. Speculative");
		System.out.println("6. Exit");

		// Get user's choice:
		try {
			styleChoice = input.nextInt();
			return styleChoice;
		} catch (InputMismatchException e) {
			System.out.println("Not a number. Try again.");
			return 10;
		}
	}

	public static int portfoliosMenu() throws InputMismatchException {

		System.out.println("Choose an option:");
		System.out.println("1. Create a Portfolio");
		System.out.println("2. Add to a Portfolio");
		System.out.println("3. Get a Portfolio's Current Value");
		System.out.println("4. New Mutual Fund");
		System.out.println("5. Exit");

		// Get user's choice:
		try {
			portfolioChoice = input.nextInt();
			return portfolioChoice;
		} catch (InputMismatchException e) {
			System.out.println("Not a number. Try again.");
			return 10;
		}
	}

	/**
	 * Choices is the control mechanism using a switch statement that is
	 * controlled by the user's input from the printMenu method.
	 * 
	 * @param choice
	 *            is the choice made by the user in the printMenu
	 * @throws IOException
	 */
	public static void stylesMenuChoices(int styleChoice) throws IOException {
		// Define the choices:
		logfile.append("Chose " + styleChoice + "\n");
		PortfolioAbstractFactory.createPortfolio(styleChoice);

		choice = mainMenu(); // Go back to the main menu for another choice
		mainMenuChoices(choice);
		System.out.println("Enter a choice from 1 to 4!"); // If something other
		// than 1, 2, 3, or 4 is entered
		choice = mainMenu();
		mainMenuChoices(choice);
	}

	/**
	 * Choices is the control mechanism using a switch statement that is
	 * controlled by the user's input from the printMenu method.
	 * 
	 * @param choice
	 *            is the choice made by the user in the printMenu
	 * @throws IOException
	 */
	public static void portfoliosMenuChoices(int portfolioChoice) throws IOException {
		
		// Define the choices:
		switch (portfolioChoice) {
		case 1:
			logfile.append("Chose Create a Portfolio: \n");
			System.out.println("Enter the investment symbol: \n");
			String symbol = input.next();
			Command mycommand = new AddInvestmentCommand<Object>(symbol);
			mycommand.execute();
//			invoke.AddInvestmentCommand();
//			InvestmentFactory.addInvestment(symbol);
			System.out.println("Enter the number of shares: \n");
			int shares = input.nextInt();
			InvestmentFactory.setPurchasePrice(symbol,DataAdapter.getLastPrice(symbol),shares);
			choice = mainMenu(); // Go back to the main menu for another choice
			mainMenuChoices(choice);
			break;

		case 2:
			logfile.append("Chose Add to a Portfolio \n");
			System.out.println("Enter the investment symbol: \n");
			symbol = input.next();
			logfile.append("Input: " + symbol + "\n");
			Command mycommand1 = new AddInvestmentCommand<Object>(symbol);
			mycommand1.execute();
			portfolioChoice = portfoliosMenu();
			portfoliosMenuChoices(portfolioChoice);

//			choice = mainMenu(); // Go back to the main menu for another choice
//			mainMenuChoices(choice);
			break;

		case 3:
			logfile.append("Chose get the portfolio's value\n");
			// get the value of the portfolio
			choice = mainMenu(); /// Go back to the main menu for another choice
			mainMenuChoices(choice);
			break;
			
		case 4:
			logfile.append("Chose new mutual fund\n");
			// create a mutual fund
			System.out.println("Enter the investment symbol: \n");
			symbol = input.next();

			
			portfolioChoice = portfoliosMenu();
			portfoliosMenuChoices(portfolioChoice);
			break;

		case 5:
			logfile.append("Return to main menu\n");
			choice = mainMenu();
			mainMenuChoices(choice);
			break;

		default:
			System.out.println("Enter a choice from 1 to 5!"); // If something
			// other than 1, 2, 3, 4, 5 is entered
			choice = mainMenu();
			mainMenuChoices(choice);
		}
	}
}
