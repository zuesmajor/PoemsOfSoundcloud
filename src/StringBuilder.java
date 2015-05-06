
import de.voidplus.soundcloud.*;
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
import java.util.Random;
import java.util.Scanner;

public class StringBuilder
{
	String sentence;
	List<String> words;
    Random random = new Random();

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


        /**
         * Soundcloud support
         */
        SoundCloud client = new SoundCloud("6839928acd7d404e2286b2b7bf8207e4",
                "7253a1ea2055aaf115a809f690afbedb", "patrickrsjsu@gmail.com", "lebron23");


        de.voidplus.soundcloud.User  user = client.getMe();

        Integer count = user.getPublicFavoritesCount();
        Integer limit = 5; // = max
        Integer pages = ((int)count/limit)+1;

        List<Comment> comments = new ArrayList<>();
        List<Integer> ids = new ArrayList<>();
        List<Track> tracks = new ArrayList<>();

        // gets all of my favorite and stores them in a track arraylist
        for(int i=0; i<pages; i++) {
            ArrayList<Track> temp_tracks = client.getMeFavorites((i * limit), limit);
            tracks.addAll(temp_tracks);
        }

        // loops through that track arraylist and getting the track ID's
        for(Track i : tracks){
            ids.add(i.getId());
        }


        // initial string builder
        String allComments = "";

        // this picks a random track id and gets entire comments to an arraylist
        int rand = random.nextInt(ids.size());
        comments = client.getCommentsFromTrack(ids.get(rand));

        /** loops through the comments forming a string off their text
         * makes sure that there's no links for the peoples stupid blogs
         */
        for(Comment i : comments){
            if(!i.getBody().contains("http://")) {
                if (!i.getBody().contains("https://")) {
                    allComments += i.getBody();
                }
            }
        }

        /**
         * Website Support
         */
//		URL url = new URL("http://www.azlyrics.com/lyrics/kanyewest/mercy.html  ");
//		Document doc = Jsoup.parse(url, 3*1000);
//
//		String websiteContent = doc.body().text();



        return allComments;
    }
}