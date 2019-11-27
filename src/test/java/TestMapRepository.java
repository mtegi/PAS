import model.IDataProvider;
import model.MapRepository;

public class TestMapRepository extends MapRepository<TestMappable> {
    public TestMapRepository(IDataProvider<TestMappable> provider) {
        super(provider);
    }
}
