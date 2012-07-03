package net.zhuoweizhang.sproutcraftskin;

import java.awt.Image;
import java.awt.Toolkit;

import java.lang.reflect.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.spoutcraft.launcher.SplashScreen;

public class Main {
	public static void main(String[] args) {
		try {
			nerfSplashScreen("/splash.png");
		} catch (Throwable t) {
		}
		List<String> oldList = Arrays.asList(args);
		List<String> newList = new ArrayList<String>(args.length + 3);
		newList.addAll(oldList);
		newList.add("--skin_class");
		newList.add("net.zhuoweizhang.sproutcraftskin.SproutcraftSkin");
		newList.add("-relaunched");
		System.out.println("main");
		org.spoutcraft.launcher.Main.main(newList.toArray(args));
	}

	public static void nerfSplashScreen(String newScreen) throws Exception {
		Field splashImg = SplashScreen.class.getDeclaredField("bg");
		splashImg.setAccessible(true);
		splashImg.set(null, Toolkit.getDefaultToolkit().getImage(Main.class.getResource(newScreen)));
	}
}
