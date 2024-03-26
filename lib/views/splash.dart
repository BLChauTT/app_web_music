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
  void initState() {
    // TODO: implement initState
    runApp();
    super.initState();
  }
  void runApp(){
    Future.delayed(Duration(seconds: 1)).then((value) => Navigator.pushReplacement(
      context,
      MaterialPageRoute(
        builder: (context) => LoginPage(),
      ),
    ));
  }
  @override
  Widget build(BuildContext context) {


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
