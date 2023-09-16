//Karishvan Ragunathan
//Student ID: 501166081
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

// Simulation of audio content in an online store
// The songs, podcasts, audiobooks listed here can be "downloaded" to your library

public class AudioContentStore
{		
		private Map<String, Integer> titleToIndex;
		private Map<String, ArrayList<Integer>> artistToIndex;
		private Map<String, ArrayList<Integer>> genreToIndex;
		private Map<Integer, String> indexToDetails;
		private File storeOptions;
		private ArrayList<AudioContent> contents; 
		
		public AudioContentStore()
		{
			contents = new ArrayList<AudioContent>();
			titleToIndex = new HashMap<>();
			artistToIndex = new HashMap<>();
			genreToIndex = new HashMap<>();
			//Treemap so keyset is ordered
			indexToDetails = new TreeMap<>();
			
			storeOptions = new File("store.txt");
			try {
				contents = createContents();
				//System.out.println(contents);
			} catch (IOException e) {
				System.out.println(e.getMessage());
				System.exit(1);
			}
			
			
			//Populating Maps
			for (int i = 0; i < contents.size(); i++) {
				int index = i+1;
				AudioContent currentContent = contents.get(i);
				titleToIndex.put(currentContent.getTitle(), index);
				String artist = "";
				String genre = null;
				if (currentContent.getType().equals(Song.TYPENAME)) {
					Song songContent = (Song) currentContent;
					artist = songContent.getArtist();
					genre = songContent.getGenre().toString();
					populateMap(genreToIndex, genre, "genre" ,index);
				} else if (currentContent.getType().equals(AudioBook.TYPENAME)) {
					AudioBook bookContent = (AudioBook) currentContent;
					artist = bookContent.getAuthor();
				} else {
					Podcast podcastContent = (Podcast) currentContent;
					artist = podcastContent.getHost();
				}
				//Add item to artist map
				populateMap(artistToIndex, artist, "artist", index);
				
				//Add item to general details map
				indexToDetails.put(index, currentContent.getDetails());
				
//				if (artistToIndex.containsKey(artist)) {
//					
//				} else {
//					
//				}
				
			}
			//System.out.println(titleToIndex);
			
		}
		
		
		public AudioContent getContent(int index)
		{
			if (index < 1 || index > contents.size())
			{
				//return null;
				throw new ContentIndexOutOfRangeException("AudioContent");
			}
			return contents.get(index-1);
		}
		
		public ArrayList<AudioContent> getContent(int startIndex, int endIndex){
			ArrayList<AudioContent> result = new ArrayList<>();
			for (int i = startIndex; i <= endIndex; i++) {
				result.add(getContent(i));
			}
			return result;
		}
		
		public void listAll()
		{
			for (int i = 0; i < contents.size(); i++)
			{
				int index = i + 1;
				System.out.print(index + ". ");
				contents.get(i).printInfo();
				System.out.println();
			}
		}
		
		//Assignment2
		//Helper method to create chapter titles
		private ArrayList<String> makeChapterTitles(Scanner scanner, int chapters){
			ArrayList<String> titles = new ArrayList<>();
			
			for (int i = 0; i < chapters; i++) {
				titles.add(scanner.nextLine());
			}
			return titles;
			
		}
		
		//Helper method to contain information of each chapter
		private ArrayList<String> makeChapters(Scanner scanner, int numOfChapters){
			ArrayList<String> chapters = new ArrayList<>();
			for (int i = 0; i < numOfChapters; i++) {
				String chapter = "";
				int numOfLines = scanner.nextInt();
				scanner.nextLine(); //consume nl
				for (int j = 0; j < numOfLines; j++) {
					chapter += scanner.nextLine() + "\n";
				}
				chapters.add(chapter);
			}
			return chapters;
			
			
		}
		
		//Read through file and return arraylist of audiocontent objects
		private ArrayList<AudioContent> createContents () throws IOException{
			Scanner scanner = new Scanner(storeOptions);
			ArrayList<AudioContent> result = new ArrayList<>();
			while (scanner.hasNextLine()) {
				AudioContent ac = null;
				
				String type = scanner.nextLine();
				//System.out.println(type);
				String id = scanner.nextLine();
				String title = scanner.nextLine();
				int year = Integer.parseInt(scanner.nextLine()); //Consume nl
				int length = Integer.parseInt(scanner.nextLine()); //Consume nl
				String artist = scanner.nextLine();
				if (type.equals(Song.TYPENAME)) {
					String composer = scanner.nextLine();
					String genreString = scanner.nextLine();
					Song.Genre genre = Song.Genre.valueOf(genreString);
					
					
					int numOfLines = scanner.nextInt();
					scanner.nextLine(); //Consumer nl
					String file = "";
					for (int i = 0; i < numOfLines; i++) {
						String line = scanner.nextLine();
						file += line + "\n";
					}
					//System.out.println(file);
					ac = new Song(title, year, id, type, file, length, artist, composer,genre,file);
					
					
				} else if (type.equals(AudioBook.TYPENAME)) {
					String narrator = scanner.nextLine();
					int numOfChapters = scanner.nextInt();
					scanner.nextLine(); //Consumer nl
					ArrayList<String> titles = makeChapterTitles(scanner, numOfChapters);
					ArrayList<String> chapters = makeChapters(scanner, numOfChapters);
					ac = new AudioBook(title, year, id, type, "", length, artist, narrator, titles, chapters);
					//System.out.println(chapters);
					
					
				}
				result.add(ac);
				System.out.println("Loading " + ac.getType());
				
			}
			scanner.close();
			return result;
		}
		

		//Assignment 2
		//Accessing map of titles to get the index of that item in the store
		public int indexOf(String title) {
			if (!titleToIndex.containsKey(title))
				throw new AudioContentNotFoundException(title);
			return titleToIndex.get(title);
		}
		//Accessing map to get indices of specified artist
		public ArrayList<Integer> getIndicesOfArtist(String artist){
			if (!artistToIndex.containsKey(artist))
				throw new AudioContentNotFoundException(artist);
			return artistToIndex.get(artist);
		}
		//Accessing map to get indices of specified genre
		public ArrayList<Integer> getIndicesOfGenre(String genre){
			if (!genreToIndex.containsKey(genre))
				throw new AudioContentNotFoundException(genre);
			return genreToIndex.get(genre);
		}
		//Searches for audiocontent with specified title
		public void search(String title) {
			int index = indexOf(title);
			AudioContent contentSelected = getContent(index);
			System.out.print(index + ". ");
			contentSelected.printInfo();
		}
		//Searches for audiocontent with specified artist/author
		public void searchA(String artist) {
			ArrayList<Integer> indices = getIndicesOfArtist(artist);
			listAll(indices);
		}
		
		//Searches for songs with specified genre
		public void searchG(String genre) {
			ArrayList<Integer> indices = getIndicesOfGenre(genre);
			listAll(indices);
		}
		
		//Searches for a partial string and prints out all audiocontent that contains the string
		public void searchP(String keyword) {
			ArrayList<Integer> allTheContent = new ArrayList<>();
			for (Integer key : indexToDetails.keySet()) {
				String value = indexToDetails.get(key);
				if (value.contains(keyword))
					allTheContent.add(key);
			}

			if (allTheContent.isEmpty())
				throw new AudioContentNotFoundException(keyword);
			listAll(allTheContent);
			
		}
		
		//Change maps to include data of genre/artist
		private void populateMap(Map<String, ArrayList<Integer>> map, String item, String type, int index) {
			ArrayList<Integer> indices;
			
			try {
				if (type.equals("genre") )
					indices = getIndicesOfGenre(item);
				else indices = getIndicesOfArtist(item);
				//Add the extra index to the arraylist
				indices.add(index);
			//If the item doesn't exist inside the map, add the item to the map
			} catch (AudioContentNotFoundException e) {
				indices = new ArrayList<>();
				//add the index to the arraylist
				indices.add(index);
				map.put(item, indices);
			}
		}
		//Different version of listAll that includes indices to print out
		public void listAll(ArrayList<Integer> indices) {
			for (Integer index : indices) {
				System.out.print(index + ". ");
				contents.get(index-1).printInfo();
				System.out.println();
			}
		}
		
}
