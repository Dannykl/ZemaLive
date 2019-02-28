package live.zema.app.data.entity;

import com.google.gson.annotations.SerializedName;

import live.zema.app.data.model.AlbumResponse;
import live.zema.app.signature.SimpleItem;

import java.util.List;

public class AlbumList extends SimpleItem {

    private String catagoryName;

    @SerializedName("albums")
    private List<AlbumResponse> listOfAlbums;

    public AlbumList() {
    }

    public AlbumList(String catagoryName, List<AlbumResponse> listOfAlbums) {
        this.catagoryName = catagoryName;
        this.listOfAlbums = listOfAlbums;
    }

    public String getCatagoryName() {
        return catagoryName;
    }

    public void setCatagoryName(String catagoryName) {
        this.catagoryName = catagoryName;
    }

    public List<AlbumResponse> getListOfAlbums() {
        return listOfAlbums;
    }

    public void setListOfAlbums(List<AlbumResponse> listOfAlbums) {
        this.listOfAlbums = listOfAlbums;
    }
}


//      public String getComment() {
//            return comment;
//      }

//      public void setComment(String comment) {
//            this.comment = comment;
//      }

//      public int getScore() {
//            return score;
//      }

//      public void setScore(int score) {
//            this.score = score;
//      }

