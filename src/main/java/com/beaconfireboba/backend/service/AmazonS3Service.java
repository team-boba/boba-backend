package com.beaconfireboba.backend.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import com.beaconfireboba.backend.config.AmazonS3Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

@Service
public class AmazonS3Service {
    @Autowired
    private AmazonS3 s3Client;

    @Autowired
    private AmazonS3Config amazonS3Config;

    public String uploadFile(MultipartFile multipartFile) {
        String fileUrl = "";
        try {
            File file = convertMultiPartToFile(multipartFile);
            String fileName = generateFileName(multipartFile);
            fileUrl = amazonS3Config.getEndpointUrl() + "/" + amazonS3Config.getBucketName() + "/" + fileName;
            uploadFileTos3bucket(fileName, file);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileUrl;
    }

    public boolean deleteFileFromS3Bucket(String fileUrl) {
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        s3Client.deleteObject(new DeleteObjectRequest(amazonS3Config.getBucketName() + "/", fileName));
        return true;
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    private String generateFileName(MultipartFile multiPart) {
        return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
    }

    private void uploadFileTos3bucket(String fileName, File file) {
        s3Client.putObject(new PutObjectRequest(amazonS3Config.getBucketName(), fileName, file)
            .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    @Async
    public byte[] downloadFile(final String fileName) {
        byte[] content = null;
        final S3Object s3Object = s3Client.getObject(amazonS3Config.getBucketName(), fileName);
        final S3ObjectInputStream stream = s3Object.getObjectContent();

        try {
            content = IOUtils.toByteArray(stream);
            s3Object.close();
        } catch(final IOException ex) {
            System.out.println("Error downloading file from s3.");
        }
        return content;
    }
}
