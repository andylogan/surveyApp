package surveyapp;
/**
 * @author Brian Logan
 * IT152-1904A-01
 * Prof Cesar Sanchez
 * Nov 2019
 */
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Survey survey1 = new Survey("Simple survey.");
        int respondentID = survey1.generateRespondentID();
//        System.out.println("Your ID is " + respondentID);
        survey1.enterQuestions();
        survey1.askQuestions(respondentID);
        survey1.displaySurveyResults();
//        survey1.topRatedQuestion();
        survey1.lowRatedQuestion();
        System.out.println("Would you like to see the stats for "
                + "a given question? (yes/no)");
        String response = sc.nextLine();
        if ("yes".equals(response)){
            System.out.println("What is the question number?");
            int qno = sc.nextInt();
            survey1.displayQuestionStats(qno);
        }
    }
}
