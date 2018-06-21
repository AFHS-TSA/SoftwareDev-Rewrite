package main.java.app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Var {
	
	// Base Frame width and height props
	public static int width = 1100;
	public static int height = 600;
	public static int minWidth = 200;
	public static int minHeight = 200;

	// Points for the rewards system
	public static int points = 8100;
	// Booleans to check if planets are bought
	public static boolean mercury = false;
	public static boolean venus = false;
	public static boolean earth = false;
	public static boolean mars = false;
	public static boolean jupiter = false;
	public static boolean saturn = false;
	public static boolean uranus = false;
	public static boolean neptune = false;

	// Username
	public static String username;
	
	// ArrayList containing the assignments
	public static ArrayList<Assignments> assignmentsList = new ArrayList<>();

	//ArrayList containing spaced rep sets
	public static ArrayList<SpacedRep> studySets = new ArrayList<SpacedRep>();

	//ArrayList containing flashcard sets
	public static ArrayList<String[]> flashSets = new ArrayList<>();

	//Array with all quotes
	public static String[] quoteList = {"Only I can change my life. No one can do it for me - Carol Burnett", "Life is 10% what happens to you and 90% how you react to it - Charles R. Swindoll", "With the new day comes new strength and new thoughts - Eleanor Roosevelt","Failure will never overtake me if my determination to succeed is strong enough -Og Mandino","It does not matter how slowly you go as long as you do not stop -Confucius", "If you invest nothing, the reward is worth little - Richelle E Goodrich","A man who dares to waste one hour of time has not discovered the value of life. - Charles Darwin", "Nothing in life is to be feared, it is only to be understood. Now is the time to understand more, so that we may fear less. -Marie Curie", "If I have seen further it is by standing on the shoulders of Giants. ― Isaac Newton", "Equipped with his five senses, man explores the universe around him and calls the adventure Science. - Edwin Powell Hubble", "I'm a greater believer in luck, and I find the harder I work the more I have of it - Thomas Jefferson", "Don't wish it were easier. Wish you were better. -Jim Rohn", "There are no shortcuts to any place worth going -Beverly Sills", "If people knew how hard I had to work to gain my mastery, it would not seem so wonderful at all. – Michelangelo", "Hard work beats talent when talent fails to work hard. – Kevin Durant", "Sometimes there’s not a better way. Sometimes there’s only the hard way. – Mary E. Pearson", "Believe you can and you're halfway there - Theodore Roosevelt", "Keep your eyes on the stars and your feet on the ground -Theodore Roosevelt", "Nature uses the longest thread to weave her patterns, so that each small piece of her fabric reveals the organization of the entire tapestry - Richard Feynman", "The first principle is that you must not fool yourself and you are the easiest person to fool - Richard Feynman", "I have no special talents. I am only passionately curious - Albert Einstein", "Pleasure in the job puts perfection in the work -Aristotle", "When you are inspired by some great purpose, some extraordinary project, all your thoughts break their bonds. -Patanjali",  "The only true wisdom is in knowing you know nothing. -Socrates", "I hated every minute of training, but I said, Don't quit. Suffer now and live the rest of your life as a champion. - Muhammad Ali"};

	// Quizlet Client ID
	public static String clientID = "UNNa57NRpT";
	public static int quizletSelectionID;
	public static String quizletSelectionTitle;
	
	// SQL Path (NOTE: Change to your system)
	public static String sqlURL = "jdbc:sqlite:C:\\Users\\Jason\\Desktop\\SoftwareDev-Rewrite\\Data.db"; 
	}
