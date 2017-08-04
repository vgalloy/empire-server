package com.vgalloy.empire.service.impl;

import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.vgalloy.empire.dao.EmpireDao;
import com.vgalloy.empire.service.EmpireService;

/**
 * Create by Vincent Galloy on 03/08/2017.
 *
 * @author Vincent Galloy
 */
// TODO final ? CGLIB
@Component
class TimerServiceImpl {

	private static final Logger LOGGER = LoggerFactory.getLogger(TimerServiceImpl.class);

	private final EmpireService empireService;
	private final EmpireDao empireDao;

	TimerServiceImpl(EmpireService empireService, EmpireDao empireDao) {
		this.empireService = Objects.requireNonNull(empireService);
		this.empireDao = Objects.requireNonNull(empireDao);
	}

	// @Scheduled(fixedDelay = 15_000)
	public void pulse() {
		LOGGER.info("Update empire");
		this.empireDao.getAll().stream()
			.peek(e -> LOGGER.info("Update : {}", e.getEmpireId().getId()))
			.map(empireService::computeNextRound)
			.forEach(empireDao::update);
	}
}
