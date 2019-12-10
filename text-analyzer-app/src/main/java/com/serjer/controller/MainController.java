package com.serjer.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.serjer.domain.LastLetter;
import com.serjer.repo.LastLetterRepo;
import com.serjer.repo.TextRepo;
import com.serjer.service.TextService;

import one.util.streamex.StreamEx;

@Controller
public class MainController {
	
	@Value("${upload.path}")
    private String uploadPath;
	
	@Autowired
	private TextService textService;
	
	@Autowired
	private LastLetterRepo lastLetterRepo;
	
	@Autowired
	private TextRepo textRepo;
	
	@GetMapping("/")
	public String main(Model model) {
	
	return "input";
	}
	
	@PostMapping("/")
	public String inputText(Model model, @RequestParam String inputText) {
	textService.countWordsByLastLetter(inputText);
	
	List<LastLetter> list = StreamEx.of(lastLetterRepo.findAllByTextTextBody(inputText))
			  .distinct(LastLetter::getLastLetter)
			  .toList();
	model.addAttribute("list", list);
	return "input";
	}
	
	@GetMapping("/upload")
	public String uploadFileForm(Model model) {
	
	return "upload";
	}
	
	@PostMapping("/upload")
	public String uploadFile(Model model, @RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
		 if (file != null && !file.getOriginalFilename().isEmpty()) {
	            File uploadDir = new File(uploadPath);

	            if (!uploadDir.exists()) {
	                uploadDir.mkdir();
	            }

	            String uuidFile = UUID.randomUUID().toString();
	            String resultFilename = uuidFile + "." + file.getOriginalFilename();

	            file.transferTo(new File(uploadPath + resultFilename));
	            Path filePath = Paths.get(uploadPath, resultFilename);
	            String fileContent = Files.readString(filePath);	
	            
	            textService.countWordsByLastLetter(fileContent);
	            List<LastLetter> list = StreamEx.of(lastLetterRepo.findAllByTextTextBody(fileContent))
	      			  .distinct(LastLetter::getLastLetter)
	      			  .toList();
	        	model.addAttribute("list", list);
	        }
	return "upload";
	}
	
	@GetMapping("/history")
	public String showHistory(Model model) {
	model.addAttribute("historyList", textRepo.findAll());
	return "history";
	}
}