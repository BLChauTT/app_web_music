import 'dart:async';

import 'package:flutter/material.dart';
import 'package:spocify/utils/global.colors.dart';
import 'package:spocify/views/login.dart';

class SplashPage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return SplashPageState();
  }
}

class SplashPageState extends State<SplashPage> {
  @override
  void initState() {}

  @override
  Widget build(BuildContext context) {

    Timer(const Duration(seconds: 2), () {
      Navigator.push(
        context,
        MaterialPageRoute(
          builder: (context) => LoginPage(),
        ),
      );
    });

    return Scaffold(
      backgroundColor: GlobalColors.mainColor,
      body: Center(
        child: Text(
          "Umisic",
          style: TextStyle(
              color: GlobalColors.textColor,
              fontSize: 30,
              fontWeight: FontWeight.bold
          ),
        ),
      ),
    );
  }
}
