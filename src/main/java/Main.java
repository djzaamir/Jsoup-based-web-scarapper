import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.UnknownHostException;

public class Main {
    public static void main(String[] args) {

        //The url and the internal structure of the URL can change
        String target_URL = "https://everydayastronaut.com/orbital-launch-attempt-live-updates/";

        try{

            Document resp_body = Jsoup.connect(target_URL).get();

//            There are two tables on this webpage that we are interested in
//            Starship launch details table and Booster-4 launch details table

            Elements tables  = resp_body.getElementsByTag("table");

            //Print details from both tables in a formatted way.

            for(int i =0; i < tables.size(); i++){
                Element table =  tables.get(i);

                //Get the table title
                String title = table.getElementsByTag("thead").get(0).getElementsByTag("th").get(0).text();

                System.out.println("\n\n=============== " + title + " ===============\n");

                //Get all the data rows for this table
                Elements trs = table.getElementsByTag("tbody")
                        .get(0) //Because there is only a single tbody in each table
                        .children(); //Get all the trs

                //From each row extract individual data points
                for (Element tr:
                     trs) {

                    System.out.printf("=> %s\n", tr.child(0).text().trim());
                    System.out.printf("=> %s\n\n", tr.child(1).text().trim());


                }

            }

        }
        catch(UnknownHostException e){
            System.out.println("everydayastronaut.com not found\nPlease Make sure your internet is connected.");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
