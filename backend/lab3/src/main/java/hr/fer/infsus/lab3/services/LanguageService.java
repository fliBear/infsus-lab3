package hr.fer.infsus.lab3.services;

import hr.fer.infsus.lab3.models.Language;

import java.util.List;

public interface LanguageService {
    public Language getLanguage(Long id);
    public List<Language> getAllLanguages();
    public boolean createLanguage(Language language);
    public void  deleteLanguage(Long id);
    public boolean editLanguage(Long id, Language language);
}
