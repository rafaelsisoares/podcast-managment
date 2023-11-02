package com.rafaelsisoares.PodcastManagment;

import java.util.Random;
import org.springframework.stereotype.Service;

@Service
public class PodcastService {
  public Podcast findPodcastById(Integer id) {
    Podcast podcast = new Podcast();
    podcast.setId(id);
    podcast.setName("JavaCast");
    podcast.setUrl("https://www.javacast.com");
    podcast.setToken("secret-token" + id);
    return podcast;
  }

  Podcast createPodcast(PodcastCreationDTO newPodcastDto) {
    Podcast podcast = new Podcast();
    // Vamos fingir que estamos salvando o podcast
    // ao atribuir um ID aleatório a ele
    podcast.setId(new Random().nextInt(0, 1000));
    podcast.setToken("super-secret-token-123");

    podcast.setName(newPodcastDto.name());
    podcast.setUrl(newPodcastDto.url());

    return podcast;
  }
}
