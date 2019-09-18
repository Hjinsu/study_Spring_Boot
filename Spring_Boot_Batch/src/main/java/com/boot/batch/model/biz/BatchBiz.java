package com.boot.batch.model.biz;


import com.boot.batch.dto.BatchDto;

public interface BatchBiz {

	public BatchDto passwordDate();
	public int insert(BatchDto dto);
	public int update(BatchDto dto);
}
