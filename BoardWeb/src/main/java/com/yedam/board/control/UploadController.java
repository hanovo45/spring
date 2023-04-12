package com.yedam.board.control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yedam.board.domain.AttachFileDTO;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@Log4j
public class UploadController {

	@GetMapping("/uploadForm")
	public void uploadForm() {
		log.info("uploadForm...");
	}

	@GetMapping("/uploadAjax")
	public void uploadAjax() {
		log.info("uploadAjax...");
	}

	@PostMapping("/uploadFormAction")
	public void uploadFormPost(MultipartFile[] uploadFile) {
		String uploadFolder = "c:\\upload";
		for (MultipartFile multipartFile : uploadFile) {
			log.info("-------");
			log.info("Upload FileName: " + multipartFile.getOriginalFilename());
			log.info("file Size: " + multipartFile.getSize());

			File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
			try {
				multipartFile.transferTo(saveFile);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@PostMapping(value = "/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<AttachFileDTO> uploadAjaxPost(MultipartFile[] uploadFile) {

		List<AttachFileDTO> list = new ArrayList<>();

		String uploadFolder = "c:\\upload";
		String uploadFolderPath = getFolder();
		// foldet setting
		File uploadPath = new File(uploadFolder, uploadFolderPath);
		log.info(uploadPath);

		if (uploadPath.exists() == false) { // 폴더가 존재하는 체크 후 폴더 생성.
			uploadPath.mkdirs();
		}

		for (MultipartFile multipartFile : uploadFile) {
			AttachFileDTO attachDTO = new AttachFileDTO();

			String uploadFileName = multipartFile.getOriginalFilename();
			attachDTO.setFileName(uploadFileName); // attachDTO setting......

			// 중복된 파일이름을 방지. UUID ->
			UUID uuid = UUID.randomUUID(); // 문자열 랜덤생성.
			uploadFileName = uuid.toString() + "_" + uploadFileName;

			log.info(uploadFileName);

			// File saveFile = new File(uploadFolder, uploadFileName);
			File saveFile = new File(uploadPath, uploadFileName);

			try {
				multipartFile.transferTo(saveFile);

				attachDTO.setUuid(uuid.toString()); // attachDTO setting......
				attachDTO.setUploadPath(uploadFolderPath); // attachDTO setting......

				// 이미지 파일이면 썸네일 이미지 생성.
				if (checkImageType(saveFile)) {
					attachDTO.setImage(true); // attachDTO setting......

					FileOutputStream thumbnail = //
							new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));
					Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 200, 200);
					thumbnail.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			list.add(attachDTO);

		} // end of for

		return list;
	} // end of uploadAjaxPost

	@GetMapping("/display")
	@ResponseBody
	public ResponseEntity<byte[]> getFile(String fileName) {

		File file = new File("c:\\upload\\" + fileName);
		ResponseEntity<byte[]> result = null;

		byte[] files;
		try {
			files = FileCopyUtils.copyToByteArray(file);

			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", Files.probeContentType(file.toPath())); // image/png, image/jpg
			result = new ResponseEntity<byte[]>(files, headers, HttpStatus.OK);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	@GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(String fileName) {

		log.info("download file: " + fileName);
		FileSystemResource resource = new FileSystemResource("C:\\upload\\" + fileName); // 리소스를 읽어와서 파일로 생성.
		if (resource.exists() == false) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 해당 리소스가 없는면 응답정보에 not_found를 보낸다.
		}
		log.info("resource: " + resource);

		String resourceName = resource.getFilename();
		HttpHeaders headers = new HttpHeaders(); // 응답정보의 content 타입을 지정하기 위해.
		try {
			// MIME 타입을 지정하기. application/octet-stream => 처리할 프로그램이 없을 경우에 파일을 다운로드 받는 상황.
			// 만약 한글파일을 링크를 누르면 한글프로그램이 실행이 되지만 알수 없는 확장자는 다운로드 처리.
			headers.add("Content-Disposition",
					"attachment; filename=" + new String(resourceName.getBytes("UTF-8"), "ISO-8859-1"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}

	// 업로드 파일을 저장할 폴더를 날짜별로 생성해서 저장하기 위한 폴더네이밍.
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);

		return str.replace("-", File.separator); // 2023\05\23 , 2023/05/23
	}

	// 이미지 파일 여부 체크.
	private boolean checkImageType(File file) {
		try {
			String contentType = Files.probeContentType(file.toPath());
			log.info(contentType); // image/PNG
			return contentType.startsWith("image");

		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

}
