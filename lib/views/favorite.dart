import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:spocify/app/globals.dart';
import 'package:spocify/utils/global.colors.dart';
import 'package:spocify/views/home.dart';
import 'package:spocify/views/song_screen.dart';

import '../models/song.model.dart';

class FavoritePage extends StatefulWidget {
  @override
  _FavoritePageState createState() => _FavoritePageState();
}

class _FavoritePageState extends State<FavoritePage> {
  List<Song> listFavorite = [];

  @override
  void initState() {
    super.initState();
    listFavorite = favoriteIndex.map((index) => songs[index]).toList();
  }

  @override
  void didChangeDependencies() {
    super.didChangeDependencies();
    setState(() {
      listFavorite = favoriteIndex.map((index) => songs[index]).toList();
    });
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: BoxDecoration(
        gradient: LinearGradient(
            begin: Alignment.topCenter,
            end: Alignment.bottomCenter,
            colors: [
              GlobalColors.bottomColor.withOpacity(0.7),
              GlobalColors.subBlueColor.withOpacity(0.7),
            ]),
      ),
      child: Scaffold(
        backgroundColor: Colors.transparent,
        appBar: AppBar(
          automaticallyImplyLeading: false,
          backgroundColor: Colors.transparent,
          surfaceTintColor: Colors.transparent,
          elevation: 0,
          leading: Padding(
            padding: const EdgeInsets.only(left: 20.0),
            child: IconButton(
              onPressed: () {
                Navigator.pushReplacement(
                  context,
                  MaterialPageRoute(builder: (context) => HomePage()),
                );
              },
              icon: Icon(
                Icons.arrow_back_ios,
                color: GlobalColors.textColor,
              ),
            ),
          ),
          title: Center(
            child: Padding(
              padding: const EdgeInsets.only(right: 40.0),
              child: Text(
                "Favorite",
                style: TextStyle(
                  color: GlobalColors.textColor,
                  fontWeight: FontWeight.bold,
                  fontSize: 20.0,
                ),
              ),
            ),
          ),
        ),
        body: _favoritePage(),
      ),
    );
  }

  Widget _favoritePage() {
    return Scaffold(
      backgroundColor: Colors.transparent,
      body: Padding(
        padding: const EdgeInsets.only(top: 8.0),
        child: Column(
          children: [
            Expanded(
              child: ListView.builder(
                itemCount: favoriteIndex.length,
                itemBuilder: (context, index) => InkWell(
                  onTap: (){
                    currentIndexTrending = favoriteIndex[index];
                    nextIndexTrending = currentIndexTrending + 1;
                    preIndexTrending = currentIndexTrending - 1;

                    Navigator.pushReplacement(
                      context,
                      MaterialPageRoute(
                        builder: (context) => SongScreenPage(),
                      ),
                    );
                  },
                  child: ListTile(
                    contentPadding: EdgeInsets.only(top: 8.0, left: 16.0),
                    leading: Image.asset(
                      listFavorite[index].coverUrl,
                      fit: BoxFit.cover,
                      width: 40.0,
                      height: 80.0,
                    ),
                    title: Text(
                      listFavorite[index].title,
                      style: TextStyle(
                        fontWeight: FontWeight.bold,
                        color: GlobalColors.textColor.withOpacity(0.95),
                      ),
                    ),
                    subtitle: Text(
                      listFavorite[index].description,
                      style: TextStyle(
                        color: GlobalColors.textColor.withOpacity(0.7),
                      ),
                    ),
                  ),
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
