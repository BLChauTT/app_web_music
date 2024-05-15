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
    Song(
      title: "Âm Thầm Bên Em",
      description: "Sơn Tùng MTP",
      url: "music/amthambenem.mp3",
      coverUrl: "assets/images/amthambenem.jpg",
    ),
    Song(
      title: "Attention",
      description: "Charlie Puth",
      url: "music/attention.mp3",
      coverUrl: "assets/images/attention.jpg",
    ),
    Song(
      title: "Chúng Ta Của Hiện Tại",
      description: "Sơn Tùng MTP",
      url: "music/chungtacuahientai.mp3",
      coverUrl: "assets/images/chungtacuahientai.jpg",
    ),
    Song(
      title: "Chúng Ta Của Tương Lai",
      description: "Sơn Tùng MTP",
      url: "music/chungtacuatuonglai.mp3",
      coverUrl: "assets/images/chungtacuatuonglai.jpg",
    ),
    Song(
      title: "Dark Horse",
      description: "Katty Perry",
      url: "music/darkhorse.mp3",
      coverUrl: "assets/images/darkhorse.jpg",
    ),
    Song(
      title: "Muộn Rồi Sao Mà Còn",
      description: "Sơn Tùng MTP",
      url: "music/muonroisaomacon.mp3",
      coverUrl: "assets/images/muonroimasaocon.jpg",
    ),
    Song(
      title: "Nơi Này Có Anh",
      description: "Sơn Tùng MTP",
      url: "music/noinaycoanh.mp3",
      coverUrl: "assets/images/noinaycoanh.jpg",
    ),
    Song(
      title: "Nhật Ký Của Mẹ",
      description: "Hiền Thục",
      url: "music/nhatkycuame.mp3",
      coverUrl: "assets/images/nhatkycuame.jpg",
    ),
    Song(
      title: "Roar",
      description: "Katty Kerry",
      url: "music/roar.mp3",
      coverUrl: "assets/images/roar.jpg",
    ),
    Song(
      title: "Attention",
      description: "Charlie Puth",
      url: "music/seeyouagain.mp3",
      coverUrl: "assets/images/seeyouagain.jpg",
    ),
    Song(
      title: "We Don't Talk Anymore",
      description: "Nine Track Mind",
      url: "music/wdtam.mp3",
      coverUrl: "assets/images/wedonttalkanymore.jpg",
    ),

  ];
}
