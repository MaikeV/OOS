package Pattern.Composite;

public class SongListGenerator {
    public static void main(String[] args) {
        SongComponent rockMusic = new SongGroup("Rock", "");
        SongComponent heavyMetalMusic = new SongGroup("Heavy Metal", "Very Cool Stuff");
        SongComponent hardRockMusic = new SongGroup("Hard Rock", "");
        SongComponent everySong = new SongGroup("Song List", "Every Song Available");

        everySong.add(rockMusic);
        rockMusic.add(new Song("Lonesome Rider", "Volbeat", 2013));
        rockMusic.add(new Song("Over and Over", "Three Days Grace", 2006));

        rockMusic.add(hardRockMusic);

        hardRockMusic.add(new Song("Hard Rock Halleluja", "Lordi", 2006));
        hardRockMusic.add(new Song("Poison", "Alice Cooper", 1972));

        everySong.add(heavyMetalMusic);

        heavyMetalMusic.add(new Song("Attack of the Dead Men", "Sabaton", 2019));
        heavyMetalMusic.add(new Song("Stargazer", "Rainbow", 1976));

        DiscJockey crazyLarry = new DiscJockey(everySong);

        crazyLarry.getSongList();

    }
}
