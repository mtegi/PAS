package copies.service;

import copies.model.Copy;
import copies.model.CopyRepository;
import model.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
