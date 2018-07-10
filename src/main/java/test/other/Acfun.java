package test.other;

public class Acfun {

	public static void startDownload() {
		
		new DownloadArticle().start();
		new UpdateContent().start();
	}
}
