package ge.edu.freeuni.sdp.xo.game;

public class UpdaterFactory {
	private static final Updater u = new ArchiveUpdater();

	public static Updater getUpdater() {
		return u;
	}

}
