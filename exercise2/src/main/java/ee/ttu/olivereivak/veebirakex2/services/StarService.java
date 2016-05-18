package ee.ttu.olivereivak.veebirakex2.services;

import java.util.List;

import javax.inject.Inject;

import com.google.inject.Singleton;

import ee.ttu.olivereivak.veebirakex2.dao.StarDAO;
import ee.ttu.olivereivak.veebirakex2.entity.Star;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Singleton
public class StarService {

    @Inject
    private StarDAO starDAO;

    public Star getByID(Long id) {
        log.debug(String.format("Get Star %s", id));
        return starDAO.findByID(id);
    }

    public List<Star> getAll() {
        log.debug("Get all stars");
        return starDAO.findAll();
    }

    public Star update(Star star) {
        log.debug(String.format("Update Star %s", star.getId()));
        return starDAO.update(star);
    }

}
