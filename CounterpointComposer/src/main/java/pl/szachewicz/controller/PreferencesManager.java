package pl.szachewicz.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import pl.szachewicz.model.Interval;
import pl.szachewicz.model.preferences.NoteJumpPunishment;
import pl.szachewicz.model.preferences.ParallelMovementPunishment;
import pl.szachewicz.model.preferences.Preferences;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class PreferencesManager {

	private static String preferencesFileName = "preferences.xml";

	protected static XStream createXStream() {
		XStream xstream = new XStream(new DomDriver());
		xstream.processAnnotations(new Class[] {
			Preferences.class, Interval.class, NoteJumpPunishment.class, ParallelMovementPunishment.class
		});
		return xstream;
	}

	public static void savePreferences(Preferences preferences) {
		String xml = createXStream().toXML(preferences);
		System.out.println(xml);

		File file = new File(preferencesFileName);
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(file));
			writer.write(xml);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Preferences loadPreferences() {
		Preferences preferences = null;
		File file = new File(preferencesFileName);

		FileInputStream fin;
		try {
			fin = new FileInputStream (preferencesFileName);
			preferences = (Preferences) createXStream().fromXML(fin);
			fin.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (preferences == null) {
			preferences = new Preferences();
			preferences.setDefaults();
		}

		return preferences;
	}
}
