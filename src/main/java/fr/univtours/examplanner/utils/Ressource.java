package fr.univtours.examplanner.utils;

import fr.univtours.examplanner.App;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Ressource {

    private Ressource() {super();}

    /**
     * Résout un chemin de ressource en une {@link URL}
     *
     * @param ressource Chemin de la ressource
     * @param cache     Si la ressource doit être mise en cache
     * @return L'URL de la ressource
     */
    public static @NotNull URL resolve( @NotNull String ressource, boolean cache ) {
        // Todo(@remi): optimize this with cache
		URL url = App.class.getResource(ressource);
		if ( null == url ) {
			// Erreur qui doit être résolue avant de pouvoir continuer
			throw new IllegalArgumentException("File not found: ".concat(ressource));
		}
		return url;
	}

	/**
	 * Résout un chemin de ressource en une {@link URL}
	 *
	 * @param ressource Chemin de la ressource
	 * @return L'URL de la ressource
	 * @see #resolve(String, boolean)
	 */
	public static @NotNull URL resolve(@NotNull String ressource) {
		return resolve(ressource, true);
	}

	/**
	 * Résout et charge un chemin de ressource en un {@link InputStream}
	 *
	 * @param ressource Chemin de la ressource
	 * @return L'{@code InputStream} de la ressource
	 */
	public static @NotNull InputStream getStream(@NotNull String ressource, boolean cache) {
		// Todo(@remi): optimize this with cache
		try {
			return resolve(ressource).openStream();
		} catch (IOException e) {
			// Devrait être compilé, car le fichier doit exister
			throw new RuntimeException("Error while opening stream for ".concat(ressource));
		}
	}

	/**
	 * Résout et charge un chemin de ressource en un {@link InputStream}
	 *
	 * @param ressource Chemin de la ressource
	 * @return L'{@code InputStream} de la ressource
	 * @see #getStream(String, boolean)
	 */
	public static @NotNull InputStream getStream(@NotNull String ressource) {
		return getStream(ressource, false);
	}

}
