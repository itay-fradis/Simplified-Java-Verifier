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

        // 1 - 9
        assertEquals(0, Sjavac.main(new String[]{"test001.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"test002.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"test003.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"test004.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test005.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test006.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test007.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test008.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"test009.sjava"}));

        // 11 - 19
        assertEquals(0, Sjavac.main(new String[]{"test011.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"test012.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"test013.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"test014.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"test015.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test016.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test017.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test018.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"test019.sjava"}));

        // 21 - 29
        assertEquals(0, Sjavac.main(new String[]{"test021.sjava"}));
//        assertEquals(0, Sjavac.main(new String[]{"test022.sjava"}));
//        assertEquals(1, Sjavac.main(new String[]{"test023.sjava"}));
//        assertEquals(1, Sjavac.main(new String[]{"test024.sjava"}));
//        assertEquals(1, Sjavac.main(new String[]{"test025.sjava"}));
//        assertEquals(1, Sjavac.main(new String[]{"test026.sjava"}));
//        assertEquals(0, Sjavac.main(new String[]{"test027.sjava"}));
//        assertEquals(0, Sjavac.main(new String[]{"test028.sjava"}));
//        assertEquals(1, Sjavac.main(new String[]{"test027.sjava"}));



    }
}
