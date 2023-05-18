package hr.fer.infsus.lab3.services;

import hr.fer.infsus.lab3.models.Genre;
import hr.fer.infsus.lab3.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class GenreServiceImpl implements GenreService{
    private GenreRepository genreRepository;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository){
        this.genreRepository = genreRepository;
    }

    @Override
    public Genre getGenre(Long id){
        Optional<Genre> optional = genreRepository.findById(id);
        if (optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    @Override
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    @Override
    public boolean createGenre(Genre genre) {
        genreRepository.save(genre);
        return true;
    }

    @Override
    public void deleteGenre(Long id) {
        genreRepository.deleteById(id);
    }

    @Override
    public boolean editGenre(Long id, Genre genre) {
        Optional<Genre> optional = genreRepository.findById(id);
        if(optional.isPresent()){
            Genre existingGenre = optional.get();
            existingGenre.setName(genre.getName());
            existingGenre.setDescription(genre.getDescription());

            genreRepository.save(existingGenre);
            return true;
        }
        return false;
    }
}
