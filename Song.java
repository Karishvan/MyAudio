//Karishvan Ragunathan
//Student ID: 501166081
import java.util.Set;
import java.util.TreeSet;

/*
 * A Song is a type of AudioContent. A Song has extra fields such as Artist (person(s) singing the song) and composer 
 */
public class Song extends AudioContent implements Comparable<Song>
{
	public static final String TYPENAME =	"SONG";
	
	public static enum Genre {POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL}; 
	private String artist; 		// Can be multiple names separated by commas
	private String composer; 	// Can be multiple names separated by commas
	private Genre  genre; 
	private String lyrics;
	
	
	public Song(String title, int year, String id, String type, String audioFile, int length, String artist,
			        String composer, Song.Genre genre, String lyrics)
	{
		super(title, year, id, type, audioFile, length);
		this.artist = artist;
		this.composer = composer;
		this.genre = genre;
		this.lyrics = lyrics;
	}
	
	public String getType()
	{
		return TYPENAME;
	}
	
	// Print information about the song. First print the basic information of the AudioContent 
	// by calling the superclass method and then print Artist, composer, Genre 
	public void printInfo()
	{
		super.printInfo();
		System.out.println("Artist: " + artist.toString() + " Composer: " + composer + " Genre: " + genre);
	}
	
	// Play the song by setting the audioFile to the lyrics string and calling the play() method of the superclass
	public void play()
	{
		setAudioFile(lyrics);
		super.play();
	}
	
	public String getComposer()
	{
		return composer;
	}
	
	public void setComposer(String composer)
	{
		this.composer = composer;
	}
	
	public String getArtist()
	{
		return artist;
	}
	public void setArtist(String artist)
	{
		this.artist = artist;
	}
	
	public String getLyrics()
	{
		return lyrics;
	}
	public void setLyrics(String lyrics)
	{
		this.lyrics = lyrics;
	}

	public Genre getGenre()
	{
		return genre;
	}

	public void setGenre(Genre genre)
	{
		this.genre = genre;
	}	
	
	// Two songs are equal if their AudioContent information is equal and both the composer and artists are the same
	public boolean equals(Object other)
	{
		Song otherSong = (Song) other;
		return super.equals(other) && composer.equals(otherSong.composer) && artist.equals(otherSong.artist);
	}
	
	// Compare two songs based on their title
	// This method will allow songs to be sorted alphabetically
	public int compareTo(Song other)
	{
		return this.getTitle().compareTo(other.getTitle());
	}
	
	@Override
	public String getDetails() {
		String text = super.getDetails();
		text += (getArtist());
		text += (getComposer());
		text += (getLyrics());
		return text;
	}
}
