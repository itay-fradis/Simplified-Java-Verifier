
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
        public void test050to67() throws FileNotFoundException {

        // 50 - 59
        assertEquals(0, Sjavac.main(new String[]{"test050.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"test051.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test052.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test053.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test054.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test055.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"test056.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test057.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"test058.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"test059.sjava"}));

        // 61 - 67
        assertEquals(1, Sjavac.main(new String[]{"test061.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test062.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test063.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test064.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test065.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test066.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test067.sjava"}));

    }

    @Test
    public void myTest() throws FileNotFoundException {
        // checks for variations of final
        assertEquals(1, Sjavac.main(new String[]{"mytest01.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"mytest02.sjava"}));

        // check  illegal variable name
        assertEquals(1, Sjavac.main(new String[]{"mytest03.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"mytest04.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"mytest05.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"mytest06.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"mytest07.sjava"}));

        //legal names
        assertEquals(0, Sjavac.main(new String[]{"mytest08.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"mytest09.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"mytest10.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"mytest11.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"mytest12.sjava"}));
    }

    @Test
    public void test101to117() throws FileNotFoundException {

        // 101 - 109
        assertEquals(1, Sjavac.main(new String[]{"test101.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test102.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test103.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test104.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test105.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"test106.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"test107.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"test108.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"test109.sjava"}));

        // 110 - 117
        assertEquals(0, Sjavac.main(new String[]{"test110.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"test111.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"test112.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"test113.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test114.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test115.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test116.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test117.sjava"}));

    }

    @Test
    public void test201to300() throws FileNotFoundException {

        // 201 - 209
        assertEquals(0, Sjavac.main(new String[]{"test201.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"test202.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"test203.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"test204.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test205.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test206.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test207.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test208.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"test209.sjava"}));

        // 211 - 219
        assertEquals(0, Sjavac.main(new String[]{"test211.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"test212.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"test213.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"test214.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"test215.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test216.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test217.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test218.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"test219.sjava"}));

        // 221 - 227
        assertEquals(0, Sjavac.main(new String[]{"test221.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"test222.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test223.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test224.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test225.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test226.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"test227.sjava"}));

        // 231 - 239
        assertEquals(0, Sjavac.main(new String[]{"test231.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"test232.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"test233.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"test234.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"test235.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test236.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test237.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test238.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"test239.sjava"}));

        // 241 - 249
        assertEquals(0, Sjavac.main(new String[]{"test241.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"test242.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"test243.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test244.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test245.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test246.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test247.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test248.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test249.sjava"}));

        // 250 - 257
        assertEquals(1, Sjavac.main(new String[]{"test250.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"test251.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test252.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"test253.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test254.sjava"}));
        assertEquals(0, Sjavac.main(new String[]{"test255.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test257.sjava"}));

        // 261 - 269
        assertEquals(1, Sjavac.main(new String[]{"test261.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test262.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test263.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test264.sjava"}));
        assertEquals(1, Sjavac.main(new String[]{"test265.sjava"}));
//        assertEquals(0, Sjavac.main(new String[]{"test266.sjava"}));
//        assertEquals(0, Sjavac.main(new String[]{"test267.sjava"}));
//        assertEquals(1, Sjavac.main(new String[]{"test268.sjava"}));
//        assertEquals(1, Sjavac.main(new String[]{"test269.sjava"}));
//
//        // 270 - 274
//        assertEquals(1, Sjavac.main(new String[]{"test270.sjava"}));
//        assertEquals(0, Sjavac.main(new String[]{"test271.sjava"}));
//        assertEquals(1, Sjavac.main(new String[]{"test272.sjava"}));
//        assertEquals(1, Sjavac.main(new String[]{"test273.sjava"}));
//        assertEquals(1, Sjavac.main(new String[]{"test274.sjava"}));

        // 291
//        assertEquals(1, Sjavac.main(new String[]{"test291.sjava"}));
    }

    @Test
    public void test300to400() throws FileNotFoundException {

        // 301 - 309
//        assertEquals(0, Sjavac.main(new String[]{"test301.sjava"}));
//        assertEquals(0, Sjavac.main(new String[]{"test302.sjava"}));
//        assertEquals(0, Sjavac.main(new String[]{"test303.sjava"}));
//        assertEquals(0, Sjavac.main(new String[]{"test305.sjava"}));
//        assertEquals(1, Sjavac.main(new String[]{"test306.sjava"}));
//        assertEquals(1, Sjavac.main(new String[]{"test307.sjava"}));
//        assertEquals(1, Sjavac.main(new String[]{"test308.sjava"}));
//        assertEquals(1, Sjavac.main(new String[]{"test309.sjava"}));

    }
}
