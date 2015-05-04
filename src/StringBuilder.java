import facebook4j.Facebook;
import facebook4j.FacebookFactory;
import facebook4j.auth.AccessToken;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StringBuilder
{
	String sentence;
	List<String> words;
    public StringBuilder(){
        words = new ArrayList<String>();
    }

    public String output(File file) throws IOException {

        /**
         * this is to read in a file and scan it
         */
//    	Scanner scan = new Scanner(file);
//
//    	while(scan.hasNextLine()){
//    		words.add(scan.nextLine());
//    	}
//
//    	for(String i : words){
//    		sentence = sentence + " " + i;
//    	}

		URL url = new URL("http://www.foodnetwork.com/recipes/tyler-florence/crisp-chicken-wings-with-chili-lime-butter-recipe.html");
		Document doc = Jsoup.parse(url, 1000);

        //selects the <p> identifiers
        Element link = doc.select("p").first();
		String websiteContent = doc.body().text(); // + link;
        

        return websiteContent;
    }
}