import java.util.List;

class Film {
  private Float rating;
  private Integer duration;
  private String genre;
  private Integer popularity;

  public Film(Float rating, Integer duration, String genre, Integer popularity) {
    this.rating = rating;
    this.duration = duration;
    this.genre = genre;
    this.popularity= popularity;
  }

  public Float getRating() {
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

  public void setRating(Float rating) {
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
