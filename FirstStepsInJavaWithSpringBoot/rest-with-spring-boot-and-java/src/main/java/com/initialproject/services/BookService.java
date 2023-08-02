package com.initialproject.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.initialproject.Controllers.BookController;
import com.initialproject.data.vo.v1.BookVO;
import com.initialproject.exceptions.RequiredObjectIsNullException;
import com.initialproject.exceptions.ResourceNotFoundException;
import com.initialproject.mapper.DozerMapper;
import com.initialproject.model.Book;
import com.initialproject.repositories.BookRepository;

@Service
public class BookService {

	private Logger logger = Logger.getLogger(BookService.class.getName());

	@Autowired
	private BookRepository repository;

	public List<BookVO> findAll() {

		logger.info("finding all book!");

		var vo = DozerMapper.parseListObject(repository.findAll(), BookVO.class);
		vo.stream().forEach(
				x -> x.add(linkTo(methodOn(BookController.class).findById(x.getKey().toString())).withSelfRel()));

		return vo;
	}

	public BookVO findById(Long id) {

		logger.info("finding one book!");

		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

		BookVO vo = DozerMapper.parseObject(entity, BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(entity.getId().toString())).withSelfRel());
		
		return vo;

	}

	public BookVO create(BookVO book) {

		if (book == null) throw new RequiredObjectIsNullException();

		logger.info("creating one book!");
        
		book.setKey(null);
		var vo = DozerMapper.parseObject(repository.save(DozerMapper.parseObject(book, Book.class)),BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey().toString())).withSelfRel());

		return vo;

	}

	public BookVO update(BookVO book) {

		if (book == null)
			throw new RequiredObjectIsNullException();

		logger.info("updating one book!");

		Book entity = repository.findById(book.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

		objectUpdate(entity, book);

		var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(entity.getId().toString())).withSelfRel());
		return vo;
	}

	public void Delete(Long id) {

		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		repository.delete(entity);
	}

	private void objectUpdate(Book entity, BookVO up) {
	
		entity.setAuthor(up.getAuthor());
		entity.setLaunchDate(up.getLaunchDate());
		entity.setPrice(up.getPrice());
		entity.setTitle(up.getTitle());
	}
	
	
}