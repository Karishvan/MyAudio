//Karishvan Ragunathan
//Student ID: 501166081
import java.util.ArrayList;
/*
 * Simple class to model podcast or other content seasons
 */
public class Season
{
	public ArrayList<String> episodeFiles;
	public ArrayList<String> episodeTitles;
	public ArrayList<Integer> episodeLengths;
	
	public Season()
	{
		episodeFiles = new ArrayList<String>();
		episodeTitles = new ArrayList<String>();
		episodeLengths = new ArrayList<Integer>();
	}
	
	@Override
	public String toString() {
		return "" + episodeFiles + episodeTitles + episodeLengths;
	}
}
