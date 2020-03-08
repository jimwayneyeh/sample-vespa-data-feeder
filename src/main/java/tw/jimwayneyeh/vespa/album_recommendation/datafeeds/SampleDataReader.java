package tw.jimwayneyeh.vespa.album_recommendation.datafeeds;

import reactor.core.publisher.Flux;

public interface SampleDataReader {
  public Flux<MusicAlbum> read();
}
