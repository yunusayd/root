package imageArranger;

import java.util.*;
import java.awt.geom.Path2D;
import java.io.*;
import org.apache.commons.io.*;



import com.sun.tools.javac.util.Paths;

public class ImageCatalog {

	private String folder;
	private List<String> catalogList;
	public ImageCatalog(String pfolder)
	{
		this.folder = pfolder;
		catalogList = new ArrayList<String>();
		
	}
	
	private void walkOnFiles(String pfolder)
	{
		
	}
	
	public void walkOnFiles()
	{
	}
}
