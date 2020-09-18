import oop.ex6.main.Sjavac;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

/**
 * test the whole program, with test files of sjava type.
 */
public class testSjava {

    @Test
    public void test001to49() throws FileNotFoundException {

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
        assertEquals(0, Sjavac.main(new String[]{"test022.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test023.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test024.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test025.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test026.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"test027.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"test028.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test029.sjava"}));

        // 30 - 39
        assertEquals(0, Sjavac.main(new String[]{"test030.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"test031.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"test032.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"test033.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"test034.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"test035.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test036.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test037.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test038.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"test039.sjava"}));

        // 41 - 49
        assertEquals(0, Sjavac.main(new String[]{"test041.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"test042.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"test043.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test044.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test045.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test046.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test047.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test048.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test049.sjava"}));
    }

        @Test
        public void test050to100() throws FileNotFoundException {

        // 50 - 59
        assertEquals(0, Sjavac.main(new String[]{"test050.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"test051.sjava"}));
//        assertEquals(1, Sjavac.main(new String[]{"test052.sjava"}));
//        assertEquals(1, Sjavac.main(new String[]{"test053.sjava"}));
//        assertEquals(1, Sjavac.main(new String[]{"test054.sjava"}));
//        assertEquals(1, Sjavac.main(new String[]{"test055.sjava"}));
//        assertEquals(0, Sjavac.main(new String[]{"test056.sjava"}));
//        assertEquals(1, Sjavac.main(new String[]{"test057.sjava"}));
//        assertEquals(0, Sjavac.main(new String[]{"test058.sjava"}));
//        assertEquals(0, Sjavac.main(new String[]{"test059.sjava"}));



    }
}
