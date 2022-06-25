package com.liuyang19900520.laymanmall.thirdparty.controller;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

/**
 * <p>
 *
 * </p>
 *
 * @author Max Liu
 * @since 2022/06/25
 */
public class Test {

  public static void main(String[] args) {

    final String usage = "\n" +
      "Usage:\n" +
      "    <bucketName> <keyName> \n\n" +
      "Where:\n" +
      "    bucketName - The name of the Amazon S3 bucket. \n\n" +
      "    keyName - A key name that represents a text file. \n" ;


    String bucketName = "layman-cloud";
    String keyName = "test.txt";
    ProfileCredentialsProvider credentialsProvider = ProfileCredentialsProvider.create();
    Region region = Region.AP_NORTHEAST_1;
    S3Presigner presigner = S3Presigner.builder()
      .region(region)
      .credentialsProvider(credentialsProvider)
      .build();

    signBucket(presigner, bucketName, keyName);
    presigner.close();
  }

  // snippet-start:[presigned.java2.generatepresignedurl.main]
  public static void signBucket(S3Presigner presigner, String bucketName, String keyName) {

    try {
      PutObjectRequest objectRequest = PutObjectRequest.builder()
        .bucket(bucketName)
        .key(keyName)
        .contentType("text/plain")
        .build();

      PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
        .signatureDuration(Duration.ofMinutes(10))
        .putObjectRequest(objectRequest)
        .build();

      PresignedPutObjectRequest presignedRequest = presigner.presignPutObject(presignRequest);
      String myURL = presignedRequest.url().toString();
      System.out.println("Presigned URL to upload a file to: " +myURL);
      System.out.println("Which HTTP method needs to be used when uploading a file: " +
        presignedRequest.httpRequest().method());

      // Upload content to the Amazon S3 bucket by using this URL.
      URL url = presignedRequest.url();

      // Create the connection and use it to upload the new object by using the presigned URL.
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setDoOutput(true);
      connection.setRequestProperty("Content-Type","text/plain");
      connection.setRequestMethod("PUT");
      OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
      out.write("This text was uploaded as an object by using a presigned URL.");
      out.close();

      connection.getResponseCode();
      System.out.println("HTTP response code is " + connection.getResponseCode());

    } catch (S3Exception | IOException e) {
      e.getStackTrace();
    }
  }

}
