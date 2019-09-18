package com.boot.batch.model.dao;


import com.boot.batch.dto.BatchDto;

public interface BatchDao {

	public BatchDto passwordDate();
	public int insert(BatchDto dto);
	public int update(BatchDto dto);
}
