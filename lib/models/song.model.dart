class Song {
  final String title;
  final String description;
  final String url;
  final String coverUrl;

  Song({
    required this.title,
    required this.description,
    required this.url,
    required this.coverUrl,
  });

  static List<Song> songs = [
    Song(
      title: "Bing Ba Ra",
      description: "Bing Ba Ra",
      url: "assets/music/BING BA RA BING BA RA BUM .mp3",
      coverUrl: "assets/images/BING BA RA BING BA RA BUM .png",
    ),
    Song(
      title: "DaDaDa",
      description: "DaDaDa",
      url: "assets/music/DaDaDa.mp3",
      coverUrl: "assets/images/Da Da Da.png",
    ),
    Song(
      title: "Dream",
      description: "DREAMS-LOST SKY",
      url: "assets/music/DREAMS - LOST SKY.mp3",
      coverUrl: "assets/images/DREAMS - LOST SKY.png",
    ),
    Song(
      title: "Low",
      description: "Low",
      url: "assets/music/Low.mp3",
      coverUrl: "assets/images/Low.png",
    ),
  ];
}
