package imageArranger;

import java.io.File;

public interface ImageCatalogCallBack {
	public void progress(int fileIx, int totalFile, File file);
	public void finished();

}
