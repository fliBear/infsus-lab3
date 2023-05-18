package hr.fer.infsus.lab3.services;

import hr.fer.infsus.lab3.models.Publisher;
import hr.fer.infsus.lab3.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class PublisherServiceImpl implements PublisherService{
    private PublisherRepository publisherRepository;

    @Autowired
    public PublisherServiceImpl(PublisherRepository publisherRepository){
        this.publisherRepository = publisherRepository;
    }

    @Override
    public Publisher getPublisher(Long id) {
        Optional<Publisher> optional = publisherRepository.findById(id);
        if (optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    @Override
    public List<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }

    @Override
    public boolean createPublisher(Publisher publisher) {
        publisherRepository.save(publisher);
        return true;
    }

    @Override
    public void deletePublisher(Long id) {
        publisherRepository.deleteById(id);
    }

    @Override
    public boolean editPublisher(Long id, Publisher publisher) {
        Optional<Publisher> optional = publisherRepository.findById(id);
        if(optional.isPresent()){
            Publisher existingPublisher=optional.get();
            existingPublisher.setName(publisher.getName());

            publisherRepository.save(existingPublisher);
            return true;
        }
        return false;
    }
}
