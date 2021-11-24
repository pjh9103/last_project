package kr.co.tjoeun.movie.dto;

public class MovieRequestDto {
    private String key;
    private String category;
    private String withGenre;

    public MovieRequestDto(String key, String category, String withGenre) {
        this.key = key;
        this.category = category;
        this.withGenre = withGenre;
    }

    public MovieRequestDto(String key, String category) {
        this.key = key;
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public String getWithGenre() {
        return withGenre;
    }

    public String getKey() {
        return key;
    }
}
