import 'dart:async';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:spocify/models/playlists_model.dart';
import 'package:spocify/models/song.model.dart';
import 'package:spocify/utils/global.colors.dart';
import 'package:spocify/views/login.dart';
import 'package:spocify/app/globals.dart' as globals;
import 'package:spocify/views/song_screen.dart';

import '../app/globals.dart';

class HomePage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return HomePageState();
  }
}

class HomePageState extends State<HomePage> {
  List<Song> songs = Song.songs;
  List<Playlist> playlists = Playlist.playlists;

  @override
  void initState() {}

  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: BoxDecoration(
        gradient: LinearGradient(
            begin: Alignment.topCenter,
            end: Alignment.bottomCenter,
            colors: [
              GlobalColors.bottomColor.withOpacity(0.8),
              GlobalColors.subBlueColor.withOpacity(0.8),
            ]),
      ),
      child: Scaffold(
        backgroundColor: Colors.transparent,
        appBar: AppBar(
          backgroundColor: Colors.transparent,
          automaticallyImplyLeading: false,
          elevation: 0,
          leading: const Icon(Icons.grid_view_rounded),
          actions: [
            Container(
              margin: const EdgeInsets.only(right: 20),
              child: CircleAvatar(
                backgroundImage:
                    Image.asset("assets/images/sagiri-1.png").image,
                backgroundColor: GlobalColors.greyColor,
              ),
            ),
          ],
        ),
        body: SingleChildScrollView(
          child: Column(
            children: [
              Padding(
                padding: const EdgeInsets.all(20),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text(
                      "Welcome",
                      style: TextStyle(
                        color: GlobalColors.whiteColor,
                        fontSize: 16,
                        fontWeight: FontWeight.w500,
                      ),
                    ),
                    const SizedBox(
                      height: 5,
                    ),
                    Text(
                      "Enjoy your favorite music",
                      style: TextStyle(
                        color: GlobalColors.whiteColor,
                        fontWeight: FontWeight.bold,
                        fontSize: 21,
                      ),
                    ),
                    const SizedBox(
                      height: 20,
                    ),
                    TextFormField(
                      decoration: InputDecoration(
                        border: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(15.0),
                          borderSide: BorderSide.none,
                        ),
                        prefixIcon: Icon(
                          Icons.search,
                          color: Colors.grey.shade400,
                        ),
                        isDense: true,
                        filled: true,
                        fillColor: GlobalColors.whiteColor,
                        hintText: "Search...",
                        hintStyle: TextStyle(
                          color: Colors.grey.shade400,
                        ),
                      ),
                    ),
                  ],
                ),
              ),
              Padding(
                padding: const EdgeInsets.only(
                  left: 20.0,
                  top: 20.0,
                  bottom: 20.0,
                ),
                child: Column(
                  children: [
                    Padding(
                      padding: const EdgeInsets.only(
                        right: 20.0,
                      ),
                      child: Row(
                        mainAxisAlignment: MainAxisAlignment.spaceBetween,
                        children: [
                          Text(
                            "Trending Music",
                            style:
                                Theme.of(context).textTheme.headline6!.copyWith(
                                      color: GlobalColors.whiteColor,
                                      fontWeight: FontWeight.bold,
                                    ),
                          ),
                          Text(
                            "View More",
                            style:
                                Theme.of(context).textTheme.bodySmall!.copyWith(
                                      color: GlobalColors.whiteColor,
                                    ),
                          ),
                        ],
                      ),
                    ),
                    const SizedBox(
                      height: 20,
                    ),
                    SizedBox(
                      height: MediaQuery.of(context).size.height * 0.27,
                      child: ListView.builder(
                        scrollDirection: Axis.horizontal,
                        itemCount: songs.length,
                        itemBuilder: (context, index) {
                          return Container(
                            margin: const EdgeInsets.only(
                              right: 10,
                            ),
                            child: Stack(
                              alignment: Alignment.bottomCenter,
                              children: [
                                Container(
                                  width:
                                      MediaQuery.of(context).size.width * 0.45,
                                  decoration: BoxDecoration(
                                    borderRadius: BorderRadius.circular(15.0),
                                    image: DecorationImage(
                                      image: AssetImage(
                                        songs[index].coverUrl,
                                      ),
                                      fit: BoxFit.cover,
                                    ),
                                  ),
                                ),
                                Container(
                                  height: 50,
                                  width:
                                      MediaQuery.of(context).size.width * 0.37,
                                  margin: const EdgeInsets.only(
                                    bottom: 10,
                                  ),
                                  decoration: BoxDecoration(
                                    borderRadius: BorderRadius.circular(15.0),
                                    color: GlobalColors.whiteColor
                                        .withOpacity(0.8),
                                  ),
                                  child: Row(
                                    mainAxisAlignment:
                                        MainAxisAlignment.spaceAround,
                                    children: [
                                      Column(
                                        crossAxisAlignment:
                                            CrossAxisAlignment.start,
                                        mainAxisAlignment:
                                            MainAxisAlignment.center,
                                        children: [
                                          Text(
                                            songs[index].title,
                                            style: Theme.of(context)
                                                .textTheme
                                                .bodyLarge!
                                                .copyWith(
                                                  color: GlobalColors.greyColor.withOpacity(1.0),
                                                  fontWeight: FontWeight.bold,
                                                ),
                                          ),
                                          Text(
                                            songs[index].description,
                                            style: Theme.of(context)
                                                .textTheme
                                                .bodySmall!
                                                .copyWith(
                                                  color: GlobalColors.greyColor.withOpacity(0.4),
                                                  fontWeight: FontWeight.bold,
                                                ),
                                          ),
                                        ],
                                      ),
                                      InkWell(
                                        onTap: (){
                                          globals.currentIndexTrending = index;
                                          nextIndexTrending = currentIndexTrending + 1;
                                          preIndexTrending = currentIndexTrending - 1;

                                          print(currentIndexTrending);
                                          print(nextIndexTrending);
                                          print(preIndexTrending);

                                          Navigator.push(
                                            context,
                                            MaterialPageRoute(
                                              builder: (context) => SongScreenPage(),
                                            ),
                                          );
                                        },
                                        child: Icon(
                                          Icons.play_circle,
                                          color: GlobalColors.mainColor,
                                        ),
                                      ),
                                    ],
                                  ),
                                ),
                              ],
                            ),
                          );
                        },
                      ),
                    ),
                  ],
                ),
              ),
              Padding(
                padding: const EdgeInsets.only(
                  left: 20.0,
                  top: 10.0,
                  bottom: 10.0,
                ),
                child: Column(
                  children: [
                    Padding(
                      padding: const EdgeInsets.only(
                        right: 20.0,
                      ),
                      child: Row(
                        mainAxisAlignment: MainAxisAlignment.spaceBetween,
                        children: [
                          Text(
                            "Playlists",
                            style:
                                Theme.of(context).textTheme.headline6!.copyWith(
                                      color: GlobalColors.whiteColor,
                                      fontWeight: FontWeight.bold,
                                    ),
                          ),
                          Text(
                            "View More",
                            style:
                                Theme.of(context).textTheme.bodySmall!.copyWith(
                                      color: GlobalColors.whiteColor,
                                    ),
                          ),
                        ],
                      ),
                    ),
                    const SizedBox(
                      height: 20,
                    ),
                    ListView.builder(
                      shrinkWrap: true,
                      physics: NeverScrollableScrollPhysics(),
                      itemCount: playlists.length,
                      itemBuilder: ((context, index) {
                        return InkWell(
                          onTap: (){
                            //nghe nhac
                          },
                          child: Container(
                            height: 75,
                            margin: const EdgeInsets.only(bottom: 10, right: 20),
                            padding: const EdgeInsets.symmetric(horizontal: 20),
                            decoration: BoxDecoration(
                              color: GlobalColors.mainColor.withOpacity(0.6),
                              borderRadius: BorderRadius.circular(15.0),
                            ),
                            child: Row(
                              mainAxisAlignment: MainAxisAlignment.spaceAround,
                              children: [
                                ClipRRect(
                                  borderRadius: BorderRadius.circular(15.0),
                                  child: Image.network(
                                    playlists[index].imageUrl,
                                    height: 50,
                                    width: 50,
                                    fit: BoxFit.cover,
                                  ),
                                ),
                                const SizedBox(
                                  width: 20,
                                ),
                                Expanded(
                                  child: Column(
                                    crossAxisAlignment: CrossAxisAlignment.start,
                                    mainAxisAlignment: MainAxisAlignment.center,
                                    children: [
                                      Text(
                                        playlists[index].title,
                                        style: Theme.of(context)
                                            .textTheme
                                            .bodyLarge!
                                            .copyWith(
                                              fontWeight: FontWeight.bold,
                                            ),
                                      ),
                                      Text(
                                        "${playlists[index].songs.length} songs",
                                        maxLines: 2,
                                        style:
                                            Theme.of(context).textTheme.bodySmall,
                                      ),
                                    ],
                                  ),
                                ),
                                IconButton(
                                  onPressed: () {},
                                  icon: Icon(
                                    Icons.play_circle,
                                    color: GlobalColors.whiteColor,
                                  ),
                                ),
                              ],
                            ),
                          ),
                        );
                      }),
                    ),
                  ],
                ),
              ),
            ],
          ),
        ),
        bottomNavigationBar: BottomNavigationBar(
          type: BottomNavigationBarType.fixed,
          backgroundColor: GlobalColors.bottomColor,
          unselectedItemColor: GlobalColors.blackColor,
          selectedItemColor: GlobalColors.blackColor,
          showUnselectedLabels: false,
          showSelectedLabels: false,
          items: const [
            BottomNavigationBarItem(
              icon: Icon(Icons.home),
              label: "Home",
            ),
            BottomNavigationBarItem(
              icon: Icon(Icons.search),
              label: "Search",
            ),
            BottomNavigationBarItem(
              icon: Icon(Icons.library_add),
              label: "Library",
            ),
            BottomNavigationBarItem(
              icon: Icon(Icons.people_outline),
              label: "Profile",
            ),
          ],
        ),
      ),
    );
  }
}
