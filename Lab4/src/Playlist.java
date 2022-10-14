
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringJoiner;

//I got help from Chris White, Maria Doan, and Minh Tran.
//They helped me to figure out using proper logics in order and gave advice to simplify some codes. 


/**
 * The Playlist class uses and ArrayList to encapsulate however much of Songs we have.
 * 
 * @author Rebekah Lee
 * @version 0.1
 */
public class Playlist {
	
	private ArrayList<Song> songs;
	
	
	/**
	 * Initialize the songs field.
	 */
	public Playlist() {
		this.songs = new ArrayList<>();
	}
	
	
	/**
	 * Create a Playlist by parsing a text file of info Strings.
	 * 
	 * @param filename the filename holds the info of the String.
	 * @throws FileNotFoundException if a file is not found, throw an exception. 
	 */
	public Playlist(String filename) throws FileNotFoundException {
		this();
		addSongs(filename);
	}
	
	
	/**
	 * Create a method to get the number of songs.
	 * 
	 * @return returns number of songs. 
	 */
	public int getNumSongs() {
		return songs.size();
	}
	
	
	/**
	 * Get song from the index. 
	 * If index is less than 0 or greater than or equals to getNumSongs, return null.
	 * 
	 * @param index given index.
	 * @return returns song from that index.
	 */
	public Song getSong(int index) {
		if (index < 0 || index >= getNumSongs()) {
			return null;
		}
		return songs.get(index);
	}
	
	
	/**
	 * Get song array at 0.
	 * 
	 * @return a song array.
	 */
	public Song[] getSongs() {
		return songs.toArray(new Song[0]);
	}
	
	
	/**
	 * This method checks if the song is added or not. 
	 * 
	 * @param song given song from Song class.
	 * @return true when a song is added.
	 */
	public boolean addSong(Song song) {
		return addSong(getNumSongs(), song);
	}
	
	
	/**
	 * Add Song to the Playlist at the given index if only if the Song is not null or 
	 * index is not out of bounds.
	 *  
	 * @param index given index.
	 * @param song gjven song.
	 * @return true if add song.
	 */
	public boolean addSong(int index, Song song) {
		
		if((song == null) || (index<0 || index>songs.size())) {
			return false;
		}
		this.songs.add(index, song);
		return true;
		
		}
		
	
	/**
	 * Add the given Songs to the end of the Playlist in the given order.
	 * 
	 * @param playlist given playlist.
	 * @return number of added Songs. 
	 */
	public int addSongs(Playlist playlist) {
		if(playlist == null) {
			return 0;
		}
		else {
			int numSongs = playlist.getNumSongs();
			this.songs.addAll(playlist.songs);
			return numSongs;
		}
	}
	
	
	/**
	 * Read a file of info Strings with the given name.
	 * For each line of the file, create a Song and add it to the end of the Playlist. 
	 * 
	 * @param filename the name of the file.
	 * @return the number of counts.
	 * @throws FileNotFoundException if file not found, throw an exception.
	 */
	public int addSongs(String filename) throws FileNotFoundException {
		File file = new File(filename);
		Scanner sc = new Scanner(file);
		
		int count = 0;
		while(sc.hasNextLine()) {
			songs.add(new Song(sc.nextLine()));
			++count;
		}
		sc.close();
		return count;
	}
	
	
	/**
	 * Remove and return the last Song in the array.
	 * 
	 * @return Song to be removed.
	 */
	public Song removeSong() {
		return removeSong(getNumSongs() - 1);
	}
	
	
	/**
	 * Remove and return the Song of the given index. 
	 * 
	 * @param index given index.
	 * @return null if index out of bounds. Else, return Song.
	 */
	public Song removeSong(int index) {
		if((index<0 || index>songs.size()-1)) {
			return null;
		}
		else {
			Song removeSongs = songs.get(index);
			if(this.songs.remove(removeSongs)) {
			return removeSongs;
		}
		return null;
	}
	}
	
	
	/**
	 * Save the output of toString to a file with the given name.
	 * Overwrite the contents of the file if it already exists.
	 * 
	 * @param filename given file name.
	 * @throws IOException if there is an input/output exception.
	 */
	public void saveSongs(String filename) throws IOException {  
		BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
		writer.write(toString());
		writer.flush();
		writer.close();
	}
	
	
	/**
	 * Use toString method to join sentences by using line separators. 
	 * 
	 * @return Returns the String representations of the Songs joined by line separators.
	 */
	public String toString() {
		StringJoiner sj1 = new StringJoiner(System.lineSeparator());
		for(int i=0; i<songs.size(); ++i) {
			sj1.add(songs.get(i).toString());
		}
		return sj1.toString();
	}
	
	/**
	 * Return the total time of all the Songs as an array of integers. 
	 * Use the same format as the time field in the Song class.
	 * 
	 * @return an int array of total time.
	 */
	public int[] getTotalTime() {
		int seconds = 0;
		int minutes = 0;
		int hours = 0;
		int rollover = 0;
		
		for (int i = 0; i < getNumSongs(); ++i)
		{
			Song temp = getSong(i);
			int [] tempTime = temp.getTime();
			if (tempTime == null)
			{
				seconds = seconds;
				minutes = minutes;
			}
			else if (tempTime.length == 3)
			{
				seconds =  seconds + tempTime[0];
				minutes = minutes + tempTime[1];
				hours = hours + tempTime [2];
			}
			else if(tempTime.length == 2)
			{
			
				seconds =  seconds + tempTime[0];
				minutes = minutes + tempTime[1];
				
			
			}
			else if (tempTime.length == 1)
			{
				seconds = seconds + tempTime[0];
			}
		
			
		}
		
		rollover = seconds/60;
		minutes =  minutes + rollover;
		seconds = seconds%60;
		rollover = minutes/60;
		hours = hours + rollover;
		minutes = minutes%60;
		
		if(seconds+minutes+hours == 0) {
			int[] empty= new int[] {0};
			return empty; 
		}
		else if(minutes+hours == 0) {
			int[] array = new int[] {seconds};
			return array;
		}
		else if(hours == 0) {
			int[] secondArr = new int[] {seconds, minutes};
			return secondArr;
		}
		else {
			int [] tT = new int [] {seconds, minutes, hours};
			return tT;
}
}
}
		
		
		
		
		