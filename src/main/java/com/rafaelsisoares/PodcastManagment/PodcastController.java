package com.rafaelsisoares.PodcastManagment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/podcasts")
public class PodcastController {
  private PodcastService service;

  @Autowired
  public PodcastController(PodcastService service) {
    this.service = service;
  }

  @GetMapping
  public String getRoot() {
    return "Yay podcasts!";
  }

  @GetMapping("/{id}")
  public ResponseEntity<PodcastDTO> getPodcast(@PathVariable Integer id) {
    if (id > 1000) {
      return ResponseEntity.notFound().build();
    }

    Podcast podcast = service.findPodcastById(id);
    PodcastDTO podcastDto = new PodcastDTO(podcast.getId(), podcast.getName(), podcast.getUrl());
    return ResponseEntity.ok(podcastDto);
  }

  @PostMapping
  public ResponseEntity<PodcastDTO> newPodcast(@RequestBody PodcastCreationDTO newPodcast) {
    Podcast podcast = service.createPodcast(newPodcast);

    PodcastDTO podcastDto = new PodcastDTO(podcast.getId(), podcast.getName(), podcast.getUrl());

    return ResponseEntity.status(HttpStatus.CREATED).body(podcastDto);
  }

  @GetMapping("/search")
  public String searchPodcasts(@RequestParam String title) {
    return String.format("Você buscou por podcasts com o titulo: %s", title);
  }
}
