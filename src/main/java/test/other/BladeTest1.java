package test.other;

import io.github.biezhi.anima.Anima;

public class BladeTest1 {

	static {
		Anima.open("jdbc:mysql://127.0.0.1:3306/acfun", "root", "root");
	}
	
	public static void main(String[] args) {
		String s;
		for(int i=1;i<200;i++) {
			s="http://webapi.aixifan.com/query/article/list?pageNo="+i+"&size=50&realmIds=6,7&originalOnly=false&orderType=2&periodType=-1&filterTitleImage=true";
			System.out.println(s);
		}
	}

}
