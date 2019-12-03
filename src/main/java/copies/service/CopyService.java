package copies.service;

import books.model.Book;
import copies.model.Copy;
import copies.model.CopyRepository;
import model.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CopyService extends AbstractService<Copy> implements ICopyService{

    private CopyRepository repository;
    @Autowired
    public CopyService(CopyRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public Copy getCopy(int bookId) {
        Copy ret = repository.getCopy(bookId);
        return ret;
    }

    @Override
    public Copy getCopy(int bookId, String type) {
        Copy ret = repository.getCopy(bookId, type);
        return ret;
    }

    @Override
    public ArrayList<Copy> getCopiesByBookTitle(String title) {
       return repository.getCopiesByBookTitle(title);
    }

    public long count(Book book){
       return repository.getAll().stream().filter(copy -> copy.getBook().getId() == book.getId()).count();
    }
}
