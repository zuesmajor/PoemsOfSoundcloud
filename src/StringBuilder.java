
import com.tumblr.jumblr.JumblrClient;
import com.tumblr.jumblr.types.Blog;
import com.tumblr.jumblr.types.Post;
import com.tumblr.jumblr.types.TextPost;
import com.tumblr.jumblr.types.User;
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

        JumblrClient client = new JumblrClient("GaECA0CSKRncdDO32pjmYZyMfc0wujXiB7MdFcKahSMXc9AKSg", "J3b9wxhPvPXCL4aK8HY13DXI9gQJxOeXT7b9hiTbUu0AxESXzO");
        client.setToken("YURNbmri7RHlqngYZRGrdejgMxSqrT4zpIvk3HmKbo0sPxKPSd", "HqpG9hLusFUYIDUPwNOjkdBKY0JDIBS5jPQ27Y2gU0PC20UBXB");
        User user = client.user();

        Blog blog = client.blogInfo("thesecretdiaryofjake.tumblr.com");

        List<Post> posts = blog.posts();
        String example = "";
        for(Post i : posts){
            if(i instanceof TextPost){
                example += ((TextPost)i).getBody();
            }
        }

//
//		URL url = new URL("http://www.engadget.com/");
//		Document doc = Jsoup.parse(url, 3*1000);
//
//        //selects the <p> identifiers
//        Element link = doc.select("p").first();
//		String websiteContent = doc.body().text();

        return example;
    }
}