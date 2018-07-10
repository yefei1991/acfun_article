package test.controller;

import com.blade.mvc.annotation.GetRoute;
import com.blade.mvc.annotation.Path;
import com.blade.mvc.annotation.PathParam;
import com.blade.mvc.http.Response;
import com.blade.mvc.ui.ModelAndView;

import io.github.biezhi.anima.Anima;
import io.github.biezhi.anima.page.Page;
import test.bean.Article;

@Path
public class IndexController {
	
    @GetRoute("/index/:page")
    public ModelAndView index(@PathParam Integer page,Response response){
    	Page<Article> articlePage = Anima.select().from(Article.class).order("createtime desc").page(page, 10);
    	//articlePage.getRows().stream().forEach(r->{System.out.println(r.getTitle());});
    	ModelAndView mav=new ModelAndView("index.html");
    	mav.add("list", articlePage.getRows());
    	mav.add("total", articlePage.getTotalPages());
    	mav.add("page", page);
    	return mav;
    }
}
