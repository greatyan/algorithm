
public class Outcast {

    private WordNet wordnet;

    public Outcast(WordNet wordnet) {
        this.wordnet = wordnet;
    }

    // given an array of WordNet nouns, return an outcast
    public String outcast(String[] nouns) {
        String returnNoun = null;
        int maxOutcast = -1;

        for (int i = 0; i < nouns.length; i++) {
            int outcast = 0;
            for (int j = 0; j < nouns.length; j++) {
                if (i != j) {
                    outcast += wordnet.distance(nouns[i], nouns[j]);
                }
            }
            if (outcast > maxOutcast) {
                maxOutcast = outcast;
                returnNoun = nouns[i];
            }
        }
        return returnNoun;
    }

    // for unit testing of this class (such as the one below)
    public static void main(String[] args) {
        String path = "./wordnet-testing/wordnet/";
        WordNet wordnet = new WordNet(path + args[0], path + args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(path + args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }
}
