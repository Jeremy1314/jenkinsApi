package jenkinsApi;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class test {
	// Credentials
	static String username = "username";
	static String password = "password";

	// Jenkins url
	static String jenkinsUrl = "http://localhost:8080";

	// Build name
	static String jobName = "jobname";
	
	// Build token
	static String buildToken = "buildToken";
	
	public static void main(String[] args) {
		System.out.println("start");
		try {
			String result = createJob();//build the job
			System.out.println(result);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("end");
	}
	
	public static String getBuilds()throws ClientProtocolException, IOException{
		String urlString = jenkinsUrl + "/job/"+jobName+"/api/json?tree=builds[number]{0,10}";
		
		URI uri = URI.create(urlString);
		HttpHost host = new HttpHost(uri.getHost(), uri.getPort(), uri.getScheme());
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(new AuthScope(uri.getHost(), uri.getPort()), new UsernamePasswordCredentials(username, password));
		// Create AuthCache instance
		AuthCache authCache = new BasicAuthCache();
		// Generate BASIC scheme object and add it to the local auth cache
		BasicScheme basicAuth = new BasicScheme();
		authCache.put(host, basicAuth);
		CloseableHttpClient httpClient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
		HttpGet httpGet = new HttpGet(uri);
		//httpPost.setEntity(reqEntity);
		// Add AuthCache to the execution context
		HttpClientContext localContext = HttpClientContext.create();
		localContext.setAuthCache(authCache);

		HttpResponse response = httpClient.execute(host, httpGet, localContext);
		String result = EntityUtils.toString(response.getEntity());
		System.out.println(result);
		return result;
	}
	
	public static String getJobs()throws ClientProtocolException, IOException{
		String urlString = jenkinsUrl + "/api/json?tree=jobs[name]{1,3},views[name,jobs[name]{2}]";
		
		URI uri = URI.create(urlString);
		HttpHost host = new HttpHost(uri.getHost(), uri.getPort(), uri.getScheme());
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(new AuthScope(uri.getHost(), uri.getPort()), new UsernamePasswordCredentials(username, password));
		// Create AuthCache instance
		AuthCache authCache = new BasicAuthCache();
		// Generate BASIC scheme object and add it to the local auth cache
		BasicScheme basicAuth = new BasicScheme();
		authCache.put(host, basicAuth);
		CloseableHttpClient httpClient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
		HttpGet httpGet = new HttpGet(uri);
		//httpPost.setEntity(reqEntity);
		// Add AuthCache to the execution context
		HttpClientContext localContext = HttpClientContext.create();
		localContext.setAuthCache(authCache);

		HttpResponse response = httpClient.execute(host, httpGet, localContext);
		String result = EntityUtils.toString(response.getEntity());
		System.out.println(result);
		return result;
	}
	@SuppressWarnings("deprecation")
	
	public static String updateJobConfig()throws ClientProtocolException, IOException{
		String urlString = jenkinsUrl + "/job/" + jobName + "/config.xml";
		
		URI uri = URI.create(urlString);
		HttpHost host = new HttpHost(uri.getHost(), uri.getPort(), uri.getScheme());
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(new AuthScope(uri.getHost(), uri.getPort()), new UsernamePasswordCredentials(username, password));
		// Create AuthCache instance
		AuthCache authCache = new BasicAuthCache();
		// Generate BASIC scheme object and add it to the local auth cache
		BasicScheme basicAuth = new BasicScheme();
		authCache.put(host, basicAuth);
		CloseableHttpClient httpClient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
		HttpPost httpPost = new HttpPost(uri);
		FileEntity entity = new FileEntity(new File("E:\\config.xml"));
		httpPost.setEntity(entity);
		httpPost.setHeader("Content-Type", "multipart/form-data;charset=UTF-8");
		//httpPost.setEntity(reqEntity);
		// Add AuthCache to the execution context
		HttpClientContext localContext = HttpClientContext.create();
		localContext.setAuthCache(authCache);

		HttpResponse response = httpClient.execute(host, httpPost, localContext);
		String result = EntityUtils.toString(response.getEntity());
		return result;
	}
	
	@SuppressWarnings("deprecation")
	public static String createJob()throws ClientProtocolException, IOException{
		String urlString = jenkinsUrl + "/createItem?name=TestIOSJob2";
		
		URI uri = URI.create(urlString);
		HttpHost host = new HttpHost(uri.getHost(), uri.getPort(), uri.getScheme());
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(new AuthScope(uri.getHost(), uri.getPort()), new UsernamePasswordCredentials(username, password));
		// Create AuthCache instance
		AuthCache authCache = new BasicAuthCache();
		// Generate BASIC scheme object and add it to the local auth cache
		BasicScheme basicAuth = new BasicScheme();
		authCache.put(host, basicAuth);
		CloseableHttpClient httpClient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
		HttpPost httpPost = new HttpPost(uri);
		FileEntity entity = new FileEntity(new File("E:\\config.xml"));
		httpPost.setEntity(entity);
		httpPost.setHeader("Content-Type", "application/xml;charset=UTF-8"); 
		//httpPost.setEntity(reqEntity);
		// Add AuthCache to the execution context
		HttpClientContext localContext = HttpClientContext.create();
		localContext.setAuthCache(authCache);
		
		HttpResponse response = httpClient.execute(host, httpPost, localContext);
		String result = EntityUtils.toString(response.getEntity());
		return result;
	}
	
	@SuppressWarnings("deprecation")
	public static String getJobConfig()throws ClientProtocolException, IOException{
		String urlString = jenkinsUrl + "/job/" + jobName + "/config.xml";
		
		URI uri = URI.create(urlString);
		HttpHost host = new HttpHost(uri.getHost(), uri.getPort(), uri.getScheme());
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(new AuthScope(uri.getHost(), uri.getPort()), new UsernamePasswordCredentials(username, password));
		// Create AuthCache instance
		AuthCache authCache = new BasicAuthCache();
		// Generate BASIC scheme object and add it to the local auth cache
		BasicScheme basicAuth = new BasicScheme();
		authCache.put(host, basicAuth);
		CloseableHttpClient httpClient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
		HttpGet httpGet = new HttpGet(uri);
		//httpPost.setEntity(reqEntity);
		// Add AuthCache to the execution context
		HttpClientContext localContext = HttpClientContext.create();
		localContext.setAuthCache(authCache);

		HttpResponse response = httpClient.execute(host, httpGet, localContext);
		String result = EntityUtils.toString(response.getEntity());
		return result;
	}
	
	public static String build() throws ClientProtocolException, IOException {
		String urlString = jenkinsUrl + "/job/" + jobName + "/build?token=" + buildToken;
		URI uri = URI.create(urlString);
		HttpHost host = new HttpHost(uri.getHost(), uri.getPort(), uri.getScheme());
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(new AuthScope(uri.getHost(), uri.getPort()), new UsernamePasswordCredentials(username, password));
		// Create AuthCache instance
		AuthCache authCache = new BasicAuthCache();
		// Generate BASIC scheme object and add it to the local auth cache
		BasicScheme basicAuth = new BasicScheme();
		authCache.put(host, basicAuth);
		CloseableHttpClient httpClient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
		HttpGet httpGet = new HttpGet(uri);
		// Add AuthCache to the execution context
		HttpClientContext localContext = HttpClientContext.create();
		localContext.setAuthCache(authCache);

		HttpResponse response = httpClient.execute(host, httpGet, localContext);

		return EntityUtils.toString(response.getEntity());
	} 
	
	public static String getBuildLog() throws ClientProtocolException, IOException{
		String buildId = "42";
		String urlString = jenkinsUrl + "/job/" + jobName +"/" +buildId+"/logText/progressiveText?start=0";
		URI uri = URI.create(urlString);
		HttpHost host = new HttpHost(uri.getHost(), uri.getPort(), uri.getScheme());
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(new AuthScope(uri.getHost(), uri.getPort()), new UsernamePasswordCredentials(username, password));
		// Create AuthCache instance
		AuthCache authCache = new BasicAuthCache();
		// Generate BASIC scheme object and add it to the local auth cache
		BasicScheme basicAuth = new BasicScheme();
		authCache.put(host, basicAuth);
		CloseableHttpClient httpClient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
		HttpGet httpGet = new HttpGet(uri);
		// Add AuthCache to the execution context
		HttpClientContext localContext = HttpClientContext.create();
		localContext.setAuthCache(authCache);

		HttpResponse response = httpClient.execute(host, httpGet, localContext);

		return EntityUtils.toString(response.getEntity());
		
	}
}

