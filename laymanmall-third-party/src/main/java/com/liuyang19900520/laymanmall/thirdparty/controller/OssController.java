package com.liuyang19900520.laymanmall.thirdparty.controller;

import com.liuyang19900520.laymanmall.common.utils.R;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

@RestController
public class OssController {

  @RequestMapping("/s3/policy/{fileName}")
  public R policy(@PathVariable("fileName") String fileName) {

    Map<String, String> respMap = new HashMap<>();
    final String usage = "\n" + "Usage:\n" + "    <bucketName> <keyName> \n\n" + "Where:\n"
      + "    bucketName - The name of the Amazon S3 bucket. \n\n"
      + "    keyName - A key name that represents a text file. \n";

    String bucketName = "layman-cloud";
    String keyName = fileName;
    ProfileCredentialsProvider credentialsProvider = ProfileCredentialsProvider.create();

    Region region = Region.AP_NORTHEAST_1;
    S3Presigner presigner = S3Presigner.builder().region(region)
      .credentialsProvider(credentialsProvider).build();

    try {
      PutObjectRequest objectRequest = PutObjectRequest.builder().bucket(bucketName).key(keyName)
        .contentType("multipart/form-data").build();

      PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
        .signatureDuration(Duration.ofMinutes(10)).putObjectRequest(objectRequest).build();

      PresignedPutObjectRequest presignedRequest = presigner.presignPutObject(presignRequest);
      String myURL = presignedRequest.url().toString();
      System.out.println("Presigned URL to upload a file to: " + myURL);
      System.out.println("Which HTTP method needs to be used when uploading a file: "
        + presignedRequest.httpRequest().method());

      // Upload content to the Amazon S3 bucket by using this URL.
      URL url = presignedRequest.url();

      respMap = new LinkedHashMap<String, String>();
//      respMap.put("accessid", accessId);
      //respMap.put("policy", encodedPolicy);
      respMap.put("signedUrl", myURL);
      //respMap.put("dir", dir);
      //respMap.put("host", host);
      //respMap.put("expire", String.valueOf(expireEndTime / 1000));

      // Create the connection and use it to upload the new object by using the presigned URL.
//      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//      connection.setDoOutput(true);
//      connection.setRequestProperty("Content-Type", "text/plain");
//      connection.setRequestMethod("PUT");
//      OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
//      out.write("This text was uploaded as an object by using a presigned URL.");
//      out.close();
//
//      connection.getResponseCode();
//      System.out.println("HTTP response code is " + connection.getResponseCode());

    } catch (S3Exception e) {
      e.getStackTrace();
    }
    presigner.close();

    return R.ok().put("data", respMap);
  }

  static byte[] HmacSHA256(String data, byte[] key) throws Exception {
    String algorithm = "HmacSHA256";
    Mac mac = Mac.getInstance(algorithm);
    mac.init(new SecretKeySpec(key, algorithm));
    return mac.doFinal(data.getBytes("UTF-8"));
  }

  static byte[] getSignatureKey(String key, String dateStamp, String regionName, String serviceName)
    throws Exception {
    byte[] kSecret = ("AWS4" + key).getBytes("UTF-8");
    byte[] kDate = HmacSHA256(dateStamp, kSecret);
    byte[] kRegion = HmacSHA256(regionName, kDate);
    byte[] kService = HmacSHA256(serviceName, kRegion);
    byte[] kSigning = HmacSHA256("aws4_request", kService);
    return kSigning;
  }
}
