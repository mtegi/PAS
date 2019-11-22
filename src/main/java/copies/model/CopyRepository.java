package copies.model;

import model.IDataProvider;
import model.MapRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CopyRepository extends MapRepository<Copy> {
    @Autowired
    public CopyRepository(IDataProvider<Copy> provider) {
        super(provider);
    }
}
