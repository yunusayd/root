package imageArranger;

import java.util.*;

public class ImageCatalog {

	private String folder;
	private List<String> catalogList;
	public ImageCatalog(String pfolder)
	{
		this.folder = pfolder;
		catalogList = new ArrayList<String>();
	}
}
