package tw.jimwayneyeh.vespa.album_recommendation.datafeeds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Runner implements CommandLineRunner {
  @Autowired
  SampleDataReader sampleDataReader;
  @Autowired
  Gson gson;

  @Override
  public void run(String... args) throws Exception {
    sampleDataReader.read().subscribe(album -> log.info("Album: {}", gson.toJson(album)));
  }
}
