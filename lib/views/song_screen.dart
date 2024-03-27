import 'package:audioplayers/audioplayers.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:rx/constructors.dart';
import 'package:spocify/app/globals.dart';
import 'package:spocify/extensions_by_me/format_time.dart';

import '../models/song.model.dart';
import '../utils/global.colors.dart';

class SongScreenPage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return SongScreenPageState();
  }
}

class SongScreenPageState extends State<SongScreenPage> {
  List<Song> songs = Song.songs;

  final audioPlayer = AudioPlayer();
  bool isPlaying = false;
  Duration duration = Duration.zero;
  Duration position = Duration.zero;

  @override
  void initState() {
    super.initState();

    setAudio();

    //Bắt sự kiện nghe nhạc, dừng, kết thúc
    audioPlayer.onPlayerStateChanged.listen((state) {
      setState(() {
        isPlaying = state == PlayerState.playing;
      });
    });

    audioPlayer.onDurationChanged.listen((newDuration) {
      setState(() {
        duration = newDuration;
      });
    });

    audioPlayer.onPositionChanged.listen((newPosition) {
      setState(() {
        position = newPosition;
      });
    });
  }

  Future setAudio() async {
    audioPlayer.setReleaseMode(ReleaseMode.loop);
    audioPlayer.play(AssetSource(songs[currentIndexTrending].url));
  }

  @override
  void dispose() {
    super.dispose();
  }

  @override
  Widget build(BuildContext context) => Scaffold(
        appBar: AppBar(
          backgroundColor: Colors.transparent,
          automaticallyImplyLeading: false,
          elevation: 0,
        ),
        extendBodyBehindAppBar: true,
        body: Stack(
          fit: StackFit.expand,
          children: [
            Image.asset(
              songs[currentIndexTrending].coverUrl,
              width: double.infinity,
              fit: BoxFit.cover,
            ),
            ShaderMask(
              shaderCallback: (rect) {
                return LinearGradient(
                  begin: Alignment.topCenter,
                  end: Alignment.bottomCenter,
                  colors: [
                    GlobalColors.whiteColor,
                    GlobalColors.whiteColor.withOpacity(0.5),
                    GlobalColors.whiteColor.withOpacity(0.0),
                  ],
                  stops: const [0.0, 0.4, 0.6],
                ).createShader(rect);
              },
              blendMode: BlendMode.dstOut,
              child: Container(
                decoration: BoxDecoration(
                  gradient: LinearGradient(
                      begin: Alignment.topCenter,
                      end: Alignment.bottomCenter,
                      colors: [
                        GlobalColors.bottomColor,
                        GlobalColors.mainColor.withOpacity(0.9),
                      ]),
                ),
              ),
            ),
            Padding(
              padding: const EdgeInsets.only(bottom: 20.0),
              child: Column(
                mainAxisAlignment: MainAxisAlignment.end,
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  const SizedBox(
                    height: 20,
                  ),
                  Padding(
                    padding: const EdgeInsets.only(
                      left: 12.0,
                      bottom: 45.0,
                    ),
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(
                          songs[currentIndexTrending].title,
                          style: TextStyle(
                            fontWeight: FontWeight.bold,
                            fontSize: 32,
                            color: GlobalColors.textColor,
                          ),
                        ),
                        const SizedBox(
                          height: 2,
                        ),
                        Padding(
                          padding: const EdgeInsets.only(left: 2.0),
                          child: Text(
                            songs[currentIndexTrending].description,
                            style: TextStyle(
                              fontSize: 22,
                              color: GlobalColors.textColor,
                            ),
                          ),
                        ),
                      ],
                    ),
                  ),
                  Padding(
                    padding: const EdgeInsets.symmetric(
                      horizontal: 16,
                    ),
                    child: Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: [
                        Text(
                          formatTime(position),
                          style: TextStyle(
                            fontSize: 16,
                            color: GlobalColors.textColor,
                          ),
                        ),
                        Text(
                          formatTime(duration - position),
                          style: TextStyle(
                            fontSize: 16,
                            color: GlobalColors.textColor,
                          ),
                        ),
                      ],
                    ),
                  ),
                  Padding(
                    padding: const EdgeInsets.symmetric(
                      horizontal: 14.0,
                    ),
                    child: Slider(
                      activeColor: GlobalColors.textColor,
                      inactiveColor: GlobalColors.textColor.withOpacity(0.3),
                      min: 0.0,
                      max: duration.inSeconds.toDouble(),
                      value: position.inSeconds.toDouble(),
                      onChanged: (value) async {
                        final position = Duration(seconds: value.toInt());
                        await audioPlayer.seek(position);

                        //TH neu audio bi pause
                        await audioPlayer.resume();
                      },
                    ),
                  ),
                  Padding(
                    padding: const EdgeInsets.all(10.0),
                    child: Row(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                        IconButton(
                          onPressed: () async {
                            if (currentIndexTrending > 0) {
                              currentIndexTrending -= 1;
                              nextIndexTrending = currentIndexTrending + 1;
                              preIndexTrending = currentIndexTrending - 1;
                              await audioPlayer.stop();
                              Navigator.pushReplacement(
                                context,
                                MaterialPageRoute(
                                  builder: (context) => SongScreenPage(),
                                ),
                              );
                            } else {}
                          },
                          icon: const Icon(Icons.skip_previous),
                          iconSize: 45.0,
                          color: GlobalColors.whiteColor,
                        ),
                        Padding(
                          padding: const EdgeInsets.only(
                            right: 15.0,
                            left: 15.0,
                          ),
                          child: CircleAvatar(
                            radius: 35,
                            child: IconButton(
                              icon: Icon(
                                isPlaying ? Icons.pause : Icons.play_arrow,
                              ),
                              iconSize: 50,
                              color: GlobalColors.mainColor,
                              onPressed: () async {
                                if (isPlaying) {
                                  await audioPlayer.pause();
                                } else {
                                  await audioPlayer.resume();
                                }
                              },
                            ),
                          ),
                        ),
                        IconButton(
                          onPressed: () async {
                            if (currentIndexTrending < songs.length - 1) {
                              currentIndexTrending += 1;
                              nextIndexTrending = currentIndexTrending + 1;
                              preIndexTrending = currentIndexTrending - 1;
                              await audioPlayer.stop();
                              Navigator.pushReplacement(
                                context,
                                MaterialPageRoute(
                                  builder: (context) => SongScreenPage(),
                                ),
                              );
                            } else {}
                          },
                          icon: const Icon(Icons.skip_next),
                          iconSize: 45.0,
                          color: GlobalColors.whiteColor,
                        ),
                      ],
                    ),
                  ),
                  Padding(
                    padding: const EdgeInsets.symmetric(horizontal: 20.0),
                    child: Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      crossAxisAlignment: CrossAxisAlignment.center,
                      children: [
                        IconButton(
                          onPressed: () {},
                          icon: Icon(
                            Icons.settings,
                            color: GlobalColors.whiteColor,
                          ),
                          iconSize: 35,
                        ),
                        IconButton(
                          onPressed: () {},
                          icon: Icon(
                            Icons.cloud_download,
                            color: GlobalColors.whiteColor,
                          ),
                          iconSize: 35,
                        ),
                      ],
                    ),
                  ),
                ],
              ),
            )
          ],
        ),
      );
}
