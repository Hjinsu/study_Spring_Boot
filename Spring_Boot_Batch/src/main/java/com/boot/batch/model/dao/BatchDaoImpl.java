package com.boot.batch.model.dao;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.boot.batch.dto.BatchDto;

public class BatchDaoImpl implements BatchDao{

	private SqlSessionTemplate sqlSession;
	private String namespace = "batch";
	
	@Override
	public BatchDto passwordDate() {
		BatchDto d = new BatchDto();
		
		d = sqlSession.selectOne(namespace+"selectpassword");
		
		return d;
	}

	@Override
	public int insert(BatchDto dto) {
		int res = sqlSession.insert("dto",dto);
		return res;
	}

	@Override
	public int update(BatchDto dto) {
		int res = sqlSession.update("dto",dto);
		return res;
	}

}
