package live.zema.app.data.entity;

import com.google.gson.annotations.SerializedName;

public class Artist
{
      @SerializedName("r_name")
      private String name;
      private String gender;

      public String getName() {
            return name;
      }
      public String getGender() {
            return gender;
      }
}
