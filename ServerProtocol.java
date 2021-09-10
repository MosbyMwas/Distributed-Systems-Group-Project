import java.net.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServerProtocol
{
    private static final int STUDENT_NUMBER = 0;
    private static final int STUDENT_NAME = 1;
    private static final int STUDENT_FACULTY = 2;
    private static final int PERSONAL_CODE = 3;

    private static final int END_STATE = 5;

    private int state = STUDENT_NUMBER;

    // Function to ensure that Student Number keyed in by user has 6 digits.
    public static boolean isValid(String id)
    {
        Pattern p = Pattern.compile ("^\\d{6}$");
        Matcher m = p.matcher(id);
        return(m.matches());
    }
    public String processInput(String theInput)
    {
        String theOutput = null;

        switch (state)
        {
            case STUDENT_NUMBER:
                theOutput = "Please input your Student Number. It should be a 6 digit number.";
                state = STUDENT_NAME;
                break;
            case STUDENT_NAME:
                if (isValid(theInput))
                {
                    theOutput = "Please input your First Name and Last Name";
                    state = STUDENT_FACULTY;
                }
                else
                {
                    theOutput = "You're supposed to input your Student Number. It should be a 6 digit number.";
                    state = STUDENT_NUMBER;
                }
                break;
            case STUDENT_FACULTY:
                if (theInput instanceof String)
                {
                    theOutput = "Please input your Faculty and Course";
                    state = PERSONAL_CODE;
                }
                else
                {
                    theOutput = "You are supposed to input your first name and last name";
                    state = STUDENT_NAME;
                }
                break;
            case PERSONAL_CODE:
                if (theInput instanceof String)
                {
                    theOutput = "Please input a random stream of characters. This will used as your personal code.";
                    state = END_STATE;
                }
                else
                {
                    theOutput = "Please input your Faculty, Course and Degree";
                    state = STUDENT_FACULTY;
                }
                break;
            default:
                theOutput = "Thank you for your input. That is all I need. Bye!!!";
                break;
        }
        return theOutput;
    }
}
