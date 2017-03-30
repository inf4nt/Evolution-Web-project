package evolution.model;

import java.sql.Date;

/**
 * Created by Admin on 04.03.2017.
 */
public class Album {

    private Album (AlbumBuilder albumBuilder) {
        this.id = albumBuilder.id;
        this.albumName = albumBuilder.albumName;
        this.albumAccess = albumBuilder.albumAccess;
        this.userId = albumBuilder.userId;
        this.createDate = albumBuilder.createDate;
    }

    public static class AlbumBuilder {

        public AlbumBuilder (String albumName, boolean albumAccess) {
            this.albumAccess = albumAccess;
            this.albumName = albumName;
        }

        public AlbumBuilder albumId (long albumId) {
            this.id = albumId;
            return this;
        }

        public AlbumBuilder userId (long userId) {
            this.userId = userId;
            return this;
        }

        public AlbumBuilder createDate (Date createDate) {
            this.createDate = createDate;
            return this;
        }

        public  Album build(){
            Album result = new Album(this);
            return result;
        }

        private long id;
        private String albumName;
        private boolean albumAccess;
        private long userId;
        private Date createDate;
    }

    public long getId() {
        return id;
    }

    public String getAlbumName() {
        return albumName;
    }

    public boolean isAlbumAccess() {
        return albumAccess;
    }

    public long getUserId() {
        return userId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    private long id;
    private String albumName;
    private boolean albumAccess;
    private long userId;
    private Date createDate;
}


