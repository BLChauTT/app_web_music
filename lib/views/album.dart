import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:spocify/utils/global.colors.dart';
import 'package:spocify/views/home.dart';

class AlbumPage extends StatefulWidget {
  @override
  _AlbumPageState createState() => _AlbumPageState();
}

class _AlbumPageState extends State<AlbumPage> {

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
        ),
        body: _albumPage(),
      ),
    );
  }

  Widget _albumPage() {

    return Scaffold(
      backgroundColor: Colors.transparent,
      body: Column(
        children: [
        ],
      ),
    );
  }
}
