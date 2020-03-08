package tw.jimwayneyeh.vespa.album_recommendation.datafeeds;

import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Accessors(fluent = true)
public class MusicAlbum {
  private String artist;
  private String name;
  private Integer releaseYear;
}
