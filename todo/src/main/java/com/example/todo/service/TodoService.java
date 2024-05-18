package com.example.todo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.todo.entity.Todo;
import com.example.todo.repository.TodoRepo;

@Service
public class TodoService {
	@Autowired
	TodoRepo todoRepo;

	public List<Todo> getAllToDoItems() {
		return todoRepo.findAll();
	}

	public Todo getToDoById(Long id) {
		return todoRepo.findById(id).get();
	}

	public void saveToDo(String title, Date date, String status) {
		Todo todo = new Todo();
		todo.setDate(date);
		todo.setStatus(status);
		todo.setTitle(title);
		todoRepo.save(todo);
	}

	public void deleteToDoById(Long id) {
		todoRepo.deleteById(id);
	}

	public void changeTitle(Long id, String title) {
		Todo todo = new Todo();
		todo = todoRepo.findById(id).get();
		todo.setTitle(title);
		todoRepo.save(todo);
	}

	public void changeDate(Long id, Date date) {
		Todo todo = new Todo();
		todo = todoRepo.findById(id).get();
		todo.setDate(date);
		todoRepo.save(todo);
	}

	public void changeStatus(Long id, String status) {
		Todo todo = new Todo();
		todo = todoRepo.findById(id).get();
		todo.setStatus(status);
		todoRepo.save(todo);
	}
}
