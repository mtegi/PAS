import model.IMapable;

public class TestMappable implements IMapable {

    private int id;

    public TestMappable(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }
}
