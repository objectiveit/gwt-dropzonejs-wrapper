package de.objectiveit.gwt.aws.dropzone.sample.server;

import java.net.URL;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.objectiveit.gwt.aws.dropzone.sample.client.GreetingService;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

	public String greetServer(String objectKey) throws IllegalArgumentException {
		Regions clientRegion = Regions.US_EAST_1;
		String bucketName = "dropzone-test-akash";

		try {
			AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(clientRegion)
					.withCredentials(new ProfileCredentialsProvider("akash-personal")).build();

			// Set the presigned URL to expire after one hour.
			java.util.Date expiration = new java.util.Date();
			long expTimeMillis = expiration.getTime();
			expTimeMillis += 1000 * 60 * 60;
			expiration.setTime(expTimeMillis);

			// Generate the presigned URL.
			System.out.println("Generating pre-signed URL.");
            GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, objectKey)
                    .withMethod(HttpMethod.PUT)
                    .withExpiration(expiration);

			URL url = s3Client.generatePresignedUrl(generatePresignedUrlRequest);

			System.out.println("Pre-Signed URL: " + url.toString());
			return url.toString();
		} catch (AmazonServiceException e) {
			// The call was transmitted successfully, but Amazon S3 couldn't process
			// it, so it returned an error response.
			e.printStackTrace();
		} catch (SdkClientException e) {
			// Amazon S3 couldn't be contacted for a response, or the client
			// couldn't parse the response from Amazon S3.
			e.printStackTrace();
		}
		throw new IllegalArgumentException();
	}

}
