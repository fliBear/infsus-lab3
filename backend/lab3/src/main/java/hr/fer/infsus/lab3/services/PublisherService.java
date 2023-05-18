package hr.fer.infsus.lab3.services;

import hr.fer.infsus.lab3.models.Publisher;

import java.util.List;

public interface PublisherService {
    public Publisher getPublisher(Long id);
    public List<Publisher> getAllPublishers();
    public boolean createPublisher(Publisher publisher);
    public void deletePublisher(Long id);
    public boolean editPublisher(Long id, Publisher publisher);
}
