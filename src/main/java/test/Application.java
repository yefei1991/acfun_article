package test;

import com.blade.Blade;

import io.github.biezhi.anima.Anima;
import test.bean.Article;
import test.other.Acfun;

public class Application {

	public static void main(String[] args) {
		Anima.open("jdbc:mysql://127.0.0.1:3306/acfun", "root", "root");
		long count = Anima.select().from(Article.class).count();
		if(count==0) {
			Acfun.startDownload();
		}
		Blade.me().start(Application.class);
	}
}
