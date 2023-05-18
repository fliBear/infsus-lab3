package hr.fer.infsus.lab3.services;

import hr.fer.infsus.lab3.models.Genre;

import java.util.List;

public interface GenreService {
    public Genre getGenre(Long id);
    public List<Genre> getAllGenres();
    public boolean createGenre(Genre genre);
    public void deleteGenre(Long id);
    public boolean editGenre(Long id, Genre genre);
}
