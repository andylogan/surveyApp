package surveyapp;

/**
 * @author Brian Logan IT152-1904A-01 Prof Cesar Sanchez Nov 2019
 */
import java.util.*;

public class Survey {

    /* variables or attributes */
    static int _respondentID = -1;     // static class variable 
    String _surveyTitle;     // instance variable
    String[] _questions;    // 1D array to hold 10 questions
    int[][] _results;       // 2D array to hold the respondents' results

    /* constructors */
    public Survey() {
    }

    ;     // no-argument constructor
    
    public Survey(String surveyTitle) {     // one-argument constructor
        _surveyTitle = surveyTitle;
    }

    /* methods */
    public int generateRespondentID() {
        _respondentID = _respondentID + 1;
        return _respondentID;
    }

    public void displaySurveyResults() {
        /* The Survey class should have a displaySurveyResults() method 
           that prints out the name of the survey and displays the entire 
           grid that holds the results. */
        System.out.println("Survey title: " + _surveyTitle);
        System.out.println(" Question Number:      1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < _respondentID + 1; i++) {
            if (_respondentID < 10) {
                System.out.print(" ");
            }
            System.out.print("Respondent " + i + " results: ");
            for (int j = 0; j < _questions.length; j++) {
                System.out.print(_results[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void displayQuestionStats(int questionNumber) {
        /* The Survey class should have a displayQuestionStats() method 
           that takes an int value that is the question number and displays 
           the responses entered so far for that question in tabular form. */
        System.out.println("The results of question " + questionNumber + " are: ");
        for (int i = 1; i < 11; i++) {    // i represents respondent IDs
            System.out.print("Respondent " + i + ": ");
            System.out.println(_results[i][questionNumber]);
        }
    }

    public void enterQuestions() {
        /* Your Survey class should store 10 questions in an array of Strings. 
           Your class should have an enterQuestions() method that allows the user 
           to enter 10 questions for a 10-question survey. 
           This should be done prior to the survey application starting a survey. */

        // taking String array input from user
        System.out.println("You will now enter the questions for the survey.");
        System.out.println();
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the number of survey questions.");
        int length = sc.nextInt();
        sc.nextLine();

        // create a String array to save user input
        _questions = new String[length];

        // might not be a good place to create this array
        _results = new int[11][_questions.length];

        // loop over array to save user input
        for (int i = 0; i < length; i++) {
            System.out.println("Please enter a survey question.");
            _questions[i] = sc.nextLine();
        }

        // show the user the questions they entered
        System.out.println("The survey questions you have entered are: ");
        System.out.println();
        for (int i = 0; i < length; i++) {
            System.out.println(i + 1 + ": " + _questions[i]);
        }
        System.out.println();
    }

    public void askQuestions(int respondentID) {
//        int rID = respondentID;
        _respondentID = respondentID;
        int response;
        Scanner sc = new Scanner(System.in);
        System.out.println("Respondent " + respondentID + ", please respond to "
                + "the following survey questions.");
        for (int i = 0; i < _questions.length; i++) {
            response = presentQuestion(i, _respondentID);
            logResponse(_respondentID, i, response);
        }
        System.out.println();
        System.out.println("Current stats:");
        displaySurveyResults();
        topRatedQuestion();
        lowRatedQuestion();
        if (_respondentID < 10) {
            moreRespondents(_respondentID);
        }
        System.out.println();
        System.out.println();
    }

    public void moreRespondents(int respondentID) {
        int rID = respondentID;
        Scanner sc = new Scanner(System.in);
//        System.out.println();
        System.out.println("Are there more respondents? (yes/no)");
        String moreRespondents = sc.nextLine();
        if ("yes".equals(moreRespondents)) {
            rID++;
            askQuestions(rID);
        }
    }

    public void logResponse(int respondentID, int questionNumber, int response) {
        /* Create a method in your Survey class called "logResponse()." 
           This method should take three arguments. The first argument is 
           an int value (which is the respondent id); the second argument is 
           an int value (which is the question number); and the third argument is 
           an int value, which is the response entered (value from 1 to 5). 
           This method should enter the response into the right location 
           on the survey grid that corresponds to the respondent ID and 
           the question number. */

        int rID = respondentID;
        int qNo = questionNumber;
        _results[rID][qNo] = response;
    }

    public void topRatedQuestion() {
        int[] tops = new int[_results.length];
        int maxValue = 0;
        int topQuestion = 0;
        int rowTotal = 0;
        
        for (int row = 0; row < _results.length; row++) {    
            for (int col = 0; col < _results[row].length; col++) {
                rowTotal += _results[row][col];
            }
            tops[row] = rowTotal;
            rowTotal = 0;
        }
        for (int i = 0; i < tops.length; i++) {
            if (tops[i] > maxValue) {
                topQuestion = i;
                maxValue = tops[i];
            }
        }
        topQuestion++;
        System.out.println("top question is: " + topQuestion);
        System.out.println("maxSum is: " + maxValue);
    }

    
    
    public void lowRatedQuestion() {
        int[] bottoms = new int[_results.length];
        int minValue = 100;
        int bottomQuestion = 0;
        int rowTotal = 0;
        
        for (int row = 0; row < _results.length; row++) {   //respondents
            for (int col = 0; col < _results[row].length; col++) {  //question No
                rowTotal += _results[row][col];
            }
            bottoms[row] = rowTotal;    // set row total to an array element
            rowTotal = 0;   // clear variable for use on a new row
        }
        for (int i = 0; i < bottoms.length; i++) {
            if (bottoms[i] < minValue) {
                bottomQuestion = i;
                minValue = bottoms[i];
            }
        }
        System.out.println("bottom question is: " + bottomQuestion);
        System.out.println("minSum is: " + minValue);
        System.out.println();
    }

    
    
    
    public void presentQuestion() {
        System.out.println("Which question would you like to view? Enter an integer.");
        Scanner sc = new Scanner(System.in);
        int userInput = sc.nextInt();
        System.out.println("That question is: " + _questions[userInput]);
    }

    public int presentQuestion(int questionNumber, int respondentID) {
        int response;
        Scanner sc = new Scanner(System.in);
//        System.out.println("on a scale from 1 to 5 where 5 is greatest.");
        System.out.println(_questions[questionNumber]);
        response = sc.nextInt();
        while (response > 5 || response < 1) {
            System.out.println("Invalid entry. Enter a number between 1 and 5.");
            response = sc.nextInt();
        }
        return response;
    }
} // end of class

//import javax.swing.JOptionPane;
//Survey survey1 =  new Survey();
//JOptionPane.showInputDialog("Please type a name for this survey: ");
//   // saving user input inside a 2D array in Java
//        System.out.println("Please enter number of rows and columns of 2D array");
//        int rows = sc.nextInt();
//        int columns = sc.nextInt();
//
//        int[][] data = new int[rows][columns];
//        System.out.println("Please enter array elements row by row");
//
//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < columns; j++) {
//                int value = sc.nextInt();
//                data[i][j] = value;
//            }
//        }
//
//        System.out.println("The 2d int array input from user is : ");
//        System.out.println(Arrays.deepToString(data));
//
//        sc.close();
//        }
