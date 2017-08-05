package com.vgalloy.empire.webservice.mapper.impl;

import org.springframework.stereotype.Component;

import com.vgalloy.empire.service.model.Stock;
import com.vgalloy.empire.webservice.dto.StockDto;
import com.vgalloy.empire.webservice.mapper.StockMapper;

/**
 * Create by Vincent Galloy on 03/08/2017.
 *
 * @author Vincent Galloy
 */
@Component
final class StockMapperImpl implements StockMapper {
	@Override
	public StockDto map(Stock stock) {
		StockDto result = new StockDto();
		result.setCurrent(stock.getCurrent());
		result.setGranary(stock.getGranary());
		result.setMax(stock.getMax());
		return result;
	}
}
