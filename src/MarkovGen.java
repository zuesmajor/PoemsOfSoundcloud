import java.util.*;

public class MarkovGen{

    //sequence of words
    private static int seqWords = 2;
    private Map<String,String> markovTable = new HashMap<String,String>();
    List<String> wordsText = new ArrayList<String>();

    public MarkovGen (String text){
        wordsText = Arrays.asList(text.split(" "));
        //splits the words
        int maxWord = wordsText.size() - seqWords - 1;
        String keyString = null;
        int end;

        //creates the sequence of words which is 2 in this case
        for (int j = 0; j < maxWord; j++){
            keyString = "";
            end = j + seqWords;
            for(int k = j; k < end; k++){
                keyString = keyString + wordsText.get(k) + " ";
            }
            keyString = keyString.trim();		// get rid of trailing spaces

            String value;
            //if in the list then add
            if(markovTable.containsKey(keyString)){
                value = markovTable.get(keyString);
                value += " " + wordsText.get(end);
                markovTable.put(keyString, value);
            }
            else{
                if(end <= maxWord){
                    markovTable.put(keyString, wordsText.get(end));
                }
            }
        }
    }

    public String generateMarkov(int numWords)
    {
        // Build a random string using the above Markov chain table
        String temp = "";
        String keyString = "";
        String createWord = "";

        Random rgen = new Random();		// initialise random number generator
        List<String> lastwords = new ArrayList<String>();
        int possible = wordsText.size() - seqWords;
        int startnum = rgen.nextInt(possible);

        //forms a random word using the max sequence of words
        for (int i = startnum, j = 0; i < startnum+ seqWords; i++,j++){
            createWord = wordsText.get(i);
            System.out.println(wordsText.get(i));
            lastwords.add(j, createWord);
            temp += createWord + " ";
        }

        for(int i = seqWords; i < numWords; i++){
            keyString = "";
            for (int j = 0; j < seqWords; j++){
                keyString = keyString + lastwords.get(j) + " ";
            }

            //delete spaces
            keyString = keyString.trim();

            //if seqWord is in list then add next word
            if(markovTable.containsKey(keyString)){
                List<String> possibleNext = new ArrayList<String>();
                possibleNext = Arrays.asList(markovTable.get(keyString).split(" "));
                int c = possibleNext.size();	//must be at least 1
                int r = rgen.nextInt(c);

                String nextWord = possibleNext.get(r);
                temp += nextWord +" ";
                for (int j = 0; j < seqWords -1; j++){
                    //shifts left
                    lastwords.set(j, lastwords.get(j+1));
                }
                lastwords.set(seqWords -1, nextWord);
            }
        }
        return temp.trim();
    }
}