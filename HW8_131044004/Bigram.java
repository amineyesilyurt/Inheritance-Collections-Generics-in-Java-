import java.io.FileNotFoundException;

/**
 *
 * @author Amine
 * @param <T>
 */

public interface Bigram<T> {

    /**
     *
     * @param filename
     * @throws java.io.FileNotFoundException
     */
    void readFile(String filename)throws Exception;
    int numGrams();
    int numOfGrams(T _first, T _second);
    String toString();
  
}
