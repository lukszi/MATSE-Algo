/**
 * @author Lukas Szimtenings
 * @author Felix Szimtenings
 * @author Cedric Radtke
 */
public class SingleMatcher implements Matcher
{
    private char matches;

    /**
     * Creates a matcher that matches only a single sign
     * @param matches sign to be matched
     */
    public SingleMatcher(char matches){
        this.matches = matches;
    }

    @Override
    public boolean match(char character)
    {
        return character==matches;
    }
}
