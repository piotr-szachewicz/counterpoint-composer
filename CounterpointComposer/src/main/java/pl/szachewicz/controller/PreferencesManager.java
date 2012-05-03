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

	private static String defaultPreferencesFileName = "preferences.xml";

	protected static XStream createXStream() {
		XStream xstream = new XStream(new DomDriver());
		xstream.processAnnotations(new Class[] {
			Preferences.class, Interval.class, NoteJumpPunishment.class, ParallelMovementPunishment.class
		});
		return xstream;
	}

	public static void savePreferencesToFile(Preferences preferences, File file) {
		String xml = createXStream().toXML(preferences);

		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(file));
			writer.write(xml);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void savePreferences(Preferences preferences) {
		savePreferencesToFile(preferences, new File(defaultPreferencesFileName));
	}

	public static Preferences loadPreferencesFromFile(File file) {
		Preferences preferences = null;

		FileInputStream fin;
		try {
			fin = new FileInputStream (file);
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

	public static Preferences loadPreferences() {
		return loadPreferencesFromFile(new File(defaultPreferencesFileName));
	}
}
