package model;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.List;

public interface ICrudController<T> {
    List<T> Get();
    T GetSingle(int id);
    String Delete(int id);
    String Create(JsonNode body);
    void Update(int copyId, JsonNode body);
}
