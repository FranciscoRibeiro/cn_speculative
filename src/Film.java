import java.util.List;

class Film {
  private Double rating;
  private Integer duration;
  private String genre;
  private Integer popularity;

  public Film(Double rating, Integer duration, String genre, Integer popularity) {
    this.rating = rating;
    this.duration = duration;
    this.genre = genre;
    this.popularity= popularity;
  }

  public Double getRating() {
    return this.rating;
  }

  public Integer getDuration() {
    return this.duration;
  }

  public String getGenre() {
    return this.genre;
  }

  public Integer getPopularity() {
    return this.popularity;
  }

  public void setRating(Double rating) {
    this.rating = rating;
  }

  public void setDuration(Integer duration) {
    this.duration = duration;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  public void setPopularity(Integer popularity) {
    this.popularity = popularity;
  }
}
