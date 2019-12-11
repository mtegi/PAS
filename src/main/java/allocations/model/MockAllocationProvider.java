package allocations.model;

import items.copies.service.CopyService;
import items.filmCopies.service.FilmCopyService;
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
    private FilmCopyService filmCopyService;


    @Override
    public void fill(Collection<Allocation> data) {
        data.add(new Allocation(idManager.nextId(),filmCopyService.findCopyByEntityId(5),userService.findByUsername("user"), LocalDateTime.now(),LocalDateTime.now()));
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

    @Autowired
    public void setFilmCopyService(FilmCopyService filmCopyService) {
        this.filmCopyService = filmCopyService;
    }
}
