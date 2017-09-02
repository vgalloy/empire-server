package com.vgalloy.empire.persistence.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.vgalloy.empire.service.model.Empire;
import com.vgalloy.empire.service.model.EmpireId;
import com.vgalloy.empire.service.model.UserId;
import com.vgalloy.empire.service.spi.dao.EmpireDao;

/**
 * Create by Vincent Galloy on 03/08/2017.
 *
 * @author Vincent Galloy
 */
@Repository
final class EmpireDaoImpl implements EmpireDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmpireDaoImpl.class);

    private final List<Empire> empires = new ArrayList<>();
    private final Map<UserId, List<EmpireId>> map = new HashMap<>();

    @Override
    public EmpireId createEmpire(UserId userId) {
        Empire empire = Empire.newInstance();
        EmpireId empireId = empire.getEmpireId();

        map.computeIfAbsent(userId, id -> new ArrayList<>()).add(empireId);
        return empireId;
    }

    @Override
    public Empire getEmpireById(EmpireId empireId) {
        return empires.stream()
            .filter(e -> e.getEmpireId().equals(empireId))
            .findFirst()
            .orElseThrow(() -> new NullPointerException("No empire for the id : " + empireId));
    }

    @Override
    public List<Empire> getAll() {
        return new ArrayList<>(empires);
    }

    @Override
    public Empire update(Empire empire) {
        Empire previous = getEmpireById(empire.getEmpireId());
        empires.remove(previous);
        empires.add(empire);
        return empire;
    }

    @Override
    public List<EmpireId> getEmpireIdByUserId(UserId userId) {
        return map.getOrDefault(userId, new ArrayList<>());
    }
}
