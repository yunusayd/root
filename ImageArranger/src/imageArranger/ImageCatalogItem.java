package imageArranger;

import java.util.*;

public class ImageCatalogItem {
	public List<String> files;
	public int year;
	public int month;
	public String key;
	public String place;
	
	public ImageCatalogItem(int year, int month, String place)
	{
		
		this.year = year;
		this.month = month;
		this.place  = place;
		this.key = String.format("%d-%s-%s", year, MonthNames.getMonthName(month), place);
		files = new ArrayList<String>();
	}
	
	public void addFile(String fileName)
	{
		if(!files.contains(fileName))
		{
			files.add(fileName);
		}
	}
}
