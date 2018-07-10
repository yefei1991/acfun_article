package test.util;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpUtil {
	
	public static String content(String url) throws IOException{
		// 设置代理IP、端口、协议（请分别替换）
		//HttpHost proxy = new HttpHost("proxysz.aac.com", 80, "http");
		HttpHost proxy = new HttpHost("10.2.249.5", 1080, "http");
		// 把代理设置到请求配置
		RequestConfig defaultRequestConfig = RequestConfig.custom().setProxy(proxy).build();
		// 实例化CloseableHttpClient对象
		CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build();
		// 访问目标地址
		HttpGet httpGet = new HttpGet(url);
		// 请求返回
		String s=null;
		CloseableHttpResponse response = httpclient.execute(httpGet);
		s=EntityUtils.toString(response.getEntity(),"utf-8");
		return s;
	}
}
