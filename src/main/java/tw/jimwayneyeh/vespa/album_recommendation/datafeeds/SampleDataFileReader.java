package tw.jimwayneyeh.vespa.album_recommendation.datafeeds;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
@Configuration
public class SampleDataFileReader implements SampleDataReader {
  @Value("${vespa.music.file}")
  private String fileName;

  @Override
  public Flux<MusicAlbum> read() {
    try (InputStream input = this.getClass().getResourceAsStream(fileName)) {
      return Flux.fromIterable(IOUtils.readLines(input, StandardCharsets.UTF_8))
          .filter(line -> !StringUtils.isEmpty(line))
          .map(line -> {
            String[] fileds = line.split(",");
            return new MusicAlbum()
                .artist(fileds[0])
                .name(fileds[1])
                .releaseYear(Integer.parseInt(fileds[2]));
          });
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
