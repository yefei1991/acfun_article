package test.bean;

import java.util.Date;

import io.github.biezhi.anima.Model;
import lombok.Getter;
import lombok.Setter;

public class Article extends Model {

	@Getter
	@Setter
	private Date createtime;
	
	@Getter
	@Setter
	private String description;
	
	@Getter
	@Setter
	private Long acid;
	
	@Getter
	@Setter
	private int likecount;
	
	@Getter
	@Setter
	private String title;
	
	@Getter
	@Setter
	private String username;
	
	@Getter
	@Setter
	private int viewcount;
	
	@Getter
	@Setter
	private String content;
	
}
