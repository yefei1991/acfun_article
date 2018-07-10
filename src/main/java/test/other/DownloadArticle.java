package test.other;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONObject;

import io.github.biezhi.anima.Anima;
import test.bean.Article;
import test.util.HttpUtil;

public class DownloadArticle {

	public static void main(String[] args) {
		DownloadArticle test=new DownloadArticle();
		test.start();
	}
	
	public void start() {
		Anima.open("jdbc:mysql://127.0.0.1:3306/acfun", "root", "root");
		ExecutorService service=Executors.newFixedThreadPool(50);
		for(int i=1;i<=200;i++) {
			service.submit(new RunTask(i));
		}
		service.shutdown();
		while(true) {
			if(service.isTerminated()) {
				System.out.println("下载文章完成");
				break;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public class RunTask implements Runnable{

		private int i;
		public RunTask(int i) {
			this.i=i;
		}
		@Override
		public void run() {
			String url="http://webapi.aixifan.com/query/article/list?pageNo="+i+"&size=50&realmIds=6,7&originalOnly=false&orderType=2&periodType=-1&filterTitleImage=true";
			String content=null;
			try {
				content = HttpUtil.content(url);
			} catch (IOException e) {
				e.printStackTrace();
			}
			JSONObject json=JSONObject.parseObject(content);
			List<Article> articles=new ArrayList<>();
			if(json.getString("code").equals("200")) {
				JSONObject data=json.getJSONObject("data");
				@SuppressWarnings("unchecked")
				List<JSONObject> jsonarray=(List<JSONObject>)data.get("articleList");
				articles.addAll(jsonarray.stream().map(r->{
					Article a=new Article();
					a.setAcid(r.getLong("id"));
					a.setCreatetime(new Date(r.getLong("contribute_time")));
					a.setDescription(r.getString("description"));
					a.setLikecount(r.getInteger("like_count"));
					a.setTitle(r.getString("title"));
					a.setUsername(r.getString("username"));
					a.setViewcount(r.getInteger("view_count"));
					return a;
				}).collect(Collectors.toList()));
			}
			Anima.saveBatch(articles);
			System.out.println(i+"保存成功");
		}
	}
}
