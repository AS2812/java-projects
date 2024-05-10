
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {

   static String extraction(String fofa) {
    StringBuilder roko = new StringBuilder();
    for (int i = 38; i < fofa.length(); i++) {
        if (fofa.charAt(i) == '\"'&&fofa.charAt(i+1)=='*'&&fofa.charAt(i+2)!='>'||fofa.charAt(i) == '\"'&&fofa.charAt(i+1)=='/'&&fofa.charAt(i+2)!='>'||fofa.charAt(i) == '\"'&&fofa.charAt(i+1)=='+'&&fofa.charAt(i+2)!='>'||fofa.charAt(i) == '\"'&&fofa.charAt(i+1)=='-'&&fofa.charAt(i+2)!='>'||fofa.charAt(i) == '\"'&&Character.isDigit(fofa.charAt(i + 1))
&&fofa.charAt(i+2)!='>') {
            if(Character.isDigit(fofa.charAt(i + 1)))roko.append("(");
            roko.append(fofa.charAt(i + 1));
            int j=2;
          while(Character.isDigit(fofa.charAt(i+j)))
          {
             roko.append(fofa.charAt(i + j));
             j++;
          }
            if(Character.isDigit(fofa.charAt(i + 1)))roko.append(")");


        }
    }
    return roko.toString();
}
static double calc(String fofa){
    StringBuilder rivo = new StringBuilder(fofa).reverse();
    Stack<Double> num = new Stack<>();
    Stack<Character> opp = new Stack<>();

    for (int i = 0; i < rivo.toString().length(); i++) {
        char c = rivo.toString().charAt(i);
        if (c == '+' || c == '-' || c == '*' || c == '/') {
            opp.push(c);
        } else if(Character.isDigit(c)) {
            String sos = Character.toString(c);
            int j = 1;
            while(Character.isDigit(rivo.toString().charAt(i + j))) {
                sos = rivo.toString().charAt(i + j) + sos;
                j++;
            }
            i += j;
            num.push(Double.parseDouble(sos));
        }

        if (num.size() >= 2 && !opp.isEmpty()) {
            double num1 = num.pop();
            double num2 = num.pop();

            switch (opp.pop()) {
                case '+':
                    num.push(num1 + num2);
                    break;
                case '-':
                    num.push(num1 - num2);
                    break;
                case '*':
                    num.push(num1 * num2);
                    break;
                case '/':
                    num.push(num1 / num2);
                    break;
            }
        }
    }

    double result = num.pop();
    return result;
}

    static String inno(String fofa) {
        Stack<String> operands = new Stack<>();

        for (int i = fofa.length() - 1; i >= 0; i--) {
            char c = fofa.charAt(i);
            if (isOperator(c)) {
                String operand1 = operands.pop();
                String operand2 = operands.pop();
                String expression = "(" + operand1 + c + operand2 + ")";
                operands.push(expression);
            } else if(Character.isDigit(fofa.charAt(i))){
                int j=1;
                String sos=Character.toString(c);;
                while(Character.isDigit(fofa.charAt(i-j)))
                {
                   sos=fofa.charAt(i-j)+sos;

                   j++;
                }
                i-=j;
                operands.push(sos);
            }
        }

        return operands.pop();
    }

    static boolean isOperator(char c) {
        return c == '*' || c == '/' || c == '+' || c == '-';
    }


public static void main(String[] args) {
    // File path where your XML file is located
    String filePath = "C:\\Users\\ahmed\\IdeaProjects\\xml-data-structure\\src\\xml.xml";

    try {
        // Read the contents of the file into a string
        String filecontent = Files.readString(Paths.get(filePath));

        // Check XML validity
        boolean isValidXML = checkXml(filecontent);
        if (!isValidXML) {
            System.out.println("The XML file is not valid.");
            return; // Exit the program if XML is not valid
        }

        // Do something with the file content
        String fofa = extraction(filecontent);
        System.out.println();
        double fina = calc(fofa);
        String inno = inno(fofa);
        System.out.println("prefix expression-->" + fofa);
        System.out.println("answer-->" + fina);
        System.out.println("infix expression-->" + inno);
    } catch (IOException e) {
        // Handle file reading errors
        e.printStackTrace();
    }
}

static boolean checkXml(String xml){  // check if your xml file is valid or not
        Stack <String> tagStack = new Stack<>();
        int i = 38;
        while (i < xml.length()) {
            if (xml.charAt(i)=='<' && xml.charAt(i+1)=='a') {
                while (xml.charAt(i)!='>') {
                    i++;
                }
            }

            if (xml.charAt(i)=='<' && xml.charAt(i+1)=='o') {
                while (xml.charAt(i)!='>') {
                    i++;
                }
            }

            if (xml.charAt(i)=='<' && xml.charAt(i+1)!='/') {
                String openTag = "<";
                i++;
                while (xml.charAt(i-1)!='>') {
                    openTag += xml.charAt(i);
                    i++;
                }
                tagStack.push(openTag);
                continue;
            }

            if (xml.charAt(i)=='<' && xml.charAt(i+1)=='/') {
                String closeTag = "<";
                i++;
                while (xml.charAt(i-1)!='>') {
                    if (i == xml.length()) {
                        closeTag += xml.charAt(i);
                        break;
                    }
                    closeTag += xml.charAt(i);
                    i++;
                }
                if (tagStack.peek().charAt(1) == closeTag.charAt(2)) {
                    tagStack.pop();
                }
                continue;
           }

            i++;
        }
        return tagStack.isEmpty();
}

}
