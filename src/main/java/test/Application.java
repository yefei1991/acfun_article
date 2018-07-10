package test;

import com.blade.Blade;

import io.github.biezhi.anima.Anima;

public class Application {

	public static void main(String[] args) {
		Anima.open("jdbc:mysql://127.0.0.1:3306/acfun", "root", "root");
		Blade.me().start(Application.class);
	}
}
