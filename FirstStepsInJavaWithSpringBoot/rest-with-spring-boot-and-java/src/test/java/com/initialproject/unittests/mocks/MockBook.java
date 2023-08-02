package com.initialproject.unittests.mocks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.initialproject.data.vo.v1.BookVO;
import com.initialproject.model.Book;

public class MockBook {

	
	
	public BookVO mockVO() {
		
		return mockVO(0);
	}
	public Book mockEntity() {
		
		return mockEntity(0);
	}
	
	public List<Book> mockEntityList(){
		List<Book> vo = new ArrayList<>();
		for(int i=0;i<14;i++) {
			vo.add(mockEntity(i));
		}
		return vo;
	}
	public List<BookVO> mockVOList(){
		List<BookVO> vo = new ArrayList<>();
		for(int i=0;i<14;i++) {
			vo.add(mockVO(i));
		}
		return vo;
	}
	
	
	public BookVO mockVO(Integer num) {
		BookVO vo = new BookVO();
		vo.setAuthor("Author Test"+num);
		vo.setTitle("Title test"+num);
		vo.setKey(num.longValue());
		vo.setPrice(100.0*num);
		vo.setLaunchDate(new Date());
		
		return vo;
	}
	
	public Book mockEntity(Integer num) {
		Book vo = new Book();
		vo.setAuthor("Author Test"+num);
		vo.setTitle("Title test"+num);
		vo.setId(num.longValue());
		vo.setPrice(100.0*num);
		vo.setLaunchDate(new Date());
		
		return vo;
	}
	
}
