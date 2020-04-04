package tw.jimwayneyeh.vespa.album_recommendation.datafeeds;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
@Configuration
public class SampleDataFileReader implements SampleDataReader {
  @Value("${vespa.music.file}")
  private String fileName;

  @Override
  public Flux<MusicAlbum> read() {
    try (InputStream input = this.getClass().getResourceAsStream(fileName);
        InputStreamReader reader = new InputStreamReader(input, StandardCharsets.UTF_8)) {
      return Flux.fromStream(
              new CsvToBeanBuilder(reader).withType(MusicAlbum.class).build().stream());
    } catch (IOException ioe) {
      log.warn("Cannot read sample data from '{}'.", fileName, ioe);
      return Flux.empty();
    }
  }

  @Bean
  public SampleDataReader sampleDataReader() {
    return new SampleDataFileReader();
  }
}
