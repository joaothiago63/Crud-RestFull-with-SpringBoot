package com.initialproject.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.initialproject.Controllers.PersonController;
import com.initialproject.data.vo.v1.PersonVO;
import com.initialproject.exceptions.RequiredObjectIsNullException;
import com.initialproject.exceptions.ResourceNotFoundException;
import com.initialproject.mapper.DozerMapper;
import com.initialproject.model.Person;
import com.initialproject.repositories.PersonRepository;



@Service
public class PersonService {

	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	@Autowired
	private PersonRepository repository;
	
	
	public List<PersonVO> findAll(){
	
		logger.info("finding all person!");

		var vo = DozerMapper.parseListObject(repository.findAll(), PersonVO.class);
		vo.stream().forEach( x -> x.add(linkTo(methodOn(PersonController.class).findById(x.getKey().toString())).withSelfRel()));
		
		return vo;
	}
	
	public PersonVO findById(Long id) {
				
		logger.info("finding one person!");
		
		
		
		var entity =   repository.findById(id).orElseThrow(() ->new ResourceNotFoundException("No records found for this ID"));
		
		PersonVO vo = DozerMapper.parseObject(entity, PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(entity.getId().toString())).withSelfRel());
		return vo;

	}
	
	public PersonVO create(PersonVO person) {
		
		if(person == null) throw new RequiredObjectIsNullException();
		
		logger.info("creating one person!");
		
	    var vo = DozerMapper.parseObject(repository.save(DozerMapper.parseObject(person, Person.class)),PersonVO.class);
	    vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey().toString())).withSelfRel());
	    
	    return vo;
	
	
	}
	
   
	
	public PersonVO update(PersonVO person) {
		
		if(person == null) throw new RequiredObjectIsNullException();
		
		logger.info("updating one person!");
		
		Person entity = repository.findById(person.getKey()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		objectUpdate(entity,person);
		
		var vo = DozerMapper.parseObject(repository.save(entity),PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(entity.getId().toString())).withSelfRel());
		return vo;
	}
	
	public void Delete (Long id) {
		
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		repository.delete(entity);
	}
	
	private void objectUpdate(Person entity , PersonVO up) {
		entity.setFirstName(up.getFirstName());
		entity.setLastName(up.getLastName());
		entity.setAddress(up.getAddress());
		entity.setGender(up.getGender());
	}
	
}