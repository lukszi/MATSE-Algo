/**
 * set of game strings utilizing hashing and double hashing to avoid collisions
 * @author Lukas Szimtenings
 * @author Felix Szimtenings
 * @author Cedric Radtke
 */
public class GamesHash
{
    private String[] hashTable = new String[31];

    /**
     * calculates HashValue for a given game
     * @param game to calculate the hash for
     * @return a hash value from 0-99
     */
    private int hashIndex(String game){
        return game.hashCode()%100;
    }

    /**
     * Calculates hashincrement
     * @param game for which a hashIncrement should be calculated
     * @return integer value from 1-10
     */
    private int hashIncrement(String game){
        return (game.hashCode()%1000)/100+1;
    }

    /**
     * adds a hash to the set, throws Exception when it can't be placed
     * @param game to be added to the set
     */
    public void add(String game){
        int h0 = hashIndex(game);
        int hashincrement = hashIncrement(game);
        for (int i = 0; i < 31; ++i){   //hashcode will be equal to h0 after 30 increments
            int hashi = Math.abs((h0 + i * hashincrement)%31);
            if (hashTable[hashi] == null){
                hashTable[hashi] = game;
                return;
            }
        }
        throw new RuntimeException("Value can't be placed");
    }

    /**
     * checks if a game is contained in set
     * @param game to be looked for
     * @return true if game is contained, false otherwise
     */
    public boolean contains(String game){
        int h0 = hashIndex(game);
        int hashincrement = hashIncrement(game);
        for (int i = 0; i < 31; ++i){   //hashcode will be equal to h0 after 30 increments
            int hashi = Math.abs((h0 + i * hashincrement)%31);
            String currentGame = hashTable[hashi];
            if(currentGame==null){
                return false;
            }
            else if (currentGame.equals(game)){
                return true;
            }
        }
        return false;
    }
}
