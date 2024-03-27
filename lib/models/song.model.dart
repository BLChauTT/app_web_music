class Song {
  late final String title;
  late final String description;
  late final String url;
  late final String coverUrl;

  Song({
    required this.title,
    required this.description,
    required this.url,
    required this.coverUrl,
  });

  static List<Song> songs = [
    Song(
      title: "Bing Ba Ra",
      description: "Roses",
      url: "music/BING BA RA BING BA RA BUM .mp3",
      coverUrl: "assets/images/BING BA RA BING BA RA BUM .png",
    ),
    Song(
      title: "DaDaDa",
      description: "Tanir&Tyomcha",
      url: "music/DaDaDa.mp3",
      coverUrl: "assets/images/Da Da Da.png",
    ),
    Song(
      title: "Dream",
      description: "Zoverze Remix",
      url: "music/DREAMS - LOST SKY.mp3",
      coverUrl: "assets/images/DREAMS - LOST SKY.png",
    ),
    Song(
      title: "Low",
      description: "Flo Rida",
      url: "music/Low.mp3",
      coverUrl: "assets/images/Low.png",
    ),
  ];
}
