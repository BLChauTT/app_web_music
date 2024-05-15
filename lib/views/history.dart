import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:spocify/utils/global.colors.dart';
import 'package:spocify/views/home.dart';
import 'package:spocify/views/song_screen.dart';

import '../app/globals.dart';
import '../models/song.model.dart';

class HistoryPage extends StatefulWidget {
  @override
  _HistoryPageState createState() => _HistoryPageState();
}

class _HistoryPageState extends State<HistoryPage> {

  List<Song> listHistory = [];

  @override
  void initState() {
    super.initState();
    listHistory = historyIndex.map((index) => songs[index]).toList();
  }

  @override
  void didChangeDependencies() {
    super.didChangeDependencies();
    setState(() {
      listHistory = historyIndex.map((index) => songs[index]).toList();
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
                "History",
                style: TextStyle(
                  color: GlobalColors.textColor,
                  fontWeight: FontWeight.bold,
                  fontSize: 20.0,
                ),
              ),
            ),
          ),
        ),
        body: _historyPage(),
      ),
    );
  }

  Widget _historyPage() {
    return Scaffold(
      backgroundColor: Colors.transparent,
      body: Padding(
        padding: const EdgeInsets.only(top: 8.0),
        child: Column(
          children: [
            Expanded(
              child: ListView.builder(
                itemCount: historyIndex.length,
                itemBuilder: (context, index) => InkWell(
                  onTap: (){
                    currentIndexTrending = historyIndex[index];
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
                      listHistory[index].coverUrl,
                      fit: BoxFit.cover,
                      width: 40.0,
                      height: 80.0,
                    ),
                    title: Text(
                      listHistory[index].title,
                      style: TextStyle(
                        fontWeight: FontWeight.bold,
                        color: GlobalColors.textColor.withOpacity(0.95),
                      ),
                    ),
                    subtitle: Text(
                      listHistory[index].description,
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
