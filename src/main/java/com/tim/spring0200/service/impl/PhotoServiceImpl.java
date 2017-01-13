package com.tim.spring0200.service.impl;

import java.util.List;

import com.tim.spring0200.common.dao.CommonDAO;
import com.tim.spring0200.model.PhotoModel;
import com.tim.spring0200.page.Pagination;
import com.tim.spring0200.page.SearchResult;
import com.tim.spring0200.service.PhotoService;

public class PhotoServiceImpl implements PhotoService{
	private CommonDAO commonDAO;
	
	@Override
	public void savePhoto(List<PhotoModel> list){
			commonDAO.saveOrUpdateAllEntity(list);
	}

	public CommonDAO getCommonDAO() {
		return commonDAO;
	}

	public void setCommonDAO(CommonDAO commonDAO) {
		this.commonDAO = commonDAO;
	}

	@Override
	public SearchResult<PhotoModel> getAllPhotoData(Pagination page) {
		String sql = "from PhotoModel";
		return commonDAO.search(sql,page);
	}
	
	
}
