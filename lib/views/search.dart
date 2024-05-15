import 'dart:math';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:spocify/app/globals.dart';
import 'package:spocify/utils/global.colors.dart';
import 'package:spocify/views/home.dart';
import 'package:spocify/views/song_screen.dart';

import '../models/category_model.dart';
import '../models/song.model.dart';

class SearchPage extends StatefulWidget {
  @override
  _SearchPageState createState() => _SearchPageState();
}

class _SearchPageState extends State<SearchPage> {
  List<Category> categories = Category.categories;
  List<Song> songs = Song.songs;
  List<Song> display_list = List.from(Song.songs);

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
          actions: [
            Padding(
              padding: const EdgeInsets.only(top: 12.0),
              child: Row(
                children: [
                  Container(
                    child: Row(
                      children: [
                        CircleAvatar(
                          backgroundImage:
                              Image.asset("assets/images/sagiri-1.png").image,
                          backgroundColor: GlobalColors.greyColor,
                        ),
                        const SizedBox(
                          width: 12.0,
                        ),
                        Text(
                          "Search",
                          style: TextStyle(
                            color: GlobalColors.textColor,
                            fontWeight: FontWeight.bold,
                            fontSize: 24.0,
                          ),
                        )
                      ],
                    ),
                  ),
                  SizedBox(
                    width: MediaQuery.of(context).size.width * 0.55,
                  ),
                  IconButton(
                    onPressed: () {},
                    icon: Icon(
                      CupertinoIcons.camera,
                      color: GlobalColors.textColor,
                      size: 24.0,
                    ),
                  ),
                ],
              ),
            ),
          ],
        ),
        body: _searchPage(),
      ),
    );
  }

  Widget _searchPage() {
    return Scaffold(
      backgroundColor: Colors.transparent,
      body: Padding(
        padding: const EdgeInsets.symmetric(horizontal: 12.0),
        child: Column(
          children: [
            Padding(
              padding: const EdgeInsets.only(
                top: 24.0,
              ),
              child: TextFormField(
                onChanged: (value) => updateList(value),
                decoration: InputDecoration(
                  border: OutlineInputBorder(
                    borderRadius: BorderRadius.circular(4.0),
                    borderSide: BorderSide.none,
                  ),
                  prefixIcon: Icon(
                    Icons.search,
                    color: Colors.black.withOpacity(0.8),
                    size: 32.0,
                  ),
                  isDense: true,
                  filled: true,
                  fillColor: GlobalColors.whiteColor,
                  hintText: "What do you want to listen to?",
                  hintStyle: TextStyle(
                    color: Colors.black.withOpacity(0.8),
                  ),
                ),
              ),
            ),
            const SizedBox(
              height: 32.0,
            ),
            Expanded(
              child: display_list.length == 0
                  ? Center(
                      child: Text(
                        "Not found music...",
                        style: TextStyle(
                          color: GlobalColors.textColor.withOpacity(0.7),
                          fontWeight: FontWeight.bold,
                          fontSize: 24.0,
                        ),
                      ),
                    )
                  : ListView.builder(
                      itemCount: display_list.length,
                      itemBuilder: (context, index) => InkWell(
                        onTap: () {
                          currentIndexTrending = index;
                          nextIndexTrending = currentIndexTrending + 1;
                          preIndexTrending = currentIndexTrending - 1;

                          Navigator.push(
                            context,
                            MaterialPageRoute(
                              builder: (context) => SongScreenPage(),
                            ),
                          );
                        },
                        child: ListTile(
                          contentPadding: EdgeInsets.all(8.0),
                          leading: Image.asset(
                            display_list[index].coverUrl,
                            fit: BoxFit.cover,
                            width: 40.0,
                            height: 80.0,
                          ),
                          title: Text(
                            display_list[index].title,
                            style: TextStyle(
                              fontWeight: FontWeight.bold,
                              color: GlobalColors.textColor.withOpacity(0.95),
                            ),
                          ),
                          subtitle: Text(
                            display_list[index].description,
                            style: TextStyle(
                              color: GlobalColors.textColor.withOpacity(0.7),
                            ),
                          ),
                        ),
                      ),
                    ),
            ),
            // GridView.builder(
            //   shrinkWrap: true,
            //   physics: NeverScrollableScrollPhysics(),
            //   gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
            //     childAspectRatio: MediaQuery.of(context).size.width /
            //         300,
            //     crossAxisCount: 2,
            //     crossAxisSpacing: 4.0,
            //     mainAxisSpacing: 4.0,
            //   ),
            //   itemBuilder: (BuildContext context, int index) {
            //     final color = Color.fromRGBO(
            //       Random().nextInt(200), // Red
            //       Random().nextInt(200), // Green
            //       Random().nextInt(200), // Blue
            //       1, // Opacity
            //     );
            //     return Padding(
            //       padding: const EdgeInsets.all(4.0),
            //       child: Container(
            //         decoration: BoxDecoration(
            //           borderRadius: BorderRadius.circular(8.0),
            //           color: color,
            //         ),
            //         child: Row(
            //           // mainAxisAlignment: MainAxisAlignment.spaceAround,
            //           children: <Widget>[
            //             Padding(
            //               padding: const EdgeInsets.all(16.0),
            //               child: Text(
            //                 categories[index].title,
            //                 style: TextStyle(
            //                   color: GlobalColors.textColor,
            //                   fontWeight: FontWeight.bold,
            //                   fontSize: 16.0,
            //                 ),
            //               ),
            //             ),
            //             Flexible(
            //               child: Align(
            //                 alignment: Alignment.centerRight,
            //                 child: Transform.rotate(
            //
            //                   angle: pi / 6,
            //                   child: Container(
            //                     height: 80,
            //                    width: 60,
            //                    decoration: BoxDecoration(
            //                      image: DecorationImage(
            //                          alignment: FractionalOffset.topCenter,
            //                        image: AssetImage(categories[index].coverUrl),
            //                        fit: BoxFit.fill
            //                      )
            //                    ),
            //                   ),
            //                 ),
            //               ),
            //             ),
            //           ],
            //         ),
            //       ),
            //
            //     );
            //   },
            //   itemCount: categories.length,
            // ),
          ],
        ),
      ),
    );
  }

  void updateList(value) {
    setState(() {
      display_list = Song.songs
          .where((_element) =>
              _element.title.toLowerCase().contains(value.toString()))
          .toList();
    });
  }
}
