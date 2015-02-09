package imageArranger;

import java.util.*;
import java.io.*;

import org.apache.commons.io.FilenameUtils;

public class ImageCatalog extends Thread {

	public int fileCount = 0;
	public List<ImageCatalogItem> catalogList;

	private String folder;
	private ImageCatalogCallBack callBack;

	public ImageCatalog(String pfolder, ImageCatalogCallBack callBack) {
		this.folder = pfolder;
		catalogList = new ArrayList<ImageCatalogItem>();
		this.callBack = callBack;
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
		String fileName = file.getName();
		String ext = FilenameUtils.getExtension(fileName);
		if (ext.equalsIgnoreCase("jpg") || ext.equalsIgnoreCase("png")
				|| ext.equalsIgnoreCase("mov") || ext.equalsIgnoreCase("mp4")
				|| ext.equalsIgnoreCase("jpeg")) {
			long lastModified = file.lastModified();
			Date date = new Date(lastModified);
			int year = date.getYear();
			int month = date.getMonth();
			// String monthName = MonthNames.getMonthName(month);

			ImageCatalogItem item = findCatalogItem(year, month, "");
			item.files.add(fileName);
			fileCount++;
			if (callBack != null)
				callBack.progress(0, fileCount, file);
		}
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

	public void run() {
		walkOnFiles(folder);
		if (callBack != null)
			callBack.finished();
	}

}
