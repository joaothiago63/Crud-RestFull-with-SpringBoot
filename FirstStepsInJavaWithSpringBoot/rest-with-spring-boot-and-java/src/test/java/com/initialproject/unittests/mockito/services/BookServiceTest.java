package com.initialproject.unittests.mockito.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.initialproject.data.vo.v1.BookVO;
import com.initialproject.exceptions.RequiredObjectIsNullException;
import com.initialproject.model.Book;
import com.initialproject.repositories.BookRepository;
import com.initialproject.services.BookService;
import com.initialproject.unittests.mocks.MockBook;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class BookServiceTest {

MockBook input;
	
	@InjectMocks
	private BookService service;
	
	@Mock
	private BookRepository repository;

	@BeforeEach
	void setUpMocks() throws Exception {
		input = new MockBook();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindAll() {
	
		var entity = input.mockEntityList();
		
		when(repository.findAll()).thenReturn(entity);
		
	    var vo = service.findAll();
	    int cont = 0;
	    
	    for(BookVO e: vo) {
	    	assertEquals("links: [</api/book/v1/"+ cont +">;rel=\"self\"]", e.toString());
	        cont++;
	    }
		
	}

	@Test
	void testFindById() {
		Book entity = input.mockEntity(1); 
		entity.setId(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		
		var result = service.findById(1L);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertEquals("links: [</api/book/v1/1>;rel=\"self\"]",result.toString());


	}

	@Test
	void testCreate() {
		
		var entity = input.mockEntity(1); 
		entity.setId(null);
		
		var persist = input.mockEntity(1); 
		
	
		
		var vo = input.mockVO(1);
		
		when(repository.save(entity)).thenReturn(persist);
		
		var result = service.create(vo);
		
		assertEquals("links: [</api/book/v1/1>;rel=\"self\"]",result.toString());
		
	
		
		

		
		
	}
	
	@Test
	void testCreateWithNullPerson() {
		
		Exception exception = assertThrows(RequiredObjectIsNullException.class,() -> service.create(null));
		
		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.contains(actualMessage));
		
			
	}
	
	@Test
	void testUpdateWithNullPerson() {
		
		Exception exception = assertThrows(RequiredObjectIsNullException.class,() -> service.update(null));
		
		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.contains(actualMessage));
		
			
	}


	@Test
	void testUpdate() {
		
		var entity = input.mockEntity(1);
		var persist = input.mockEntity(1);
		var vo = input.mockVO(1);
		
		
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		when(repository.save(entity)).thenReturn(persist);
		
		var result = service.update(vo);
		
	
		assertEquals("links: [</api/book/v1/1>;rel=\"self\"]",result.toString());

	
		
	}

	@Test
	void testDelete() {
		
		var entity = input.mockEntity(1);
		entity.setId(1L);
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		
		service.Delete(1L);
		
	}
}
