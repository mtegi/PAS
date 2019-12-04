package items.copies.model;

import model.AbstractCopyRepository;
import model.IDataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CopyRepository extends AbstractCopyRepository<Copy> {

    @Autowired
    public CopyRepository(IDataProvider<Copy> provider) {
        super(provider);
    }

    public Copy getCopy(int bookId, String type) {
        return container.values().stream().filter(copy -> copy.getEntity().getId() == bookId && type.contentEquals(copy.getBookType().getClass().getSimpleName())).findAny().orElse(null);
    }

}
