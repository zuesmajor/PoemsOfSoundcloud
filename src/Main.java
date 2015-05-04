import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Pattern;

import javax.sound.midi.InvalidMidiDataException;

import org.jfugue.Player;

import com.sun.speech.freetts.VoiceManager;


public class Main {
	
	public static String sentence;

    public static void main(String[] v) throws IOException {
    	
        File file = new File("kendrick.txt");
        StringBuilder sb = new StringBuilder();
        MarkovGen mc = new MarkovGen(sb.output(file));
        Player player = new Player();

        sentence = " ";
        sentence = mc.generateMarkov(400);

        //System.setProperty("mbrola.base", "/OSx/Users/admin/Downloads/macos/mbrola");

        com.sun.speech.freetts.Voice v1;
        VoiceManager vm = VoiceManager.getInstance();
        v1 = vm.getVoice("kevin16");



        String[] words = sentence.split(" ");
        for(int i = 0; i < words.length; i++){
        	System.out.print(words[i] + " ");
        	if(i%8==0) System.out.println(" ");
        }

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
					player.playMidiDirectly(new File("first.mid"));
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
