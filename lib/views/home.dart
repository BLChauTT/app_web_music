import 'dart:async';

import 'package:flutter/material.dart';
import 'package:spocify/utils/global.colors.dart';
import 'package:spocify/views/login.dart';

class HomePage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return HomePageState();
  }
}

class HomePageState extends State<HomePage> {
  @override
  void initState() {}

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: GlobalColors.mainColor,
      body: Center(
        child: Text(
          "Home",
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
