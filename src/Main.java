import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.sound.midi.InvalidMidiDataException;

import org.jfugue.Player;

import com.sun.speech.freetts.VoiceManager;


public class Main {
	
	public static String sentence;

    public static void main(String[] v) throws IOException {
    	
        //File file = new File("kendrick.txt");
        StringBuilder sb = new StringBuilder();
        MarkovGen mc = new MarkovGen(sb.output());
        Player player = new Player();

        sentence = " ";
        sentence = mc.generateMarkov(300);
        if(sentence.length() < 30 || sentence.length() == 0){
            sentence = mc.generateMarkov(300);
        }


        com.sun.speech.freetts.Voice v1;
        VoiceManager vm = VoiceManager.getInstance();
        v1 = vm.getVoice("kevin16");


        PrintWriter writer = new PrintWriter("poem.txt");
        String[] words = sentence.split(" ");
        for(int i = 0; i < words.length; i++){
            writer.print(words[i] + " ");
        	System.out.print(words[i] + " ");
        	if(i%8==0) System.out.println(" ");
        }

        writer.close();

        //loads the voice
        v1.allocate();
        
        Thread t1 = new Thread(new Runnable(){
			@Override
			public void run() {
		        v1.speak(sentence);
			}
        });
        
        Thread t2 = new Thread(new Runnable(){
			@Override
			public void run() {
				try {
					player.playMidiDirectly(new File("poppa.mid"));
				}
                catch (IOException e) {
					e.printStackTrace();
				}
                catch (InvalidMidiDataException e) {
					e.printStackTrace();
				}
			}
        });
        
        t1.start();
        t2.start();
    }
}
