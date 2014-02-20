import org.junit.Test;

public class FastTest {
    @Test
    public void test() {
        Fast.main(new String[] { "./test/equidistant.txt" });
        StdIn.readString();
    }
}
