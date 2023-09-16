//Karishvan Ragunathan
//Student ID: 501166081
import java.util.ArrayList;
import java.util.Set;

/*
 * An AudioBook is a type of AudioContent.
 * It is a recording made available on the internet of a book being read aloud by a narrator
 * 
 */
public class AudioBook extends AudioContent
{
	public static final String TYPENAME =	"AUDIOBOOK";
	
	private String author; 
	private String narrator;
	private ArrayList<String> chapterTitles;
	private ArrayList<String> chapters;
	private int currentChapter = 0;

	public AudioBook(String title, int year, String id, String type, String audioFile, int length,
									String author, String narrator, ArrayList<String> chapterTitles, ArrayList<String> chapters)
	{
		super(title, year, id, type, audioFile, length);
		this.author = author;
		this.narrator = narrator;
		this.chapterTitles = chapterTitles;
		this.chapters = chapters;
	}
	
	public String getType()
	{
		return TYPENAME;
	}

	public void printInfo()
	{
		super.printInfo();
		System.out.println("Author: " + author + " Narrated by: " + narrator);
	}
	
	public void play()
	{
		int ch = currentChapter + 1;
		setAudioFile("Chapter " + ch + ". " + chapterTitles.get(currentChapter) + "\n" + chapters.get(currentChapter));
		super.play();
	}
	
	// Print the Table of Contents of a Book
	public void printTOC()
	{
		for (int i = 0; i < chapterTitles.size(); i++)
		{
			String title = chapterTitles.get(i);
			int ch = i + 1;
			System.out.println("Chapter " + ch + ". " + title + "\n");
		}
	}

	public void selectChapter(int chapter)
	{
		if (chapter >= 1 && chapter <= chapters.size())
		{
			currentChapter = chapter - 1;
		}
	}
	
	//Two AudioBooks are equal if their AudioContent information is equal and both the author and narrators are equal
	public boolean equals(Object other)
	{
		AudioBook book = (AudioBook) other;
		return super.equals(other) && author.equals(book.author) && narrator.equals(book.narrator); 
	}
	
	public int getNumberOfChapters()
	{
		return chapters.size();
	}

	public String getAuthor()
	{
		return author;
	}

	public void setAuthor(String author)
	{
		this.author = author;
	}

	public String getNarrator()
	{
		return narrator;
	}

	public void setNarrator(String narrator)
	{
		this.narrator = narrator;
	}

	public ArrayList<String> getChapterTitles()
	{
		return chapterTitles;
	}

	public void setChapterTitles(ArrayList<String> chapterTitles)
	{
		this.chapterTitles = chapterTitles;
	}

	public ArrayList<String> getChapters()
	{
		return chapters;
	}

	public void setChapters(ArrayList<String> chapters)
	{
		this.chapters = chapters;
	}
	
	@Override
	public String getDetails() {
		String text = super.getDetails();
		text += (getAuthor()); 
		text += (getNarrator());
		text += (getChapterTitles().toString()); 
		text += (getChapters().toString());
		return text;
	}

}
