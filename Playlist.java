import java.util.Arrays;

class Playlist {
    private String[] songs;
    private int count;

    Playlist(int size) {
        songs = new String[size];
        count = 0;
    }

    void addSong(String song) {
        if (count < songs.length) {
            songs[count] = song;
            count++;
        } else {
            System.out.println("Playlist is full");
        }
    }

    String[] getSongs() {
        return Arrays.copyOf(songs, count);
    }

    int getSongCount() {
        return count;
    }

    public static void main(String[] args) {
        Playlist p = new Playlist(10);

        p.addSong("Song A");
        p.addSong("Song B");

        String[] copy = p.getSongs();

        System.out.println("Songs before modification:");
        for (String song : p.getSongs()) {
            System.out.println(song);
        }

        copy[0] = "Hacked";

        System.out.println("Songs after modifying copy:");
        for (String song : p.getSongs()) {
            System.out.println(song);
        }

        System.out.println("Song count: " + p.getSongCount());
    }
}