package com.fourward.urcoach.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;

/**
 * FileUploadController
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/upload")
public class UploadController {

//    @PostMapping("")
//    public String handleUpload(@RequestParam("file") MultipartFile uploadFile) throws Exception {
//        String newFileName = "";
//        if(uploadFile != null) {
//            String filePath = "C:/Users/YONG/newdeal/project/team_Project/frontend/public/user-image";
//            File fileDir = new File(filePath);
//            if(fileDir.exists() == false) {
//                fileDir.mkdirs();
//            }
//
//            SimpleDateFormat stampFormat = new SimpleDateFormat("yyyyMMdd-HHmmssSSSS");
//            String timeStamp = stampFormat.format(new Date());
//            String fileExtension = "";
//            int extensionIndex = uploadFile.getOriginalFilename().lastIndexOf(".");
//            if(extensionIndex != -1) {
//                fileExtension = uploadFile.getOriginalFilename().substring(extensionIndex);
//            }
//            String uuid = UUID.randomUUID().toString().replace("-", "");
//            newFileName = timeStamp + "-" + uuid + fileExtension;
//
//            uploadFile.transferTo(new File(filePath + "/" + newFileName));
//        }
//        return newFileName;
//    }

//	s3에서 사진 올리기
//	 s3 관련
	
	private static final String BUCKET_NAME = "urcoach.fileupload.com";
	private static final String ACCESS_KEY = "AKIA3KNYQRDBWQWSKA7Q";
	private static final String SECRET_KEY = "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";
	private static final String clientRegion = "ap-northeast-2";
	private AmazonS3 s3;
	private static final String cloudfront = "http://urcoach.fileupload.com.s3-website.ap-northeast-2.amazonaws.com";

	@PostMapping("")
    public String handleUpload(@RequestParam("file") MultipartFile uploadfile) throws Exception{
    	String newFileName = "";

    	String datePath = new SimpleDateFormat("/yyyy/MM/dd/HH").format(new Date());
    	AWSCredentials awsCredentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
    	s3=AmazonS3ClientBuilder.standard().withRegion(clientRegion).withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).build();
    	
    	if (uploadfile != null) {
            // 파일명 중복 방지
            String uName = UUID.randomUUID().toString();
            String ext = FilenameUtils.getExtension(uploadfile.getOriginalFilename());
            String convName = uName + "." + ext;
            // s3 업로드를 위해 multipart -> File convert
            File convfile = new File(convName);
            convfile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convfile);
            fos.write(uploadfile.getBytes());
            fos.close();
    	
            if (s3 != null) {
                try {
                    PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET_NAME + datePath, convName,
                            convfile);
                    putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead); // file permission
                    s3.putObject(putObjectRequest);
                } catch (Exception e) {
                    // TODO: handle exception
                } finally {
                    s3 = null;
                }
            }
           
            return newFileName = uploadfile.getOriginalFilename() ;
    	}
    	return newFileName = uploadfile.getOriginalFilename() ;
	}    
}