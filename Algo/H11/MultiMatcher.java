import java.util.List;

/**
 * @author Lukas Szimtenings
 * @author Felix Szimtenings
 * @author Cedric Radtke
 */
public class MultiMatcher implements Matcher
{
    private List<Matcher> matches;

    /**
     * Creates a Matcher matching multiple characters
     * @param matchingCharacters list of matchers that should be matched
     */
    public MultiMatcher(List<Matcher> matchingCharacters){
        matches = matchingCharacters;
    }

    @Override
    public boolean match(char character)
    {
        return matches.stream().anyMatch(matcher -> matcher.match(character));
    }
}