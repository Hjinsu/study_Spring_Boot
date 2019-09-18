package com.boot.batch.model.biz;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.batch.dto.BatchDto;
import com.boot.batch.model.dao.BatchDao;

public class BatchBizImpl implements BatchBiz{

	private BatchDao dao;
	
	@Override
	public BatchDto passwordDate() {
		return dao.passwordDate();
	}

	@Override
	public int insert(BatchDto dto) {
		return dao.insert(dto);
	}

	@Override
	public int update(BatchDto dto) {
		return dao.update(dto);
	}

}
