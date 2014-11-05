/*
 * All components of this software is dual licensed under GNU General Public License v2 (GPL-2) 
 * for personal usage for commercial usage you must contact the author prior distribution, usage.
 *
 */
package gammalottery;

import org.junit.Test;
import static org.junit.Assert.*;
/**
 * @author:Rodrigo Mansueli Nunes  {@literal <mansueli@ualberta.ca>}
 * <a href="http://kyllo.com.br">kyllo.com.br</a>
 *
 **/
public class RandomOrgTest {
    


    /**
     * Test of getRand method, of class RandomOrg.
     */
    @Test
    public void testGetRand() {
        System.out.println("getRand");
        double expResult = 0.5;
        double result = RandomOrg.getRand();
        assertEquals(expResult, result, 0.5);
    }

    /**
     * Test of getRandLong method, of class RandomOrg.
     */
    @Test
    public void testGetRandLong() {
        System.out.println("getRandLong");
        long expResult = 500;
        long result = RandomOrg.getRandLong();
        assertEquals(expResult, result,500);
    }

    /**
     * Test of getRandInteger method, of class RandomOrg.
     */
    @Test
    public void testGetRandInteger() {
        System.out.println("getRandInteger");
        int min = 0;
        int max = 100;
        int expResult = 50;
        int result = RandomOrg.getRandInteger(min, max);
        assertEquals(expResult, result,50);
    }

    /**
     * Test of get method, of class RandomOrg.
     * @throws java.lang.Exception website error
     */
    @Test
    public void testGet() throws Exception {
        System.out.println("get");
        String urlString = "http://www.random.org/integers/?format=plain&min=0&max=1000&num=1&base=10&col=1";
        int r = 0;
        String result = RandomOrg.get(urlString);
        System.out.println(result);
        r = Integer.parseInt(result);
        assertTrue(r>0);
    }
    
}
