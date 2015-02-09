package imageArranger;

import java.util.*;
import java.io.*;

public class ImageCatalog {

	private String folder;
	private List<ImageCatalogItem> catalogList;

	public ImageCatalog(String pfolder) {
		this.folder = pfolder;
		catalogList = new ArrayList<ImageCatalogItem>();

	}

	private void walkOnFiles(String pfolder) {
		File fld = new File(pfolder);
		File[] listOfFiles = fld.listFiles();

		for (File file : listOfFiles) {
			if (file.isFile()) {
				addToList(file);
			} else if (file.isDirectory()) {
				String absPath = file.getAbsolutePath();
				if (!absPath.equals(""))
					walkOnFiles(absPath);
			}
		}
	}

	void addToList(File file) {
		long lastModified = file.lastModified();
		Date date = new Date(lastModified);
		int year = date.getYear();
		int month = date.getMonth();
		// String monthName = MonthNames.getMonthName(month);

		ImageCatalogItem item = findCatalogItem(year, month, "");

	}

	ImageCatalogItem findCatalogItem(int year, int month, String place) {
		ImageCatalogItem result = null;
		for (ImageCatalogItem item : catalogList) {
			if (item.year == year && item.month == month
					&& item.place.equals(place)) {
				result = item;
				break;
			}
		}
		if (result == null) {
			result = new ImageCatalogItem(year, month, place);
			catalogList.add(result);
		}

		return result;
	}

	public void Process() {
		walkOnFiles(folder);

	}

}
