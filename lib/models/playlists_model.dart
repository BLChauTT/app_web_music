import 'song.model.dart';

class Playlist {
  final String title;
  final List<Song> songs;
  final String imageUrl;

  Playlist({
    required this.title,
    required this.songs,
    required this.imageUrl,
  });

  static List<Playlist> playlists = [
    Playlist(
      title: "Hip-hop R&B Mix",
      songs: Song.songs,
      imageUrl: "https://cdn.alongwalk.info/vn/wp-content/uploads/2022/03/30010446/image-hip-hop-tu-am-nhac-duong-pho-den-vu-tru-nghe-thuat-164855188612165.jpg",
    ),
    Playlist(
      title: "Rock & Roll",
      songs: Song.songs,
      imageUrl: "https://www.udiscovermusic.com/wp-content/uploads/2022/01/The-Last-Waltz-GettyImages-1348760426.jpg",
    ),
    Playlist(
      title: "Techno",
      songs: Song.songs,
      imageUrl: "https://nexus.radio/wp-content/uploads/2022/05/Techno.jpg",
    ),
  ];
}
