package net.zhuoweizhang.sproutcraftskin;

import java.lang.reflect.*;

import org.spoutcraft.launcher.api.skin.JavaSkin;
import org.spoutcraft.launcher.api.skin.gui.LoginFrame;
import org.spoutcraft.launcher.skin.LegacyLoginFrame;


public class SproutcraftSkin extends JavaSkin {
	private LegacyLoginFrame loginFrame;
	private static boolean hasChangedImages = false;

	public SproutcraftSkin() {
		if (!hasChangedImages) {
			try {
				changeLauncherImages();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		loginFrame = new LegacyLoginFrame(this);
		loginFrame.setTitle("Sproutcraft launcher");
	}

	public void onDisable() {
	}

	public void onEnable() {
	}

	public LoginFrame getLoginFrame() {
		return loginFrame;
	}

	public static void changeLauncherImages() throws Exception {
		Class<LegacyLoginFrame> frameClass = LegacyLoginFrame.class;
		Field logo = frameClass.getDeclaredField("spoutcraftLogo");
		logo.setAccessible(true);
		Field modifiersField = Field.class.getDeclaredField("modifiers");
		modifiersField.setAccessible(true);
		modifiersField.setInt(logo, logo.getModifiers() & ~Modifier.FINAL);
		logo.set(null, SproutcraftSkin.class.getResource("/sproutcraft_logo.png"));
		System.out.println("Changed launcher images");
		hasChangedImages = true;
	}

	static {

		try {
			changeLauncherImages();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

