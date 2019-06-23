public class H13Main
{
    public static void main(String[] args)
    {
        NFA nfa = new NFA("3,3,1,2,a,1,3,a,2,2,a,2,2,b,2,3,a");
        System.out.println(nfa.testString("abbaabababaa"));
        System.out.println(nfa.testString("aabba"));
        System.out.println(nfa.testString("a"));
        System.out.println(nfa.testString(""));
        System.out.println(nfa.testString("aaaab"));
        System.out.println(nfa.testString("c"));
    }
}
