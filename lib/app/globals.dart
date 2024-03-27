library spocify.globals;

import '../models/song.model.dart';

bool isLogin = false;
late dynamic name;
late dynamic email;
late dynamic pwd;

late dynamic currentIndexTrending;
late dynamic nextIndexTrending;
late dynamic preIndexTrending;

List<Song> songs = Song.songs;