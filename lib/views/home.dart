import 'dart:async';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:spocify/models/playlists_model.dart';
import 'package:spocify/models/song.model.dart';
import 'package:spocify/utils/global.colors.dart';
import 'package:spocify/views/account_details.dart';
import 'package:spocify/views/album.dart';
import 'package:spocify/views/favorite.dart';
import 'package:spocify/views/history.dart';
import 'package:spocify/views/login.dart';
import 'package:spocify/app/globals.dart' as globals;
import 'package:spocify/views/playlist_screen.dart';
import 'package:spocify/views/premium.dart';
import 'package:spocify/views/profile.dart';
import 'package:spocify/views/search.dart';
import 'package:spocify/views/settings.dart';
import 'package:spocify/views/song_screen.dart';
import 'package:spocify/views/testaudio_screen.dart';

import '../app/globals.dart';
import 'about.dart';

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
          surfaceTintColor: Colors.transparent,
          automaticallyImplyLeading: false,
          elevation: 0,
          leading: Builder(
            builder: (BuildContext context) {
              return IconButton(
                icon: const Icon(Icons.grid_view_rounded),
                onPressed: () {
                  Scaffold.of(context).openDrawer();
                },
              );
            },
          ),
          actions: [
            Container(
              margin: const EdgeInsets.only(right: 20),
              child: Row(
                children: [
                  CircleAvatar(
                    backgroundImage:
                        Image.asset("assets/images/sagiri-1.png").image,
                    backgroundColor: GlobalColors.greyColor,
                  ),
                ],
              ),
            ),
            // IconButton(
            //     onPressed: (){
            //       Navigator.push(
            //         context,
            //         MaterialPageRoute(builder: (context) =>  SimpleExampleApp()),
            //       );
            //     }, icon: Icon(Icons.add))
          ],
        ),
        drawer: Drawer(
          backgroundColor: GlobalColors.greyColor,
          child: Padding(
            padding: const EdgeInsets.only(right: 15.0, left: 10.0, top: 55.0),
            child: ListView(
              children: <Widget>[
                ExpansionTile(
                  shape: const Border(),
                  title: GestureDetector(
                    onTap: () {
                      Navigator.push(
                        context,
                        MaterialPageRoute(builder: (context) => SearchPage()),
                      );
                    },
                    child: Text(
                      'Search',
                      style: TextStyle(
                        color: GlobalColors.textColor,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                  ),
                  leading: Icon(
                    Icons.search,
                    color: GlobalColors.textColor,
                  ),
                  trailing: const Icon(
                    Icons.flash_on,
                    size: 0,
                  ),
                ),
                ExpansionTile(
                  shape: const Border(),
                  title: Text(
                    'Personal',
                    style: TextStyle(
                      color: GlobalColors.textColor,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                  leading: Icon(
                    Icons.account_circle,
                    color: GlobalColors.textColor,
                  ),
                  trailing: Icon(
                    CupertinoIcons.plus_app,
                    color: GlobalColors.greyColor,
                    size: 0,
                  ),
                  textColor: GlobalColors.textColor,
                  children: <Widget>[
                    Padding(
                      padding: const EdgeInsets.only(left: 35.0),
                      child: Column(
                        children: [
                          GestureDetector(
                            child: ListTile(
                              title: Text(
                                'View Profile',
                                style: TextStyle(
                                  color: GlobalColors.textColor,
                                ),
                              ),
                            ),
                            onTap: () {
                              Navigator.push(
                                context,
                                MaterialPageRoute(
                                    builder: (context) => ProfilePage()),
                              );
                            },
                          ),
                          ListTile(
                            onTap: () {
                              Navigator.push(
                                context,
                                MaterialPageRoute(
                                    builder: (context) => AccountDetailsPage()),
                              );
                            },
                            title: Text(
                              'Account Details',
                              style: TextStyle(
                                color: GlobalColors.textColor,
                              ),
                            ),
                          ),
                        ],
                      ),
                    ),
                  ],
                ),
                ExpansionTile(
                  shape: const Border(),
                  title: Text(
                    'Library',
                    style: TextStyle(
                      color: GlobalColors.textColor,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                  leading: Icon(
                    Icons.library_music_sharp,
                    color: GlobalColors.textColor,
                  ),
                  trailing: Icon(
                    CupertinoIcons.plus_app,
                    color: GlobalColors.greyColor,
                    size: 0,
                  ),
                  children: <Widget>[
                    Padding(
                      padding: const EdgeInsets.only(left: 35.0),
                      child: Column(
                        children: [
                          ListTile(
                            onTap: (){
                              Navigator.pushReplacement(
                                context,
                                MaterialPageRoute(builder: (context) => HistoryPage()),
                              );
                            },
                            title: Text(
                              'Listening history',
                              style: TextStyle(
                                color: GlobalColors.textColor,
                              ),
                            ),
                          ),
                          ListTile(
                            onTap: (){
                              Navigator.pushReplacement(
                                context,
                                MaterialPageRoute(builder: (context) => FavoritePage()),
                              );
                            },
                            title: Text(
                              'Favorite',
                              style: TextStyle(
                                color: GlobalColors.textColor,
                              ),
                            ),
                          ),
                          ListTile(
                            onTap: (){
                              Navigator.pushReplacement(
                                context,
                                MaterialPageRoute(builder: (context) => AlbumPage()),
                              );
                            },
                            title: Text(
                              'Album',
                              style: TextStyle(
                                color: GlobalColors.textColor,
                              ),
                            ),
                          ),
                        ],
                      ),
                    ),
                  ],
                ),
                ExpansionTile(
                  shape: const Border(),
                  title: Text(
                    'Settings and privacy',
                    style: TextStyle(
                      color: GlobalColors.textColor,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                  leading: Icon(
                    Icons.settings,
                    color: GlobalColors.textColor,
                  ),
                  trailing: Icon(
                    CupertinoIcons.plus_app,
                    color: GlobalColors.greyColor,
                    size: 0,
                  ),
                  children: <Widget>[
                    Padding(
                      padding: const EdgeInsets.only(left: 35.0),
                      child: Column(
                        children: [
                          ListTile(
                            onTap: (){
                              Navigator.pushReplacement(
                                context,
                                MaterialPageRoute(builder: (context) => SettingsPage()),
                              );
                            },
                            title: Text(
                              'Settings',
                              style: TextStyle(
                                color: GlobalColors.textColor,
                              ),
                            ),
                          ),
                          ListTile(
                            title: Text(
                              'Privacy',
                              style: TextStyle(
                                color: GlobalColors.textColor,
                              ),
                            ),
                          ),
                          ListTile(
                            onTap: (){
                              Navigator.pushReplacement(
                                context,
                                MaterialPageRoute(builder: (context) => AboutPage()),
                              );
                            },
                            title: Text(
                              'About',
                              style: TextStyle(
                                color: GlobalColors.textColor,
                              ),
                            ),
                          ),
                          ListTile(
                            onTap: (){
                              favoriteIndex.clear();
                              print(favoriteIndex);
                              Navigator.pushAndRemoveUntil(
                                context,
                                MaterialPageRoute(builder: (context) => LoginPage()),
                                    (Route<dynamic> route) => false,
                              );

                            },
                            title: Text(
                              'Log out',
                              style: TextStyle(
                                color: GlobalColors.textColor,
                              ),
                            ),
                          ),
                        ],
                      ),
                    ),
                  ],
                ),
                ExpansionTile(
                  shape: const Border(),
                  title: GestureDetector(
                    onTap: () {
                      Navigator.push(
                        context,
                        MaterialPageRoute(builder: (context) => PremiumPage()),
                      );
                    },
                    child: Text(
                      'Premium',
                      style: TextStyle(
                        color: GlobalColors.textColor,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                  ),
                  leading: Icon(
                    Icons.flash_on,
                    color: GlobalColors.textColor,
                  ),
                  trailing: const Icon(
                    Icons.flash_on,
                    size: 0,
                  ),
                ),
              ],
            ),
          ),
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
                    SingleChildScrollView(
                      scrollDirection: Axis.horizontal,
                      child: Row(
                        children: [
                          InkWell(
                            onTap: (){
                              Navigator.push(
                                context,
                                MaterialPageRoute(builder: (context) => SearchPage()),
                              );
                            },
                            child: Card(
                              color: GlobalColors.greyColor,
                              child: Padding(
                                padding: const EdgeInsets.all(16.0),
                                child: Row(
                                  children: [
                                    Icon(
                                      Icons.search,
                                      color: GlobalColors.textColor,
                                    ),
                                    const SizedBox(
                                      width: 8.0,
                                    ),
                                    Text(
                                      "Search",
                                      style: TextStyle(
                                        color: GlobalColors.textColor,
                                        fontSize: 16.0,
                                      ),
                                    )
                                  ],
                                ),
                              ),
                            ),
                          ),
                          InkWell(
                            onTap: (){
                              Navigator.push(
                                context,
                                MaterialPageRoute(builder: (context) => FavoritePage()),
                              );
                            },
                            child: Card(
                              color: GlobalColors.greyColor,
                              child: Padding(
                                padding: const EdgeInsets.all(16.0),
                                child: Row(
                                  children: [
                                    Icon(
                                      Icons.star,
                                      color: GlobalColors.textColor,
                                    ),
                                    const SizedBox(
                                      width: 8.0,
                                    ),
                                    Text(
                                      "Favorite",
                                      style: TextStyle(
                                        color: GlobalColors.textColor,
                                        fontSize: 16.0,
                                      ),
                                    )
                                  ],
                                ),
                              ),
                            ),
                          ),
                          InkWell(
                            onTap: (){
                              Navigator.push(
                                context,
                                MaterialPageRoute(builder: (context) => HistoryPage()),
                              );
                            },
                            child: Card(
                              color: GlobalColors.greyColor,
                              child: Padding(
                                padding: const EdgeInsets.all(16.0),
                                child: Row(
                                  children: [
                                    Icon(
                                      Icons.history_sharp,
                                      color: GlobalColors.textColor,
                                    ),
                                    const SizedBox(
                                      width: 8.0,
                                    ),
                                    Text(
                                      "History",
                                      style: TextStyle(
                                        color: GlobalColors.textColor,
                                        fontSize: 16.0,
                                      ),
                                    )
                                  ],
                                ),
                              ),
                            ),
                          ),
                          InkWell(
                            onTap: (){
                              Navigator.push(
                                context,
                                MaterialPageRoute(builder: (context) => AlbumPage()),
                              );
                            },
                            child: Card(
                              color: GlobalColors.greyColor,
                              child: Padding(
                                padding: const EdgeInsets.all(16.0),
                                child: Row(
                                  children: [
                                    Icon(
                                      Icons.album,
                                      color: GlobalColors.textColor,
                                    ),
                                    const SizedBox(
                                      width: 8.0,
                                    ),
                                    Text(
                                      "Album",
                                      style: TextStyle(
                                        color: GlobalColors.textColor,
                                        fontSize: 16.0,
                                      ),
                                    )
                                  ],
                                ),
                              ),
                            ),
                          ),
                          InkWell(
                            onTap: (){
                              Navigator.push(
                                context,
                                MaterialPageRoute(builder: (context) => SettingsPage()),
                              );
                            },
                            child: Card(
                              color: GlobalColors.greyColor,
                              child: Padding(
                                padding: const EdgeInsets.all(16.0),
                                child: Row(
                                  children: [
                                    Icon(
                                      Icons.settings,
                                      color: GlobalColors.textColor,
                                    ),
                                    const SizedBox(
                                      width: 8.0,
                                    ),
                                    Text(
                                      "Settings",
                                      style: TextStyle(
                                        color: GlobalColors.textColor,
                                        fontSize: 16.0,
                                      ),
                                    )
                                  ],
                                ),
                              ),
                            ),
                          ),
                        ],
                      ),
                    ),
                  ],
                ),
              ),
              Padding(
                padding: const EdgeInsets.only(
                  left: 20.0,
                  // top: 20.0,
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
                          InkWell(
                            onTap: (){
                              Navigator.push(
                                context,
                                MaterialPageRoute(
                                  builder: (context) => SearchPage(),
                                ),
                              );
                            },
                            child: Text(
                              "View More",
                              style:
                                  Theme.of(context).textTheme.bodySmall!.copyWith(
                                        color: GlobalColors.whiteColor,
                                      ),
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
                                  width: MediaQuery.of(context).size.width * 0.37,
                                  margin: const EdgeInsets.only(
                                    bottom: 10,
                                  ),
                                  decoration: BoxDecoration(
                                    borderRadius: BorderRadius.circular(15.0),
                                    color: GlobalColors.whiteColor.withOpacity(0.8),
                                  ),
                                  child: Row(
                                    mainAxisAlignment: MainAxisAlignment.spaceAround,
                                    children: [
                                      Flexible(
                                        child: Column(
                                          crossAxisAlignment: CrossAxisAlignment.start,
                                          mainAxisAlignment: MainAxisAlignment.center,
                                          children: [
                                            Text(
                                              songs[index].title,
                                              style: Theme.of(context).textTheme.bodyLarge!.copyWith(
                                                color: GlobalColors.greyColor.withOpacity(1.0),
                                                fontWeight: FontWeight.bold,
                                                fontSize: 16.0,
                                              ),
                                              overflow: TextOverflow.ellipsis,
                                            ),
                                            Text(
                                              songs[index].description,
                                              style: Theme.of(context).textTheme.bodySmall!.copyWith(
                                                color: GlobalColors.greyColor.withOpacity(0.4),
                                                fontWeight: FontWeight.bold,
                                              ),
                                              overflow: TextOverflow.ellipsis,
                                            ),
                                          ],
                                        ),
                                      ),
                                      InkWell(
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
                          onTap: () {
                            //nghe nhac
                          },
                          child: Container(
                            height: 75,
                            margin:
                                const EdgeInsets.only(bottom: 10, right: 20),
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
                                    crossAxisAlignment:
                                        CrossAxisAlignment.start,
                                    mainAxisAlignment: MainAxisAlignment.center,
                                    children: [
                                      Text(
                                        playlists[index].title,
                                        style: Theme.of(context)
                                            .textTheme
                                            .bodyLarge!
                                            .copyWith(
                                              fontWeight: FontWeight.bold,
                                              color: GlobalColors.textColor,
                                            ),
                                      ),
                                      Text(
                                        "${playlists[index].songs.length} songs",
                                        maxLines: 2,
                                        style: Theme.of(context)
                                            .textTheme
                                            .bodySmall!
                                            .copyWith(
                                              color: GlobalColors.textColor,
                                            ),
                                      ),
                                    ],
                                  ),
                                ),
                                IconButton(
                                  onPressed: () {},
                                  icon: InkWell(
                                    onTap: () {
                                      Navigator.push(
                                        context,
                                        MaterialPageRoute(
                                          builder: (context) => PlayListPage(),
                                        ),
                                      );
                                    },
                                    child: Icon(
                                      Icons.play_circle,
                                      color: GlobalColors.whiteColor,
                                    ),
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
        // bottomNavigationBar: BottomNavigationBar(
        //   type: BottomNavigationBarType.fixed,
        //   backgroundColor: GlobalColors.bottomColor,
        //   unselectedItemColor: GlobalColors.blackColor,
        //   selectedItemColor: GlobalColors.textColor,
        //   showUnselectedLabels: false,
        //   showSelectedLabels: false,
        //   items: const [
        //     BottomNavigationBarItem(
        //       icon: Icon(Icons.home),
        //       label: "Home",
        //     ),
        //     // BottomNavigationBarItem(
        //     //   icon: Icon(Icons.search),
        //     //   label: "Search",
        //     // ),
        //     BottomNavigationBarItem(
        //       icon: Icon(Icons.library_add),
        //       label: "Library",
        //     ),
        //     BottomNavigationBarItem(
        //       icon: Icon(Icons.people_outline),
        //       label: "Profile",
        //     ),
        //   ],
        // ),
      ),
    );
  }
}
