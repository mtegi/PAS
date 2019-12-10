package allocations.model;

import items.copies.service.CopyService;
import login.service.UserService;
import model.IDataProvider;
import model.IIdManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collection;

@Component
public class MockAllocationProvider implements IDataProvider<Allocation> {

    private IIdManager idManager;
    private UserService userService;
    private CopyService copyService;


    @Override
    public void fill(Collection<Allocation> data) {
        data.add(new Allocation(idManager.nextId(),copyService.findCopyByEntityId(1), userService.findByUsername("user"), LocalDateTime.now(),LocalDateTime.now()));
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setCopyService(CopyService copyService) {
        this.copyService = copyService;
    }

    @Qualifier("AllocationIdManager")
    @Autowired
    public void setIdManager(IIdManager idManager) {
        this.idManager = idManager;
    }
}
