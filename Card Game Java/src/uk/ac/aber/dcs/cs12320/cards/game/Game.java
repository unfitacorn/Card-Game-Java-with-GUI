package uk.ac.aber.dcs.cs12320.cards.game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import uk.ac.aber.dcs.cs12320.cards.manage.GUIcontrol;
import uk.ac.aber.dcs.cs12320.cards.timer.Timer;

public class Game {

	private Scanner scan;
	private GUIcontrol frame = new GUIcontrol();
	ArrayList<Cards> cardPile = new ArrayList<Cards>();
	ArrayList<Cards> visibleCards = new ArrayList<Cards>();
	ArrayList<Score> scores = new ArrayList<Score>();
	Timer stopWatch = new Timer();
	private boolean stopWatchFlag = false;

	public static void printMenu() {
		System.out.println("1 -  print the pack out ");
		System.out.println("2 -  shuffle");
		System.out.println("3 -  deal a card");
		System.out.println("4 -  make a move, last pile onto previous one");
		System.out.println("5 -  make a move, last pile back over two piles");
		System.out.println("6 -  Amalgamate piles in the middle");
		System.out.println("7 -  play for me");
		System.out.println("8 -  show low scores");
		System.out.println("q -  Quit and Submit score");

	}

	public void runMenu() {
		String response;
		do {
			printMenu();
			System.out.println("What would you like to do:");
			scan = new Scanner(System.in);
			response = scan.nextLine().toUpperCase();
			switch (response) {
			case "1":
				printAll();
				break;
			case "2":
				shuffle();
				break;
			case "3":
				if (!stopWatchFlag) {
					stopWatchFlag = true;
					stopWatch.start();
				}

				if (!cardPile.isEmpty()) {
					deal();
				}
				if (cardPile.isEmpty()) {
					frame.finished();
					System.out.println("NO CARDS LEFT");
				}

				break;
			case "4":
				lastPrevious();
				break;
			case "5":
				lastTwo();
				break;
			case "6":
				amalgamate();
				break;
			case "7":
				
				System.out.println("How many times?");
					int times = scan.nextInt();
					for(int i = times; i>0;i--) {
						playForMe();
					}
				
				break;
			case "8":
				showScores();
				break;

			case "Q":
				break;
			default:
				System.out.println("Try again");
			}
		} while (!(response.equals("Q")));
	}

	private void playForMe() {
		boolean flag = false;
		for (int i = visibleCards.size(); i > 1; i--) {
			int number3 = 99;
			int number2 = 99;

			int number1 = visibleCards.indexOf(visibleCards.get(i-1));
			
			if (visibleCards.size() > 1) {
				number2 = visibleCards.indexOf(visibleCards.get(i-2));
			}
			if (visibleCards.size() > 3 && i>4) {
				number3 = visibleCards.indexOf(visibleCards.get(i -4));
			}
			
			System.out.println(visibleCards.indexOf(visibleCards.get(visibleCards.size()-1)));
			System.out.println(visibleCards.size());
			System.out.println(number1);
			System.out.println(number2);
			System.out.println(number3);

			if (number1 - number2 == 1 && visibleCards.size() > 1
					&& check(number1, number2)) {
				flag=true;
				move(number1, number2);
				break;

			}
			if (number1 - number3 == 3 && visibleCards.size() > 3
					&& check(number1, number3)) {
				flag=true;
				move(number1, number3);
				break;

			}
		}
		
		if (!flag) {
			deal();
		}
		

	}

	private void showScores() {
		System.out.println("\tNAME\tSCORE\tTIME");
		for (Score sc : scores) {
			StringBuilder SB = new StringBuilder();
			SB.append(scores.indexOf(sc) + 1);
			SB.append(".   ");
			SB.append(sc.getName());
			SB.append("\t    ");
			SB.append(sc.getScore());
			SB.append("\t    ");
			SB.append(sc.getTime());
			System.out.println(SB);
		}
		System.out.println("");

	}

	private void amalgamate() {

		int number1;
		int number2;
		scan = new Scanner(System.in);
		System.out
				.println("Please enter the position(from left to right) of the card you would like to move:");
		number1 = scan.nextInt() - 1;
		System.out
				.println("Please enter the position(from left to right) you would like to move onto: ");
		number2 = scan.nextInt() - 1;

		if (number1 - number2 == 1 && visibleCards.size() > 1) {

			move(number1, number2);

		} else if (number1 - number2 == 3 && visibleCards.size() > 3) {
			move(number1, number2);
		} else {
			notValid();
		}

	}

	private void lastTwo() {
		int number1 = visibleCards.size() - 1;
		int number2 = visibleCards.size() - 4;

		if (visibleCards.size() > 3) {

			move(number1, number2);

		} else {
			notValid();
		}

	}

	private void lastPrevious() {
		int number1 = visibleCards.size() - 1;
		int number2 = visibleCards.size() - 2;

		if (visibleCards.size() > 1) {
			move(number1, number2);
		} else {
			notValid();
		}

	}

	private void move(int number1, int number2) {

		if (check(number1, number2)) {

			frame.replace(number2, number1);
			visibleCards.set(number2, visibleCards.get(number1));
			visibleCards.remove(number1);

		} else {
			notValid();
		}
	}

	private boolean check(int number1, int number2) {

		char suite1 = checkSuite(number1);
		char suite2 = checkSuite(number2);
		char value1 = checkValue(number1);
		char value2 = checkValue(number2);

		if (suite1 == suite2 || value1 == value2) {
			return true;
		} else {
			return false;
		}

	}

	private void notValid() {
		System.out.println("Not a valid move");

	}

	private void deal() {
		frame.addCard(cardPile.get(0).getFileName());
		visibleCards.add(cardPile.get(0));
		cardPile.remove(0);
	}

	private void shuffle() {
		Collections.shuffle(cardPile);

	}

	private void printAll() {
		for (Cards cd : cardPile) {
			System.out.println("" + cd.getValue() + cd.getSuit());

		}

	}

	private void initialiseCards() throws IOException {
		String filename = "cards.txt";
		try (FileReader fr = new FileReader(filename);
				BufferedReader br = new BufferedReader(fr);
				Scanner infile = new Scanner(br)) {

			for (int i = 0; i < 52; i++) {
				char value = infile.nextLine().charAt(0);
				char suit = infile.nextLine().charAt(0);

				String image = "" + value + suit + ".gif";

				Cards cd = new Cards(suit, value, image);

				cardPile.add(cd);

			}
			infile.close();
		} catch (FileNotFoundException e) {
			System.err
					.println("Please check all files are in the correct place.");
		}

	}

	private void initialiseScores() throws IOException {
		String filename = "scores.txt";
		try (FileReader fr = new FileReader(filename);
				BufferedReader br = new BufferedReader(fr);
				Scanner infile = new Scanner(br)) {

			for (int i = 0; i < 5; i++) {

				String name = infile.next();
				int score = infile.nextInt();
				long time = infile.nextInt();

				Score sc = new Score(name, score, time);

				scores.add(sc);

			}
			infile.close();
		} catch (FileNotFoundException e) {
			System.err
					.println("Please check all files are in the correct place.");
		}

	}

	private void submitScore() {

		stopWatch.stop();
		scan = new Scanner(System.in);

		System.out.println("Please enter your name:");
		String name = scan.nextLine();

		int scr = visibleCards.size();

		long time = stopWatch.getElapsedTimeSecs();

		Score completedScore = new Score(name, scr, time);

		if (cardPile.isEmpty()) {
			for (Score sc : scores) {
				if ((scr < sc.getScore())
						|| ((scr == sc.getScore()) && (time < sc.getTime()))) {
					scores.add(scores.indexOf(sc), completedScore);
					System.out.println("CONGRATS ON THE HIGH SCORE");
					break;
				}

			}
		} else {
			System.out.println("YOU DID NOT FINISH YOUR GAME");
		}
		showScores();
	}

	private void saveScores() {
		try (FileWriter fw = new FileWriter("scores.txt");
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter outfile = new PrintWriter(bw);) {
			for (int i = 0; i < 5; i++) {
				outfile.println(scores.get(i).getName());
				outfile.println(scores.get(i).getScore());
				outfile.println(scores.get(i).getTime());
			}
			outfile.close();

		} catch (IOException e) {
			System.err.println("");
		}
	}

	public char checkSuite(int i) {
		char suite = visibleCards.get(i).getSuit();
		return suite;

	}

	public char checkValue(int i) {
		char value = visibleCards.get(i).getValue();
		return value;

	}

	// --------------------------------------------------------------------------------------
		

	
	public static void main(String[] args) throws IOException {
		Game game = new Game();
		game.initialiseCards();
		game.initialiseScores();
		game.runMenu();
		game.submitScore();
		game.saveScores();
	}

}