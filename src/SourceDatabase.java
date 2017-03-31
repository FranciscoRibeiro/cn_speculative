import java.util.List;

class SourceDatabase {
  private List<Film> films;

  public SourceDatabase(List<Film> films) {
    this.films = films;
  }

  public List<Film> getFilms() {
    return this.films;
  }

  public void addFilm(Film film) {
    films.add(film);
  }
}
