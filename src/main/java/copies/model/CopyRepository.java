package copies.model;

import model.IDataProvider;
import model.MapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CopyRepository extends MapRepository<Copy> {
    @Autowired
    public CopyRepository(IDataProvider<Copy> provider) {
        super(provider);
    }
}
