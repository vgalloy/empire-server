package com.vgalloy.empire.persistence.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private final List<Empire> empires = new ArrayList<>();
    private final Map<UserId, List<EmpireId>> map = new HashMap<>();

    @Override
    public EmpireId createEmpire(final UserId userId) {
        final Empire empire = Empire.newInstance();
        final EmpireId empireId = empire.getEmpireId();

        map.computeIfAbsent(userId, id -> new ArrayList<>()).add(empireId);
        return empireId;
    }

    @Override
    public Empire getEmpireById(final EmpireId empireId) {
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
    public Empire update(final Empire empire) {
        final Empire previous = getEmpireById(empire.getEmpireId());
        empires.remove(previous);
        empires.add(empire);
        return empire;
    }

    @Override
    public List<EmpireId> getEmpireIdByUserId(final UserId userId) {
        return map.getOrDefault(userId, new ArrayList<>());
    }
}
