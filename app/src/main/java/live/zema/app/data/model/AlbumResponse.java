package live.zema.app.data.model;

import com.google.gson.annotations.SerializedName;

import live.zema.app.data.entity.Artist;
import live.zema.app.data.entity.Music;
import live.zema.app.signature.SimpleItem;

import java.util.ArrayList;
import java.util.List;

public class AlbumResponse extends SimpleItem {
      @SerializedName("album_name")
      private String album_name;
      @SerializedName("genres_name")
      private String genres;
      @SerializedName("released_date")
      private String releasedDate;
      @SerializedName("album_poster")
      private String poster;
      @SerializedName("artists")
      private List<Artist> artists;
      @SerializedName("song_details")
      private ArrayList<Music> tracks;



      public AlbumResponse(ArrayList<Music> tracks, String releasedDate)
      {     this.tracks = tracks;
            this.releasedDate = releasedDate;
      }
      public AlbumResponse(String album_name)
      {
            this.album_name=album_name;
      }
      public String getAlbum_name() {
            return album_name;
      }
      public String getGenres() {
            return genres;
      }
      public String getReleasedDate() {
            return releasedDate;
      }
      public String getPoster() {
            return poster;
      }
      public List<Artist> getArtists() {
            return artists;
      }
      public ArrayList<Music> getTracks() {
            return tracks;
      }

}
