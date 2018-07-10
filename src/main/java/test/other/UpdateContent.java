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

public class UpdateContent {

	public void start(){
		System.out.println("进入此方法..................................");
		//Anima.open("jdbc:mysql://127.0.0.1:3306/acfun", "root", "root");
		List<Article> articles = Anima.select().from(Article.class).where("content is null").all();
		System.out.println(articles.size());
		ExecutorService service=Executors.newFixedThreadPool(50);
		articles.stream().forEach(a->{
			service.submit(()->{
				String url="http://www.acfun.cn/a/ac"+a.getAcid();
				int index=0;
				String s=null;
				while(index<5) {
					try {
						s = HttpUtil.content(url).replaceAll("<img[^>]*/>", "");
					} catch (IOException e) {
						System.out.println(url+"调用失败,重新下载");
						e.printStackTrace();
						index++;
					}
					break;
				}
				if(s==null) {
					System.out.println(url+"下载失败");
					return;
				}
				Document document = Jsoup.parse(s);
				Element element=document.getElementsByClass("article-content").first();
				if(element!=null) {
					Anima.update().from(Article.class).set(Article::getContent, element.text()).where(Article::getAcid).eq(a.getAcid()).execute();
				}
				System.out.println(a.getAcid()+"内容更新成功");
			});
		});
		service.shutdown();
		while(true) {
			if(service.isTerminated()) {
				System.out.println("更新文章完成");
				break;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
