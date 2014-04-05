package com.actuate.wyan;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

public class SubstringWithConcatenationOfAllWords {

    @Test
    public void test() {
        assertEquals(new int[] { 0 }, new Solution().findSubstring(
                "a",
                new String[] { "a" }));

        assertEquals(new int[] { 13 }, new Solution().findSubstring(
                "lingmindraboofooowingdingbarrwingmonkeypoundcake",
                new String[] { "fooo", "barr", "wing", "ding", "wing" }));
        assertEquals(new int[] { 0, 9 }, new Solution().findSubstring(
                "barfoothefoobarman", new String[] { "foo", "bar" }));
        assertEquals(
                new int[] { 935 },
                new Solution()
                        .findSubstring(
                                "pjzkrkevzztxductzzxmxsvwjkxpvukmfjywwetvfnujhweiybwvvsrfequzkhossmootkmyxgjgfordrpapjuunmqnxxdrqrfgkrsjqbszgiqlcfnrpjlcwdrvbumtotzylshdvccdmsqoadfrpsvnwpizlwszrtyclhgilklydbmfhuywotjmktnwrfvizvnmfvvqfiokkdprznnnjycttprkxpuykhmpchiksyucbmtabiqkisgbhxngmhezrrqvayfsxauampdpxtafniiwfvdufhtwajrbkxtjzqjnfocdhekumttuqwovfjrgulhekcpjszyynadxhnttgmnxkduqmmyhzfnjhducesctufqbumxbamalqudeibljgbspeotkgvddcwgxidaiqcvgwykhbysjzlzfbupkqunuqtraxrlptivshhbihtsigtpipguhbhctcvubnhqipncyxfjebdnjyetnlnvmuxhzsdahkrscewabejifmxombiamxvauuitoltyymsarqcuuoezcbqpdaprxmsrickwpgwpsoplhugbikbkotzrtqkscekkgwjycfnvwfgdzogjzjvpcvixnsqsxacfwndzvrwrycwxrcismdhqapoojegggkocyrdtkzmiekhxoppctytvphjynrhtcvxcobxbcjjivtfjiwmduhzjokkbctweqtigwfhzorjlkpuuliaipbtfldinyetoybvugevwvhhhweejogrghllsouipabfafcxnhukcbtmxzshoyyufjhzadhrelweszbfgwpkzlwxkogyogutscvuhcllphshivnoteztpxsaoaacgxyaztuixhunrowzljqfqrahosheukhahhbiaxqzfmmwcjxountkevsvpbzjnilwpoermxrtlfroqoclexxisrdhvfsindffslyekrzwzqkpeocilatftymodgztjgybtyheqgcpwogdcjlnlesefgvimwbxcbzvaibspdjnrpqtyeilkcspknyylbwndvkffmzuriilxagyerjptbgeqgebiaqnvdubrtxibhvakcyotkfonmseszhczapxdlauexehhaireihxsplgdgmxfvaevrbadbwjbdrkfbbjjkgcztkcbwagtcnrtqryuqixtzhaakjlurnumzyovawrcjiwabuwretmdamfkxrgqgcdgbrdbnugzecbgyxxdqmisaqcyjkqrntxqmdrczxbebemcblftxplafnyoxqimkhcykwamvdsxjezkpgdpvopddptdfbprjustquhlazkjfluxrzopqdstulybnqvyknrchbphcarknnhhovweaqawdyxsqsqahkepluypwrzjegqtdoxfgzdkydeoxvrfhxusrujnmjzqrrlxglcmkiykldbiasnhrjbjekystzilrwkzhontwmehrfsrzfaqrbbxncphbzuuxeteshyrveamjsfiaharkcqxefghgceeixkdgkuboupxnwhnfigpkwnqdvzlydpidcljmflbccarbiegsmweklwngvygbqpescpeichmfidgsjmkvkofvkuehsmkkbocgejoiqcnafvuokelwuqsgkyoekaroptuvekfvmtxtqshcwsztkrzwrpabqrrhnlerxjojemcxel",
                                new String[] { "dhvf", "sind", "ffsl", "yekr",
                                        "zwzq", "kpeo", "cila", "tfty", "modg",
                                        "ztjg", "ybty", "heqg", "cpwo", "gdcj",
                                        "lnle", "sefg", "vimw", "bxcb" }));

    }

    public static void assertEquals(int[] values, ArrayList<Integer> results) {
        if (values.length == results.size()) {
            for (int i = 0; i < values.length; i++) {
                if (values[i] != results.get(i)) {
                    Assert.fail(values[i] + "!=" + results.get(i));
                }
            }
        } else {
            Assert.fail("length diff");
        }
    }

    public class Solution {

        class CharNode {
            char ch;
            Object value;
            HashMap<Character, CharNode> nodes;

            CharNode(char ch) {
                this.ch = ch;
            }

            CharNode get(char ch) {
                if (nodes != null) {
                    CharNode node = nodes.get(ch);
                    if (node != null) {
                        return node;
                    }
                }
                return null;
            }

            CharNode add(char ch) {
                CharNode node = get(ch);
                if (node != null) {
                    return node;
                }

                if (nodes == null) {
                    nodes = new HashMap<Character, CharNode>();
                }
                node = new CharNode(ch);
                nodes.put(ch, node);
                return node;
            }
        }

        class WordTree {
            int[] wordCount;
            CharNode root;
        }

        CharNode addWord(CharNode root, String word) {
            CharNode node = root;
            for (int i = 0; i < word.length(); i++) {
                node = node.add(word.charAt(i));
            }
            return node;
        }

        WordTree createWordTree(String[] words) {
            int[] wordcount = new int[words.length];
            CharNode root = new CharNode('\0');
            for (int i = 0; i < words.length; i++) {
                CharNode leafNode = addWord(root, words[i]);
                if (leafNode.value == null) {
                    leafNode.value = Integer.valueOf(i);
                    wordcount[i]++;
                } else {
                    int wordId = (Integer) leafNode.value;
                    wordcount[wordId]++;
                }
            }
            WordTree tree = new WordTree();
            tree.root = root;
            tree.wordCount = wordcount;
            return tree;
        }

        int matchWord(CharNode root, String word, int offset, int size) {
            char ch = word.charAt(offset++);
            CharNode node = root.get(ch);
            if (node != null) {
                for (int i = 1; i < size; i++) {
                    ch = word.charAt(offset++);
                    node = node.get(ch);
                    if (node == null) {
                        return -1;
                    }
                }
                assert node != null;
                return (Integer) node.value;
            }
            return -1;
        }

        public ArrayList<Integer> findSubstring(String S, String[] L) {
            ArrayList<Integer> result = new ArrayList<Integer>();
            int wordSize = L[0].length();
            int totalSize = wordSize * L.length;
            if (S.length() < totalSize) {
                return result;
            }
            WordTree tree = createWordTree(L);
            for (int i = 0; i <= S.length() - totalSize; i++) {
                if (matches(tree, S, i, L.length, wordSize)) {
                    result.add(i);
                }
            }
            return result;
        }

        private boolean matches(WordTree tree, String word, int offset,
                int wordCount, int wordSize) {
            int[] usedWord = new int[wordCount];
            for (int i = 0; i < wordCount; i++) {
                int wordId = matchWord(tree.root, word, offset, wordSize);
                if (wordId != -1) {
                    usedWord[wordId]++;
                    if (usedWord[wordId] <= tree.wordCount[wordId]) {
                        offset += wordSize;
                        continue;
                    }
                }
                return false;
            }
            return true;
        }
    }
}
