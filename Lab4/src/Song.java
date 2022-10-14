import java.util.Arrays;

//I got help from Chris White, Maria Doan, and Minh Tran.
//They helped me to figure out using proper logics in order and gave advice to simplify some codes. 

/**
 * The Song Class stores the file, artist, and time of a song. 
 * 
 * @author Rebekah Lee
 * @version 0.1
 */
public class Song {
	
	private String title;
	private String artist;
	private int[] time;
	private final static String INFO_DELIMITER = "; ";
	private final static String TIME_DELIMITER = ":";
	private final static int IDX_TITLE = 0;
	private final static int IDX_ARTIST = 1;
	private final static int IDX_TIME = 2;
	
	
	/**
	 * Initialize a Song with the given title, artist, and time.
	 * 
	 * @param title the title
	 * @param artist the artist
	 * @param time the time
	 */
	public Song(String title, String artist, int[] time) {
		this.title = title;
		this.artist = artist;
		this.time = Arrays.copyOf(time, time.length);
	}
	
	
	/**
	 * Initialize Song objects by parsing information stored in Strings. 
	 * 
	 * @param info song information.
	 */
	public Song(String info) {	
		String[] outputSong = info.split(INFO_DELIMITER);
		this.title = outputSong[IDX_TITLE];
		this.artist = outputSong[IDX_ARTIST];

		String[] realTime = outputSong[IDX_TIME].split(TIME_DELIMITER);
		time = new int[realTime.length];
		for(int i=0; i<time.length; ++i) {
			time[i] = Integer.parseInt(realTime[time.length-i-1]);
		}
	}
	
	
	/** 
	 * Get the title and return it.
	 * 
	 * @return title of the song.
	 */
	public String getTitle() {
		return title;
	}
	
	
	/** 
	 * Get the artist and return it.
	 * 
	 * @return artist of the song.
	 */
	public String getArtist() {
		return artist;
	}
	
	
	/** 
	 * Make a copy of the time array and return it.
	 * 
	 * @return a copied array.
	 */
	public int[] getTime() {
		return Arrays.copyOf(time, time.length);
	}
	
	
	/**
	 * Use the same format as info Strings but print it out this time.
	 * 
	 * @return info the String representation of the Song.
	 */
	public String toString() {
		String info = title + INFO_DELIMITER + artist + INFO_DELIMITER+Integer.toString(time[time.length-1]);
		for(int i=time.length-2; i >= 0; --i) {
			info = info + TIME_DELIMITER + String.format("%02d", time[i]);
		}
		return info;
	}

		
	
}
