package Pattern.Composite;

public class DiscJockey {
    SongComponent songList;

    public DiscJockey(SongComponent newSongList) {
        songList = newSongList;
    }

    public void getSongList() {
        songList.displaySongInformation();
    }
}
