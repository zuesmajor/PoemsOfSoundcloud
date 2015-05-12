
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
    List<Comment> comments;


    public StringBuilder(){
        words = new ArrayList<String>();
    }

    public String output() throws IOException {

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
         * Sets up the client and logs you in
         */

        SoundCloud client = new SoundCloud("6839928acd7d404e2286b2b7bf8207e4",
                "7253a1ea2055aaf115a809f690afbedb", "xxxx", "xxxx");
        System.out.println("Connecting...");

        // sets the user up for the client so the client is the User
        de.voidplus.soundcloud.User  user = client.getMe();

        Integer count = user.getPublicFavoritesCount();
        Integer limit = 400; // = max
        // lmit on how many pages to search
        Integer pages = ((int)count/limit)+1;

        List<Integer> ids = new ArrayList<>();
        List<Track> tracks = new ArrayList<>();

        System.out.println("Loading Tracks...");
        // gets all of my favorite and stores them in a track arraylist
        for(int i=0; i < pages; i++) {
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
        System.out.println("Chose track " + client.getTrack(ids.get(rand)).getTitle() + " By "
                + client.getTrack(ids.get(rand)).getUser().getUsername());

        comments = client.getCommentsFromTrack(ids.get(rand));

        /** loops through the comments forming a string off their text
         * makes sure that there's no links for the peoples stupid blogs
         */
        System.out.println("Building String...\n");
        String combinedComments = "";
        combinedComments = checkChar(comments);


        /**
         * Website Support
         */
//		URL url = new URL("http://www.azlyrics.com/lyrics/kanyewest/mercy.html  ");
//		Document doc = Jsoup.parse(url, 3*1000);
//
//		String websiteContent = doc.body().text();

        return combinedComments;
        //return websiteContent;
    }

    public String checkChar(List<Comment> array) {
        String allComments = "";
        for (Comment i : array) {
            if (!i.getBody().contains("http://")) {
                if (!i.getBody().contains("https://")) {
                    if (!i.getBody().contains("_")) {
                        if (!i.getBody().contains("*")) {
                            if (!i.getBody().contains("<")) {
                                if(!i.getBody().contains(("www."))) {
                                    allComments += i.getBody();
                                }
                            }
                        }
                    }
                }
            }
        }
        return allComments;
    }
}