import oop.ex6.main.Sjavac;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

/**
 * test the whole program, with test files of sjava type.
 */
public class testSjava {

    @Test
    public void test001to9() throws FileNotFoundException {
        //
        assertEquals(0, Sjavac.main(new String[]{"test001.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"test002.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"test003.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"test004.sjava"}));

        assertEquals(1, Sjavac.main(new String[]{"test005.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test006.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test007.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test008.sjava"}));

        assertEquals(0, Sjavac.main(new String[]{"test009.sjava"}));

    }
}
