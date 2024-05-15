library spocify.globals;

import 'package:spocify/Helpers/account_helpers.dart';

import '../models/song.model.dart';

var db = AccountDBHelper();

bool isLogin = false;
late dynamic name;
late dynamic email;
late dynamic pwd;

late dynamic usernameinlogin;

late dynamic currentIndexTrending;
late dynamic nextIndexTrending;
late dynamic preIndexTrending;

List<Song> songs = Song.songs;

List<int> favoriteIndex = [];
List<int> historyIndex = [];