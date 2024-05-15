import 'dart:async';
import 'dart:io';
import 'package:audioplayers/audioplayers.dart';
import 'package:flutter/material.dart';
import 'package:spocify/app/globals.dart';
import '../utils/global.colors.dart';
import 'package:flutter/services.dart' show rootBundle;
import 'package:external_path/external_path.dart';

class SongScreenPage extends StatefulWidget {
  const SongScreenPage();

  @override
  SongScreenPageState createState() => SongScreenPageState();
}

class SongScreenPageState extends State<SongScreenPage> {
  late AudioPlayer player = AudioPlayer();

  @override
  void initState() {
    super.initState();

    // Create the audio player.
    player = AudioPlayer();

    // Set the release mode to keep the source after playback has completed.
    player.setReleaseMode(ReleaseMode.stop);

    // Start the player as soon as the app is displayed.
    WidgetsBinding.instance.addPostFrameCallback((_) async {
      await player.play(AssetSource(songs[currentIndexTrending].url));

      await player.resume();
    });
  }

  @override
  void dispose() {
    // Release all sources and dispose the player.
    player.dispose();

    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.transparent,
        automaticallyImplyLeading: false,
        elevation: 0,
      ),
      extendBodyBehindAppBar: true,
      body: PlayerWidget(player: player),
    );
  }
}

// The PlayerWidget is a copy of "/lib/components/player_widget.dart".
//#region PlayerWidget

class PlayerWidget extends StatefulWidget {
  final AudioPlayer player;

  const PlayerWidget({
    required this.player,
    super.key,
  });

  @override
  State<StatefulWidget> createState() {
    return _PlayerWidgetState();
  }
}

class _PlayerWidgetState extends State<PlayerWidget> {
  bool isFavorite = false;

  PlayerState? _playerState;
  Duration? _duration;
  Duration? _position;

  StreamSubscription? _durationSubscription;
  StreamSubscription? _positionSubscription;
  StreamSubscription? _playerCompleteSubscription;
  StreamSubscription? _playerStateChangeSubscription;

  bool get _isPlaying => _playerState == PlayerState.playing;

  bool get _isPaused => _playerState == PlayerState.paused;

  String get _durationText => _duration?.toString().split('.').first ?? '';

  String get _positionText => _position?.toString().split('.').first ?? '';

  AudioPlayer get player => widget.player;

  @override
  void initState() {
    super.initState();
    // Use initial values from player
    _playerState = player.state;
    player.getDuration().then(
          (value) => setState(() {
            _duration = value;
          }),
        );
    player.getCurrentPosition().then(
          (value) => setState(() {
            _position = value;
          }),
        );
    _initStreams();

    for(int i = 0; i < favoriteIndex.length; i++){
      if(favoriteIndex[i] == currentIndexTrending){
        isFavorite = true;
        break;
      }
    }

    if (historyIndex.contains(currentIndexTrending)) {
      historyIndex.remove(currentIndexTrending);
    }

    historyIndex.insert(0, currentIndexTrending);

    print(historyIndex);

  }

  @override
  void setState(VoidCallback fn) {
    // Subscriptions only can be closed asynchronously,
    // therefore events can occur after widget has been disposed.
    if (mounted) {
      super.setState(fn);
    }
  }

  @override
  void dispose() {
    _durationSubscription?.cancel();
    _positionSubscription?.cancel();
    _playerCompleteSubscription?.cancel();
    _playerStateChangeSubscription?.cancel();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) => Scaffold(
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
                          _position != null
                              ? '$_positionText'
                              : _duration != null
                                  ? _durationText
                                  : '',
                          style: TextStyle(
                            fontSize: 16,
                            color: GlobalColors.textColor,
                          ),
                        ),
                        Text(
                          _position != null
                              ? '$_durationText'
                              : _duration != null
                                  ? _durationText
                                  : '',
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
                      onChanged: (value) async {
                        final duration = _duration;
                        final position = value * duration!.inMilliseconds;
                        player.seek(Duration(milliseconds: position.round()));
                      },
                      value: (_position != null &&
                              _duration != null &&
                              _position!.inMilliseconds > 0 &&
                              _position!.inMilliseconds <
                                  _duration!.inMilliseconds)
                          ? _position!.inMilliseconds /
                              _duration!.inMilliseconds
                          : 0.0,
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
                              await player.stop();
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
                                _isPlaying ? Icons.pause : Icons.play_arrow,
                              ),
                              iconSize: 50,
                              color: GlobalColors.mainColor,
                              onPressed: _isPlaying ? _pause : _play,
                            ),
                          ),
                        ),
                        IconButton(
                          onPressed: () async {
                            if (currentIndexTrending < songs.length - 1) {
                              currentIndexTrending += 1;
                              nextIndexTrending = currentIndexTrending + 1;
                              preIndexTrending = currentIndexTrending - 1;
                              await player.stop();
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
                          onPressed: () {
                            setState(() {
                              isFavorite = !isFavorite;
                              isFavorite ? favoriteIndex.add(currentIndexTrending) : favoriteIndex.remove(currentIndexTrending);
                              print(favoriteIndex);
                            });
                          },
                          icon: Icon(
                            isFavorite ? Icons.favorite : Icons.favorite_border,
                            color: GlobalColors.whiteColor,
                          ),
                          iconSize: 35,
                        ),
                        IconButton(
                          onPressed: () {
                            downMusic(
                                "assets/${songs[currentIndexTrending].url}",
                                "${songs[currentIndexTrending].title}.mp3");
                            print("download done");
                          },
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

  Future<File?> downMusic(String assetPath, String name) async {
    final downloadsDirectory =
        await ExternalPath.getExternalStoragePublicDirectory(
            ExternalPath.DIRECTORY_DOWNLOADS);
    final file = File('${downloadsDirectory}/$name');
    print('${downloadsDirectory}/$name');

    try {
      final byteData = await rootBundle.load(assetPath);

      final buffer = byteData.buffer;
      await file.writeAsBytes(
        buffer.asUint8List(byteData.offsetInBytes, byteData.lengthInBytes),
      );

      return file;
    } catch (e) {
      print(e);
      return null;
    }
  }

  void _initStreams() {
    _durationSubscription = player.onDurationChanged.listen((duration) {
      setState(() => _duration = duration);
    });

    _positionSubscription = player.onPositionChanged.listen(
      (p) => setState(() => _position = p),
    );

    _playerCompleteSubscription = player.onPlayerComplete.listen((event) {
      setState(() {
        _playerState = PlayerState.stopped;
        _position = Duration.zero;
      });
    });

    _playerStateChangeSubscription =
        player.onPlayerStateChanged.listen((state) {
      setState(() {
        _playerState = state;
      });
    });
  }

  Future<void> _play() async {
    await player.resume();
    setState(() => _playerState = PlayerState.playing);
  }

  Future<void> _pause() async {
    await player.pause();
    setState(() => _playerState = PlayerState.paused);
  }

  Future<void> _stop() async {
    await player.stop();
    setState(() {
      _playerState = PlayerState.stopped;
      _position = Duration.zero;
    });
  }
}
