//Karishvan Ragunathan
//Student ID: 501166081
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringTokenizer;

// Simulation of a Simple Text-based Music App (like Apple Music)

public class MyAudioUI {
	public static void main(String[] args)
	{
		// Simulation of audio content in an online store
		// The songs, podcasts, audiobooks in the store can be downloaded to your library
		AudioContentStore store = new AudioContentStore();
		
		// Create my music library
		Library library = new Library();

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");
		
		
		
			
		
		// Process keyboard actions
		while (scanner.hasNextLine())
		{
			String action = scanner.nextLine();
			try {
				
			if (action == null || action.equals("")) 
			{
				System.out.print("\n>");
				continue;
			}
			else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
				return;
			
			else if (action.equalsIgnoreCase("STORE"))	// List all songs
			{
				store.listAll(); 
			}
			else if (action.equalsIgnoreCase("SONGS"))	// List all songs
			{
				library.listAllSongs(); 
			}
			else if (action.equalsIgnoreCase("BOOKS"))	// List all songs
			{
				library.listAllAudioBooks(); 
			}
			else if (action.equalsIgnoreCase("PODCASTS"))	// List all songs
			{
				library.listAllPodcasts(); 
			}
			else if (action.equalsIgnoreCase("ARTISTS"))	// List all songs
			{
				library.listAllArtists(); 
			}
			else if (action.equalsIgnoreCase("PLAYLISTS"))	// List all play lists
			{
				library.listAllPlaylists(); 
			}
			//Download all content from a range of indexes
			else if (action.equalsIgnoreCase("DOWNLOAD")) 
			{
				int fromIndex = 0;
				int toIndex = 0;
				
				System.out.print("From Store Content #: ");
				if (scanner.hasNextInt()) {
					fromIndex = scanner.nextInt();
					scanner.nextLine(); // consume nl
				}
				
				
				System.out.print("To Store Content #: ");
				if (scanner.hasNextInt()) {
					toIndex = scanner.nextInt();
					scanner.nextLine();
				}
				
				//Go through each element and download them
				ArrayList<AudioContent> contentsDownloaded = store.getContent(fromIndex, toIndex);
				for (AudioContent content : contentsDownloaded) {
					try {
						library.download(content);
					} catch (AudioContentAlreadyDownloadedException e) {
						//If content is already downloaded print error message
						System.out.println(e.getMessage());
					}
				}
				
//				if (content == null)
//					System.out.println("Content Not Found in Store");
//				else if (!library.download(content))
//						
									
			}
			else if (action.equalsIgnoreCase("PLAYSONG")) 
			{
				int index = 0;

				System.out.print("Song Number: ");
				if (scanner.hasNextInt())
				{
					index = scanner.nextInt();
				// consume the nl character since nextInt() does not
					scanner.nextLine(); 
				}
				library.playSong(index);
					
			}
			else if (action.equalsIgnoreCase("BOOKTOC")) 
			{
				int index = 0;

				System.out.print("Audio Book Number: ");
				if (scanner.hasNextInt())
				{
					index = scanner.nextInt();
					scanner.nextLine();
				}
				library.printAudioBookTOC(index);
					
			}
			else if (action.equalsIgnoreCase("PLAYBOOK")) 
			{
				int index = 0;

				System.out.print("Audio Book Number: ");
				if (scanner.hasNextInt())
				{
					index = scanner.nextInt();
				}
				int chapter = 0;
				System.out.print("Chapter: ");
				if (scanner.hasNextInt())
				{
					chapter = scanner.nextInt();
					scanner.nextLine();
				}
				library.playAudioBook(index, chapter);
						
			}
			
			else if (action.equalsIgnoreCase("PODTOC")) 
			{
				int index = 0;
				int season = 0;
				
				System.out.print("Podcast Number: ");
				if (scanner.hasNextInt())
				{
					index = scanner.nextInt();
				}
				System.out.print("Season: ");
				if (scanner.hasNextInt())
				{
					season = scanner.nextInt();
					scanner.nextLine();
				}
				library.printPodcastEpisodes(index, season);
						
			}
			else if (action.equalsIgnoreCase("PLAYPOD")) 
			{
				int index = 0;

				System.out.print("Podcast Number: ");
				if (scanner.hasNextInt())
				{
					index = scanner.nextInt();
					scanner.nextLine();
				}
				int season = 0;
				System.out.print("Season: ");
				if (scanner.hasNextInt())
				{
					season = scanner.nextInt();
					scanner.nextLine();
				}
				int episode = 0;
				System.out.print("Episode: ");
				if (scanner.hasNextInt())
				{
					episode = scanner.nextInt();
					scanner.nextLine();
				}
				library.playPodcast(index, season, episode);
						
			}
			else if (action.equalsIgnoreCase("PLAYALLPL")) 
			{
				String title = "";

				System.out.print("Playlist Title: ");
				if (scanner.hasNextLine())
				{
					title = scanner.nextLine();
				}
				library.playPlaylist(title);
						
			}
			else if (action.equalsIgnoreCase("PLAYPL")) 
			{
				String title = "";
				int index = 0;
        
				System.out.print("Playlist Title: ");
				if (scanner.hasNextLine())
				{
					title = scanner.nextLine();
				}
				System.out.print("Content Number: ");
				if (scanner.hasNextInt())
				{
					index = scanner.nextInt();
					scanner.nextLine();
				}
				library.playPlaylist(title, index);
						
			}
			// Delete a song from the library and any play lists it belongs to
			else if (action.equalsIgnoreCase("DELSONG")) 
			{
				int songNum = 0;

				System.out.print("Library Song #: ");
				if (scanner.hasNextInt())
				{
					songNum = scanner.nextInt();
					scanner.nextLine();
				}
				
				library.deleteSong(songNum);
						
			}
			else if (action.equalsIgnoreCase("MAKEPL")) 
			{
				String title = "";

				System.out.print("Playlist Title: ");
				if (scanner.hasNextLine())
				{
					title = scanner.nextLine();
				}
				library.makePlaylist(title);
						
			}
			else if (action.equalsIgnoreCase("PRINTPL"))	// print playlist content
			{
				String title = "";

				System.out.print("Playlist Title: ");
				if (scanner.hasNextLine())
					title = scanner.nextLine();

				library.printPlaylist(title);
					
			}
			// Add content from library (via index) to a playlist
			else if (action.equalsIgnoreCase("ADDTOPL")) 
			{
				int contentIndex = 0;
				String contentType = "";
		        String playlist = "";
		        
		        System.out.print("Playlist Title: ");
				if (scanner.hasNextLine())
					playlist = scanner.nextLine();
        
				System.out.print("Content Type [SONG, PODCAST, AUDIOBOOK]: ");
				if (scanner.hasNextLine())
					contentType = scanner.nextLine();
				
				System.out.print("Library Content #: ");
				if (scanner.hasNextInt())
				{
					contentIndex = scanner.nextInt();
					scanner.nextLine(); // consume nl
				}
				
				library.addContentToPlaylist(contentType, contentIndex, playlist);
						
			}
			// Delete content from play list
			else if (action.equalsIgnoreCase("DELFROMPL")) 
			{
				int contentIndex = 0;
				String playlist = "";

				System.out.print("Playlist Title: ");
				if (scanner.hasNextLine())
					playlist = scanner.nextLine();
				
				System.out.print("Playlist Content #: ");
				if (scanner.hasNextInt())
				{
					contentIndex = scanner.nextInt();
					scanner.nextLine(); // consume nl
				}
				library.delContentFromPlaylist(contentIndex, playlist);
						
			}
			else if (action.equalsIgnoreCase("SORTBYYEAR")) // sort songs by year
			{
				library.sortSongsByYear();
			}
			else if (action.equalsIgnoreCase("SORTBYNAME")) // sort songs by name (alphabetic)
			{
				library.sortSongsByName();
			}
			else if (action.equalsIgnoreCase("SORTBYLENGTH")) // sort songs by length
			{
				library.sortSongsByLength();
			}
			//Assignment 2 Methods
			//Search for an item with entered title in the store
			else if (action.equalsIgnoreCase("SEARCH"))
			{	
				
				String title = "";
				System.out.print("Title: ");
				
				if (scanner.hasNextLine()) {
					title = scanner.nextLine();
				}
				
				
				
				
				store.search(title);
				
				
				
				
			}
			//Search for all audiocontent from a specific artist/author
			else if (action.equalsIgnoreCase("SEARCHA"))
			{
				String artist = "";
				System.out.print("Artist: ");
				
				if (scanner.hasNextLine()) {
					artist = scanner.nextLine();
				}
				
				
				store.searchA(artist);
				
				
			}
			//Search for all songs inside a genre
			else if (action.equalsIgnoreCase("SEARCHG"))
			{
				String genre = "";
				System.out.print("Genre [POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL]: ");
				if (scanner.hasNextLine()) {
					genre = scanner.nextLine();
				}
				
				
				
				store.searchG(genre);
				
			}
			//Search for all items that partially matches a string
			else if (action.equalsIgnoreCase("SEARCHP"))
			{
				System.out.print("Keyword: ");
				String keyword = "";
				
				if (scanner.hasNextLine()) {
					keyword = scanner.nextLine();
				}
				
				
				store.searchP(keyword);
			}
			//Download all items with specified artist
			else if (action.equalsIgnoreCase("DOWNLOADA"))
			{
				System.out.print("Artist Name: ");
				String artist = "";
				if (scanner.hasNextLine()) {
					artist = scanner.nextLine();
				}
				
				
				
				ArrayList<Integer> artistContent = store.getIndicesOfArtist(artist);
				
				//Go through each item that wants to be downloaded
				for (Integer index : artistContent) {
					AudioContent content = store.getContent(index);
					try {
						library.download(content);
					} catch (AudioContentAlreadyDownloadedException e) {
						//If audio content is already downloaded print error message and check the other audio contents
						System.out.println(e.getMessage());
					}
					
				}
			}
			//Download all songs of a specific genre
			else if (action.equalsIgnoreCase("DOWNLOADG"))
			{
				System.out.print("Genre: ");
				String genre = "";
				
				if (scanner.hasNextLine()) {
					genre = scanner.nextLine();
				}
				
				//Get indices from genre map
				ArrayList<Integer> genreContent = store.getIndicesOfGenre(genre);
				for (Integer index : genreContent) {
					AudioContent content = store.getContent(index);
					try {
						library.download(content);
					} catch (AudioContentAlreadyDownloadedException e) {
						System.out.println(e.getMessage());
					}
					
				}
			}
		//Catch all other exceptions and print out their error messages
		} catch (RuntimeException e) {
			System.out.println("\n" + e.getMessage());
		}
			System.out.print("\n>");
		} 
	}
}
