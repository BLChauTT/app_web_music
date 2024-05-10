package com.demo.model;

import java.sql.Date;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "music", schema = "app_web_musics_database", catalog = "")
public class MusicEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "authorId")
    private int authorId;
    @Basic
    @Column(name = "image")
    private String image;
    @Basic
    @Column(name = "file")
    private String file;
    @Basic
    @Column(name = "album")
    private String album;
    @Basic
    @Column(name = "postingDate")
    private Date postingDate;
    @Basic
    @Column(name = "modifyDate")
    private Date modifyDate;
    @Basic
    @Column(name = "time")
    private String time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Date getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(Date postingDate) {
        this.postingDate = postingDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MusicEntity that = (MusicEntity) o;

        if ((id != that.id) || (authorId != that.authorId) || (name != null ? !name.equals(that.name) : that.name != null) || (image != null ? !image.equals(that.image) : that.image != null)) return false;
        if ((file != null ? !file.equals(that.file) : that.file != null) || (album != null ? !album.equals(that.album) : that.album != null) || (postingDate != null ? !postingDate.equals(that.postingDate) : that.postingDate != null)
				|| (modifyDate != null ? !modifyDate.equals(that.modifyDate) : that.modifyDate != null)) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + authorId;
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (file != null ? file.hashCode() : 0);
        result = 31 * result + (album != null ? album.hashCode() : 0);
        result = 31 * result + (postingDate != null ? postingDate.hashCode() : 0);
        result = 31 * result + (modifyDate != null ? modifyDate.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }
}
