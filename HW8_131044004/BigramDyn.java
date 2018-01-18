import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

/**
 *
 * @author Amine
 * @param <T>
 */
public class BigramDyn<T> implements Bigram<T> {

    private String[] arrStrings;
    private int dataType;
    private int countOfBgs;
    private boolean validData;

    public boolean isValidData() {
        return validData;
    }

    public void setValidData(boolean validData) {
        this.validData = validData;
    }

    public BigramDyn() {
        setDataType(0);
        setCountOfBgs(0);
        setValidData(true);
    }

    public BigramDyn(int dataType) {
        setDataType(dataType);
        setCountOfBgs(0);
        setValidData(true);
    }

    public int getCountOfBgs() {
        return countOfBgs;
    }

    public void setCountOfBgs(int countOfBgs) {
        this.countOfBgs = countOfBgs;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public String[] getArrStrings() {
        return arrStrings;
    }

    public void setArrStrings(String[] arrStrings) {
        this.arrStrings = arrStrings;
    }

    public void readFile(String filename) throws Exception {

        BufferedReader rd = null;
        rd = new BufferedReader(new FileReader(new File(filename)));

        String delimeter = "[ ]";
        // Read all contents of the file.
        String inputLine = null;
        String line = null;
        while ((inputLine = rd.readLine()) != null) {
            arrStrings = inputLine.split(delimeter);
        }
        rd.close();
        if (badData()) {
            setValidData(false);
            setCountOfBgs(-1);

        } else {
            setCountOfBgs(arrStrings.length - 1);

        }

    }

    public int numGrams() {
        return getCountOfBgs();
    }

    public int numOfGrams(T _first, T _second) {
        int counts = 0;
        if (!isValidData()) {
            counts = -1;
        } else {
            Pair<T, T> target = new Pair<>(_first, _second);
            String theFirst = target.first.toString();
            String theSecond = target.second.toString();
            for (int i = 0, j = 1; j < arrStrings.length; ++i, ++j) {
                if ((arrStrings[i].equals(theFirst)) && (arrStrings[j].equals(theSecond))) {
                    ++counts;
                }
            }
        }
        return counts;
    }

    public String toString() {

        for (int i = 0, j = 1; j < arrStrings.length; ++i, ++j) {
            String mypair = arrStrings[i] + "," + arrStrings[j];
            System.out.println(mypair);
        }
        
        return "";
    }

    private boolean badData() {
        boolean isBad = false;
        if (dataType == 1) {
            for (int i = 0; i < arrStrings.length; ++i) {
                for (int j = 0; j < arrStrings[i].length(); ++j) {
                    if (!(Character.isDigit(arrStrings[i].charAt(j)))) {
                        isBad = true;
                    }
                }
            }
        }

        if (dataType == 3) {
            for (int i = 0; i < arrStrings.length; ++i) {
                for (int j = 0; j < arrStrings[i].length(); ++j) {
                    if (!(Character.isDigit(arrStrings[i].charAt(j)))) {
                        if (arrStrings[i].charAt(j) != '.') {
                            isBad = true;
                        }
                    }
                }
            }
        }

        return isBad;

    }

    //Copied from http://stackoverflow.com/questions/156275/what-is-the-equivalent-of-the-c-pairl-r-in-java
    public class Pair<A, B> {

        private A first;
        private B second;

        public Pair(A first, B second) {
            super();
            this.first = first;
            this.second = second;
        }

        public int hashCode() {
            int hashFirst = first != null ? first.hashCode() : 0;
            int hashSecond = second != null ? second.hashCode() : 0;

            return (hashFirst + hashSecond) * hashSecond + hashFirst;
        }

        public boolean equals(Object other) {
            if (other instanceof Pair) {
                Pair otherPair = (Pair) other;
                return ((this.first == otherPair.first
                        || (this.first != null && otherPair.first != null
                        && this.first.equals(otherPair.first)))
                        && (this.second == otherPair.second
                        || (this.second != null && otherPair.second != null
                        && this.second.equals(otherPair.second))));
            }

            return false;
        }

        public String toString() {
            return "(" + first + ", " + second + ")";
        }

        public A getFirst() {
            return first;
        }

        public void setFirst(A first) {
            this.first = first;
        }

        public B getSecond() {
            return second;
        }

        public void setSecond(B second) {
            this.second = second;
        }
    }

}
