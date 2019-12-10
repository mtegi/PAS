package items.copies.service;

import books.model.Book;
import items.copies.model.Copy;
import items.copies.model.CopyRepository;
import model.AbstractCopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CopyService extends AbstractCopyService<Copy>  {

    private CopyRepository repository;
    @Autowired
    public CopyService(CopyRepository repository) {
        super(repository);
        this.repository = repository;
    }


    public Copy getCopy(int bookId, String type) {
        Copy ret = repository.getCopy(bookId, type);
        return ret;
    }

    public void replaceBookWithNull ( int bookId, Book nullBook)
    {
        repository.getCopiesByEntityId(bookId).forEach(copy -> copy.setEntity(nullBook));
    }


}
