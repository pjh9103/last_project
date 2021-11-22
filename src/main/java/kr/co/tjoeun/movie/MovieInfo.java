package kr.co.tjoeun.movie;

public class MovieInfo {

    private String title;
    private String posterImg;

    public MovieInfo(String title, String posterImg) {
        this.title = title;
        this.posterImg = posterImg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterImg() {
        return posterImg;
    }

    public void setPosterImg(String posterImg) {
        this.posterImg = posterImg;
    }

}
