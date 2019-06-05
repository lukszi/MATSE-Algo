import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

/**
 * @author Lukas Szimtenings
 * @author Felix Szimtenings
 * @author Cedric Radtke
 */
public class TextSearch
{
    /**
     * Method to test the textSearch
     */
    public static void main(String[] args)
    {
        System.out.println(textSearch("abcabcdababdc.", "ab")); //0, 3, 7, 9
        System.out.println(textSearch("abcabcdababdc.", "c.")); //2, 5, 12
        System.out.println(textSearch("abcabcdababdc.", "c\\.")); //12
        System.out.println(textSearch("abcabcdababdc.", "b[cd]")); //1,4,10
        System.out.println(textSearch("abcabcdababdc.", "a....c")); //0,7
        System.out.println(textSearch("a[aababa][ab]a", "a[ab]a")); //3,5
        System.out.println(textSearch("a[aababa][ab]a", "a.\\[a")); //7
    }

    /**
     * Searches for a pattern in a text
     *
     * @param text to be searched for patterns
     * @param pattern to be looked for in text
     * @return list of starting indices of all found matches
     */
    public static ArrayList<Integer> textSearch(String text, String pattern){
        return findMatches(text, parsePattern(pattern));
    }

    /**
     * Parses a pattern and returns a list of matchers
     *
     * @param pattern to be parsed
     * @return list of matchers represented by the pattern
     */
    private static List<Matcher> parsePattern(String pattern)
    {
        List<Matcher> matchers = new ArrayList<>();

        char[] patternChars = pattern.toCharArray();
        int cursor = 0;

        while(cursor<patternChars.length){

            // Escape sequence found
            if(patternChars[cursor] == '\\'){
                // Parse escape sequence
                matchers.add(parseSingle(pattern.substring(cursor, cursor+2)));
                // Increase by 2 to adjust for leading backslash
                cursor+=2;
            }

            // MultiMatcher found
            else if(patternChars[cursor] == '['){
                // find closing tag
                int closingPosition = cursor;

                while(closingPosition<patternChars.length){
                    if(patternChars[closingPosition] == ']')
                        break;
                    closingPosition++;
                }
                // Check if closing bracket was found
                if(patternChars[closingPosition] != ']')
                    throw new PatternSyntaxException("Opened bracket has no associated closing bracket", pattern, cursor);

                // Parse MultiMatcher, ignore leading "[" and trailing "]" and move the cursor
                matchers.add(parseMulti(pattern.substring(cursor+1,closingPosition)));
                cursor = closingPosition+1;
            }

            // AnyMatcher found
            else if(patternChars[cursor] == '.'){
                matchers.add(new AnyMatcher());
                cursor++;
            }

            // SingleMatcher found
            else{
                matchers.add(parseSingle(pattern.substring(cursor, cursor+1)));
                cursor++;
            }
        }
        return matchers;
    }

    /**
     * Tries to match the text using the naive matching algorithm
     *
     * @param text to be checked
     * @param matchers to be checked with
     * @return starting indices of all matches
     */
    private static ArrayList<Integer> findMatches(String text, List<Matcher> matchers)
    {
        ArrayList<Integer> positions = new ArrayList<>();
        // First matching sign
        int currentOpening = 0;
        // Amount of matched signs in a row
        int found = 0;
        // placement in the text
        int cursor = 0;
        while(cursor < text.length()){
            // Check if sign matches current matcher
            if(matchers.get(found).match(text.charAt(cursor)))
            {
                // If this was the first found sign, move the currentOpening
                if(found==0){
                    currentOpening = cursor;
                }
                // Increase the found signs by one
                found++;
                // Check if all matchers matched, if so add the index of the first matched sign to the list and restart
                if(found == matchers.size())
                {
                    positions.add(currentOpening);
                    found = 0;
                    cursor = currentOpening+1;
                }
            }
            // If the current matcher doesn't match, reset to 0 and try again
            else if(found!=0){
                found = 0;
                cursor = currentOpening+1;
                continue;
            }
            cursor++;
        }

        return positions;
    }

    /**
     * Parses an Expression describing a class of characters
     *
     * @param pattern Partial String from but excluding "[" to but excluding "]"
     * @return A matcher matching said class
     */
    private static MultiMatcher parseMulti(String pattern)
    {
        char[] patternChars = pattern.toCharArray();
        List<Matcher> matchers = new ArrayList<>();

        int cursor = 0;
        while (cursor < patternChars.length) {
            matchers.add(new SingleMatcher(patternChars[cursor]));
            cursor++;
        }

        return new MultiMatcher(matchers);
    }

    /**
     * Parses an Expression describing a single characters
     *
     * @param pattern character to be matched, single character or character prefixed by "\"
     * @return a Matcher for this expression
     */
    private static SingleMatcher parseSingle(String pattern){
        if(pattern.length()>1){
            return new SingleMatcher(pattern.charAt(1));
        }
        else{
            return new SingleMatcher(pattern.charAt(0));
        }
    }
}
