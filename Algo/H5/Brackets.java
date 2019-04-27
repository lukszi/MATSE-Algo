/**
 * The class contains a function to test, if a String is validly bracketed.
 * @author Lukas Szimtenings
 * @author Felix Szimtenings
 * @author Cedric Radtke
 */
public class Brackets {

    /**
     * Tests if a String is validly bracketed.
     * @param s the String to be tested.
     * @return if the String is validly bracketed.
     * @throws IllegalArgumentException , if s is null.
     */
    public boolean isValid(String s){
        if (s == null){
            throw new IllegalArgumentException("s cannot be null");
        }
        MyDeque bracketStack = new MyDeque();
        for (int i = 0; i < s.length(); ++i){
            char c = s.charAt(i);

            if (c == '(' || c == '{' || c == '['){     //add open bracket to Stack
                bracketStack.addLast(Character.toString(c));
            } else if (c == ')'){
                String openBracket = bracketStack.removeLast();
                if (openBracket == null || !openBracket.equals("(")){   //bracket was not opened, or different bracket kind was opened
                    return false;
                }
            } else if (c == '}'){
                String openBracket = bracketStack.removeLast();
                if (openBracket == null || !openBracket.equals("{")){
                    return false;
                }
            } else if (c == ']'){
                String openBracket = bracketStack.removeLast();
                if (openBracket == null || !openBracket.equals("[")){
                    return false;
                }
            }
        }
        return bracketStack.removeLast() == null;   //bracketStack is empty
    }

    /**
     * tests the class Brackets, using the given cases.
     * @param args not used.
     */
    public static void main(String[] args){
        Brackets test = new Brackets();
        System.out.println(test.isValid("(([[]]))"));   //true
        System.out.println(test.isValid("([)]"));       //false
        System.out.println(test.isValid("([]])"));      //false
        System.out.println(test.isValid("(()))"));      //false
        System.out.println(test.isValid("(()"));        //false
        System.out.println(test.isValid("({[])}"));     //false
        System.out.println(test.isValid(""));           //true
    }
}
