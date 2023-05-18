package hr.fer.infsus.lab3.services;

import hr.fer.infsus.lab3.models.Language;
import hr.fer.infsus.lab3.repositories.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class LanguageServiceImpl implements LanguageService{
    private LanguageRepository languageRepository;

    @Autowired
    public LanguageServiceImpl(LanguageRepository languageRepository){
        this.languageRepository = languageRepository;
    }

    @Override
    public Language getLanguage(Long id) {
        Optional<Language> optional = languageRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    @Override
    public List<Language> getAllLanguages() {
        return languageRepository.findAll();
    }

    @Override
    public boolean createLanguage(Language language) {
        languageRepository.save(language);
        return true;
    }

    @Override
    public void deleteLanguage(Long id) {
        languageRepository.deleteById(id);
    }

    @Override
    public boolean editLanguage(Long id, Language language) {
        Optional<Language> optional = languageRepository.findById(id);
        if(optional.isPresent()){
            Language existingLanguage = optional.get();
            existingLanguage.setName(language.getName());

            languageRepository.save(existingLanguage);
            return true;
        }
        return false;
    }
}
