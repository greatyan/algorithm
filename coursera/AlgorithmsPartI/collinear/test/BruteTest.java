import org.junit.Test;

public class BruteTest {
    @Test
    public void test() {
        Brute.main(new String[] { "./test/equidistant.txt" });
        StdIn.readString();
    }
}
