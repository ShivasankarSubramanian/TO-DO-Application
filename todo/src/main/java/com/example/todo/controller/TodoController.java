package com.example.todo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.example.todo.entity.Todo;
import com.example.todo.repository.TodoRepo;
import com.example.todo.service.TodoService;

@Controller
public class TodoController {
	@Autowired
	TodoService todoService;
	@Autowired
	TodoRepo todoRepo;

	@GetMapping("/")
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("create_Todo");
		return modelAndView;
	}

	@PostMapping("/addTodo")
	public ModelAndView saveTodo(@RequestParam("title") String title, @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
			@RequestParam("status") String status) {
		todoService.saveToDo(title, date, status);
		ModelAndView mv = new ModelAndView();
		List<Todo> todoList = todoRepo.findAll();
		mv.setViewName("todo_List");
		mv.addObject("todoList", todoList);
		return mv;
	}
	@GetMapping("/deleteTodo/{id}")
	public ModelAndView deleteTODO(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView();
		todoService.deleteToDoById(id);
		List<Todo> todoList = todoRepo.findAll();
		mv.setViewName("todo_List");
		mv.addObject("todoList", todoList);
		return mv;
	}
	@PostMapping("/changeTitle")
	public ModelAndView changeTitle(@RequestParam("id") Long id, @RequestParam("title") String title) {
		ModelAndView mv = new ModelAndView();
		todoService.changeTitle(id, title);
		List<Todo> todoList = todoRepo.findAll();
		mv.setViewName("todo_List");
		mv.addObject("todoList", todoList);
		return mv;
	}
	@PostMapping("/changeDate")
	public ModelAndView changeDate(@RequestParam("id") Long id, @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
		ModelAndView mv = new ModelAndView();
		todoService.changeDate(id, date);
		List<Todo> todoList = todoRepo.findAll();
		mv.setViewName("todo_List");
		mv.addObject("todoList", todoList);
		return mv;
	}
	@PostMapping("/changeStatus")
	public ModelAndView changeStatus(@RequestParam("id") Long id, @RequestParam("status") String status) {
		ModelAndView mv = new ModelAndView();
		todoService.changeStatus(id, status);
		List<Todo> todoList = todoRepo.findAll();
		mv.setViewName("todo_List");
		mv.addObject("todoList", todoList);
		return mv;
	}
	@GetMapping("/listTodo")
	public String showView(Model model) {
		List<Todo> todoList = todoService.getAllToDoItems();
		model.addAttribute("todoList", todoList);
		return "todo_List";
	}
}
