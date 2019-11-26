package copies.service;

import copies.model.Copy;
import model.AbstractService;
import model.MapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CopyService extends AbstractService<Copy> implements ICopyService{

    @Autowired
    public CopyService(MapRepository<Copy> repository) {
        super(repository);
    }
}
