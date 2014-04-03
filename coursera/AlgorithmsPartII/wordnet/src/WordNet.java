import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class WordNet {

    private static class Synset {
        private int id;
        private String synonyms;
    }

    private Digraph graph;
    private ArrayList<Synset> synsets;
    private HashMap<String, List<Integer>> synonyms;
    private SAP sap;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        readSynset(synsets);
        readHypernym(hypernyms);
        if (new CountRoot(graph).getRootCount() != 1
                || new CheckCycle(graph).hasCycle()) {
            throw new IllegalArgumentException();
        }
        sap = new SAP(graph);
    }

    private static class CountRoot {
        private boolean[] marked;
        private Digraph graph;
        private int rootCount;

        CountRoot(Digraph g) {
            this.marked = new boolean[g.V()];
            this.graph = g;
            rootCount = 0;
            for (int i = 0; i < graph.V(); i++) {
                if (marked[i] == false) {
                    dfs(i);
                }
            }
        }

        private void dfs(int v) {
            marked[v] = true;
            Iterable<Integer> adj = graph.adj(v);
            if (adj == null || adj.iterator().hasNext() == false) {
                rootCount++;
            } else {
                for (Integer w : adj) {
                    if (!marked[w]) {
                        dfs(w);
                    }
                }
            }
        }

        private int getRootCount() {
            return rootCount;
        }
    }

    private static class CheckCycle {
        private boolean[] marked;
        private Digraph graph;
        boolean hasCycle;
        private HashSet<Integer> parents;

        CheckCycle(Digraph g) {
            this.marked = new boolean[g.V()];
            this.graph = g;
            this.hasCycle = false;
            this.parents = new HashSet<Integer>();
            for (int i = 0; i < graph.V(); i++) {
                if (!marked[i]) {
                    dfs(i);
                }
            }
        }

        private void dfs(int v) {
            if (parents.contains(v)) {
                // has cycle...
                hasCycle = true;
                return;
            }
            parents.add(v);
            marked[v] = true;
            Iterable<Integer> adj = graph.adj(v);
            for (Integer w : adj) {
                if (!marked[w]) {
                    dfs(w);
                }
            }
            parents.remove(v);
        }

        private boolean hasCycle() {
            return hasCycle;
        }
    }

    private void readSynset(String file) {
        synsets = new ArrayList<Synset>();
        synonyms = new HashMap<String, List<Integer>>();
        In in = new In(file);
        String line = in.readLine();
        while (line != null) {
            String[] tokens = line.split(",");
            Synset synset = new Synset();
            synset.id = Integer.parseInt(tokens[0]);
            synset.synonyms = tokens[1];
            // synset.gloss = tokens[2];
            addSynset(synset);
            line = in.readLine();
        }
        in.close();
    }

    private void addSynset(Synset synset) {

        synsets.add(synset);
        String[] nouns = synset.synonyms.split(" ");
        for (String noun : nouns) {
            List<Integer> list = synonyms.get(noun);
            if (list == null) {
                list = new ArrayList<Integer>();
                synonyms.put(noun, list);
            }
            list.add(synset.id);
        }
    }

    private void readHypernym(String file) {

        graph = new Digraph(synsets.size());
        In in = new In(file);
        String line = in.readLine();
        while (line != null) {
            String[] tokens = line.split(",");
            int synsetId = Integer.parseInt(tokens[0]);
            for (int i = 1; i < tokens.length; i++) {
                int hypernymId = Integer.parseInt(tokens[i]);
                graph.addEdge(synsetId, hypernymId);
            }
            line = in.readLine();
        }
        in.close();
    }

    // the set of nouns (no duplicates), returned as an Iterable
    public Iterable<String> nouns() {
        return synonyms.keySet();

    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        return synonyms.get(word) != null;
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException();
        }

        List<Integer> synsetId1 = synonyms.get(nounA);
        List<Integer> synsetId2 = synonyms.get(nounB);
        return sap.length(synsetId1, synsetId2);
    }

    // a synset (second field of synsets.txt) that is the common ancestor of
    // nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException();
        }
        List<Integer> synsetId1 = synonyms.get(nounA);
        List<Integer> synsetId2 = synonyms.get(nounB);
        int ancestorId = sap.ancestor(synsetId1, synsetId2);
        if (ancestorId != -1) {
            return synsets.get(ancestorId).synonyms;
        }
        return null;
    }

    private static void createWordNet(String synset, String hypernym) {
        String path = "wordnet-testing/wordnet/";
        new WordNet(path + synset, path + hypernym);
    }

    // for unit testing of this class
    public static void main(String[] args) {
        // createWordNet("synsets3.txt", "hypernymsInvalidTwoRoots.txt");
        createWordNet("synsets3.txt", "hypernymsInvalidTwoRoots.txt");
        createWordNet("synsets.txt", "hypernyms.txt");
    }
}
