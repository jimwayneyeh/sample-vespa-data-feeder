package tw.jimwayneyeh.vespa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.netty.channel.ChannelOption;
import reactor.netty.http.client.HttpClient;

@SpringBootApplication
public class DataFeedsApplication {
	public static void main(String[] args) {
		SpringApplication.run(DataFeedsApplication.class, args);
	}

	@Bean
	public Gson gson() {
	  return new GsonBuilder().disableHtmlEscaping().create();
	}

	@Bean
	public HttpClient httpClient() {
	  return HttpClient.create()
	        .tcpConfiguration(client -> client.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000));
	}

	@Autowired
	HttpClient httpClient;

	@Bean
	public WebClient webClient() {
	  return WebClient.builder()
	      .clientConnector(new ReactorClientHttpConnector(httpClient))
	      .build();
	}
}
