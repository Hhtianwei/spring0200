package com.tim.spring0200.service;

import java.io.File;
import java.util.List;

import com.tim.spring0200.model.PhotoModel;
import com.tim.spring0200.model.WeatherModel;
import com.tim.spring0200.page.Pagination;
import com.tim.spring0200.page.SearchResult;

public interface PhotoService {
	void savePhoto(List<PhotoModel> list);

	SearchResult<PhotoModel> getAllPhotoData(Pagination page);
}
