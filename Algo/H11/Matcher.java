/**
 * @author Lukas Szimtenings
 * @author Felix Szimtenings
 * @author Cedric Radtke
 */
public interface Matcher
{
    /**
     * Checks if a character can be matched
     *
     * @param character to be matched
     * @return true if the matcher matches the character, false otherwise
     */
    public boolean match(char character);
}