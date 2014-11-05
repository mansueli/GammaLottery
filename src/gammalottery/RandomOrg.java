package gammalottery;

/*
 * All components of this software is dual licensed under GNU General Public License v2 (GPL-2) 
 * for personal usage for commercial usage you must contact the author prior distribution, usage.
 *
 * @Author: Rodrigo Mansueli Nunes
 * @e-mail: mansueli@ualberta.ca
 * @site: http://kyllo.com.br
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mansueli
 */
public class RandomOrg {

    /**
     *
     * @return a double value between 0 and 1.
     */
    public static double getRand() {
        String rand;
        try {
            final String query = "http://www.random.org/integers/?format=plain&min=0&max=1000&num=1&base=10&col=1";
            rand = get(query);
            return Double.parseDouble(rand) / 1000.0;
        } catch (Exception e) {
            //Logger.getLogger(RandomOrg.class.getName()).log(Level.SEVERE, null, e);
        }
        System.out.println("Failed to get from Random.org (getRand)");
        return Math.random();
    }
    /**
     *
     * @return a long value between 0 and 1000.
     */
    public static long getRandLong() {
        String rand;
        try {
            final String query = "http://www.random.org/integers/?format=plain&min=0&max=1000&num=1&base=10&col=1";
            rand = get(query);
            return Long.parseLong(rand);
        } catch (Exception e) {
            //Logger.getLogger(RandomOrg.class.getName()).log(Level.SEVERE, null, e);
        }
        System.out.println("Failed to get from Random.org (getRandLong)");
        return (long) (Math.random()*1000);
    }
    
     /**
     *
     * @param min lower range (usually 0)
     * @param max maximum value of to be achieved
     * @return a double value between 0 and 1.
     */
    public static int getRandInteger(int min, int max) {
        String rand;
        try {
            final String query = "http://www.random.org/integers/?format=plain&min=" + 
                                        min + "&max=" + max + "&num=1&base=10&col=1";
            rand = get(query);
            return Integer.parseInt(rand);
        } catch (Exception e) {
            //Logger.getLogger(RandomOrg.class.getName()).log(Level.SEVERE, null, e);
        }
        System.out.println("Failed to get from Random.org (getRandInteger)");
        Random random = new Random();
        int randomNum = random.nextInt((max - min) + 1) + min;
        return randomNum;
    }
    /**
     *
     * @param urlString A website URL which responds with a Plain Text output
     * (UTF-8)
     * @return A String with the contents of the website
     * @throws IOException when it couldn´t load the page.
     */
    public static String get(String urlString) throws IOException {
        HttpURLConnection connection;
        BufferedReader br = null;
        String number = "";
        try {
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            number = br.readLine();
        }catch(Exception e){
            return null;
        } 
        finally {
            if (br != null) {
                br.close();
            }
        }
        return number;
    }
}
