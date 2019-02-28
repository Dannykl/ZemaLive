package live.zema.app.data.entity;

import com.google.gson.annotations.SerializedName;
import live.zema.app.signature.SimpleItem;

public class Music extends SimpleItem
{
      @SerializedName("song_name")
      private String song_name;
      @SerializedName("song_location")
      private String song_location;
      @SerializedName("song_duration")
      private long song_duration;

      private String song_index;


      public Music(String song_index, String song_name,String song_location,long song_duration)
      {     this.song_index=song_index;
            this.song_name = song_name;
            this.song_location=song_location;
            this.song_duration=song_duration;
      }
      public String getSongIndex() {
            return song_index;
      }
      public String getSongName() {
            return song_name;
      }
      public String getSongLocation() {
            return song_location;
      }
      public long getSong_duration() {
            return song_duration;
      }

      @Override
      public int getType() {
            return TYPE_ITEM;
      }

}










