package fr.univtours.examplanner.utils;

import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Json {

	/**
	 * Lis et parse un fichier JSON<br/>
	 * En cas d'erreur, retourne un objet JSON vide
	 *
	 * @param file le chemin du fichier JSON dans les ressources
	 * @return un objet JSON
	 */
	public static @NotNull JSONObject parse(@NotNull String file) {
		JSONParser parser = new JSONParser();
		try {
			return (JSONObject) parser.parse(new FileReader(Ressource.resolve(file).getPath()));
		} catch (IOException | ParseException e) {
			try {
				System.err.println("Error while parsing JSON file: " + "src/main/resources/fr/univtours/examplanner/".concat(file));
				System.out.println("Empty Json object will be returned instead.");
				return (JSONObject) parser.parse("{}");
			} catch (ParseException ex) {
				throw new RuntimeException(ex);
			}
		}
	}

	/**
	 * Convertit un objet JSON en HashMap
	 *
	 * @param jsonObject l'objet JSON à convertir
	 * @return une HashMap
	 */
	public static @NotNull HashMap<String, String> JsonObjectToHashMap(@NotNull JSONObject jsonObject) {
		HashMap<String, String> hashMap = new HashMap<>();
		for (Object key : jsonObject.keySet()) {
			hashMap.put((String) key, (String) jsonObject.get(key));
		}
		return hashMap;
	}

}
