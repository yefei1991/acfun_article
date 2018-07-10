package test.other;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import io.github.biezhi.anima.Anima;
import test.bean.Article;
import test.util.HttpUtil;

public class UpdateTest {

	public static void main(String[] args){
		Anima.open("jdbc:mysql://127.0.0.1:3306/acfun", "root", "root");
		List<Article> articles = Anima.select().from(Article.class).where("content is null").all();
		System.out.println(articles.size());
		ExecutorService service=Executors.newFixedThreadPool(30);
		articles.stream().forEach(a->{
			service.submit(()->{
				String url="http://www.acfun.cn/a/ac"+a.getAcid();
				String s=null;
				try {
					s = HttpUtil.content(url).replaceAll("<img[^>]*/>", "");
				} catch (IOException e) {
					System.out.println(url+"调用失败");
					e.printStackTrace();
					return;
				}
				Document document = Jsoup.parse(s);
				Element element=document.getElementsByClass("article-content").first();
				//System.out.println(element.text());
				System.out.println(element.text().getBytes().length);
				Anima.update().from(Article.class).set(Article::getContent, element.text()).where(Article::getAcid).eq(a.getAcid()).execute();
			});
		});

	}

}
