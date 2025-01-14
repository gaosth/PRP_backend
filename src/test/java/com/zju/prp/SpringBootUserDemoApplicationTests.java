package com.zju.prp;

import com.zju.prp.model.Projects;
import com.zju.prp.model.UploadImages;
import com.zju.prp.repository.ProjectsRepository;
import com.zju.prp.repository.UploadImagesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

import java.io.File;
import java.util.Map;
import java.util.stream.Collectors;


@SpringBootTest
class SpringBootUserDemoApplicationTests {

	@Autowired
	ProjectsRepository projectsRepo;
	@Autowired
	UploadImagesRepository uploadImagesRepo;

	@Test
	void contextLoads() {
	}

	@Test
	void writeDatabase() {
		String directoryPath = "D:\\Academic\\毕设\\projects\\test_img";
		File directory = new File(directoryPath);

		if (!directory.exists() || !directory.isDirectory()) {
			throw new IllegalArgumentException("Invalid directory path: " + directoryPath);
		}

		File[] pngFiles = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".png"));
		if (pngFiles == null || pngFiles.length == 0) {
			System.out.println("No PNG files found in directory: " + directoryPath);
			return;
		}

		for (File pngFile : pngFiles) {
			String fileName = pngFile.getName();
			String minioPath = "/resource/" + fileName;
			int randomUserId = (int) (Math.random() * 10) + 1;

			// 创建 UploadImages 对象
			UploadImages uploadImage = new UploadImages();
			uploadImage.setMinioId(minioPath);
			uploadImage.setUserId(randomUserId); // 假设 Users 表中有 ID 1-10 的数据

			// 保存到数据库
			uploadImagesRepo.save(uploadImage);

			System.out.println("Saved image: " + minioPath + " with User_ID: " + randomUserId);
		}
	}

	@Transactional
	@Test
	void minioTest(){
		List<Projects> projectsList = this.projectsRepo.findAll();
		// 将 Projects 对象转换为 Map 格式
		List<Map<String, Object>> result = projectsList.stream().map(project -> {
			Map<String, Object> map = new HashMap<>();
			map.put("id", project.getProjectId());
			map.put("title", project.getTitle());
			map.put("image_url", project.getImage() != null ? project.getImage().getImageUrl() : null);
			return map;
		}).collect(Collectors.toList());
		System.out.println(result);
	}

}
